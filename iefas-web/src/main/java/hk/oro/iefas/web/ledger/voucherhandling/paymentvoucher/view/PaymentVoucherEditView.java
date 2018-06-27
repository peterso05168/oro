package hk.oro.iefas.web.ledger.voucherhandling.paymentvoucher.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.PrivilegeConstant;
import hk.oro.iefas.core.constant.ShroffConstant;
import hk.oro.iefas.core.constant.WorkFlowAction;
import hk.oro.iefas.core.util.BusinessUtils;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.casemgt.vo.CaseAccountInfoVO;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.dividend.vo.SysApprovalWfVO;
import hk.oro.iefas.domain.organization.vo.ApproverVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.ControlAccountVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherAccountItemVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherBasicInformationVO;
import hk.oro.iefas.domain.shroff.vo.PaymentVoucherDetailVO;
import hk.oro.iefas.domain.shroff.vo.VoucherTypeVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseAccountClientService;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.ledger.maintenance.controlaccount.service.ControlAccountClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.view.VoucherEditView;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3310 $ $Date: 2018-06-26 18:58:21 +0800 (週二, 26 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class PaymentVoucherEditView extends VoucherEditView {

    private static final long serialVersionUID = 1L;

    @Inject
    private PostClientService postClientService;

    @Inject
    private CaseAccountClientService caseAccountClientService;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private ControlAccountClientService controlAccountClientService;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyVOs;

    @Getter
    @Setter
    private List<ApplicationCodeTableVO> paymentMethods;

    @Getter
    @Setter
    private List<ApproverVO> firstApproverList;

    @Getter
    @Setter
    private ApproverVO selectedFirstApprover;

    @Getter
    @Setter
    private List<ApproverVO> secondApproverList;

    @Getter
    @Setter
    private List<ControlAccountVO> creditAccountVOs;

    @Getter
    @Setter
    private ControlAccountVO selectControlAccount;

    @Getter
    @Setter
    private Integer accountId;

    @Getter
    @Setter
    private AnalysisCodeVO selectedAnalysisCode;

    @Getter
    @Setter
    private PaymentVoucherDetailVO paymentVoucherVO;

    @Getter
    @Setter
    private List<PaymentVoucherAccountItemVO> paymentItemVOs;

    @Getter
    @Setter
    private List<PaymentVoucherAccountItemVO> delItemVOs = new ArrayList<PaymentVoucherAccountItemVO>();

    @Getter
    @Setter
    private PaymentVoucherAccountItemVO paymentItemVO = new PaymentVoucherAccountItemVO();

    @Getter
    @Setter
    private List<SysApprovalWfVO> workflowList;

    @Getter
    @Setter
    private Boolean requireSecondApprover = false;

    @PostConstruct
    private void init() {
        log.info("=====PaymentVoucherSearchView init=====");
        paymentMethods = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.PYM.name());
        currencyVOs = currencyClientService.findAll();
        creditAccountVOs = controlAccountClientService.findAllControlAccounts();
        firstApproverList = postClientService.getFirstApprover(SecurityUtils.getCurrenDivisionId());
        secondApproverList = postClientService.getSecondApprover(SecurityUtils.getCurrenDivisionId());

        voucherId = Faces.getRequestParameter("voucherId", Integer.class);
        if (voucherId != null) {
            loadDetail(voucherId);
        } else {
            initActionButton();
            paymentVoucherVO = new PaymentVoucherDetailVO();
            paymentVoucherVO.setPaymentVoucherBasicInformation(new PaymentVoucherBasicInformationVO());
            VoucherTypeVO type = new VoucherTypeVO();
            type.setVoucherTypeCode(ShroffConstant.VT_PAY);
            setIsSubmitted(false);
            setIsApproved(false);
            setIsVerified(false);
            paymentVoucherVO.getPaymentVoucherBasicInformation().setVoucherType(type);
            paymentVoucherVO.getPaymentVoucherBasicInformation().setControlAccount(new ControlAccountVO());
            paymentVoucherVO.getPaymentVoucherBasicInformation().setIsHardCopy(false);
            paymentVoucherVO.getPaymentVoucherBasicInformation().setVoucherTotalAmount(BigDecimal.ZERO);
            paymentVoucherVO.getPaymentVoucherBasicInformation().setStatus(CoreConstant.VOUCHER_STATUS_DRAFT);
            paymentVoucherVO.getPaymentVoucherBasicInformation().setPreparerId(SecurityUtils.getCurrentPostId());
            paymentItemVOs = new ArrayList<PaymentVoucherAccountItemVO>();
        }
    }

    public void save() {
        log.info("save() start");
        if (CommonUtils.isNotEmpty(paymentItemVOs)) {
            voucherId
                = voucherClientService.savePaymentVoucher(preparePaymentVoucherDetail(WorkFlowAction.Save.getCode()));
            if (voucherId == null) {
                Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
                setSaveSuccessed(false);
            } else {
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
                setSaveSuccessed(true);
            }
            loadDetail(voucherId);
        } else {
            Messages
                .addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_VOUCHER_ACCOUNT_ITEM_REQUIRED));
        }
        log.info("save() end");
    }

    private void loadDetail(Integer voucherId) {
        paymentVoucherVO = voucherClientService.findPaymentVoucher(voucherId);
        attachmentList = voucherAttachmentClientService.findVoucherAttachmentByVoucher(voucherId);
        if (paymentVoucherVO.getPaymentVoucherBasicInformation().getControlAccount() == null) {
            paymentVoucherVO.getPaymentVoucherBasicInformation().setControlAccount(new ControlAccountVO());
        }
        setIsSubmitted(CoreConstant.VOUCHER_STATUS_SUBMITTED
            .equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getStatus())
            || CoreConstant.VOUCHER_STATUS_APPROVED
                .equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getStatus())
            || CoreConstant.VOUCHER_STATUS_SUBMITTED_FOR_2ND_APPROVAL
                .equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getStatus())
            || CoreConstant.VOUCHER_STATUS_VERIFIED
                .equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getStatus()));
        setIsApproved(CoreConstant.VOUCHER_STATUS_APPROVED
            .equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getStatus())
            || CoreConstant.VOUCHER_STATUS_VERIFIED
                .equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getStatus()));
        setIsVerified(CoreConstant.VOUCHER_STATUS_VERIFIED
            .equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getStatus()));

        isSecondApproverRequired();
        paymentItemVOs = CommonUtils.isNotEmpty(paymentVoucherVO.getPaymentVoucherItems())
            ? paymentVoucherVO.getPaymentVoucherItems() : new ArrayList<PaymentVoucherAccountItemVO>();
        if (paymentVoucherVO.getPaymentVoucherBasicInformation().getControlAccount() == null) {
            paymentVoucherVO.getPaymentVoucherBasicInformation().setControlAccount(new ControlAccountVO());
        }
        setAction(paymentVoucherVO.getAction());
    }

    public void searchAccountInfo() {
        log.info("searchAccountInfo() start");
        if (CommonUtils.isNotEmpty(paymentItemVO.getCaseNoValue())
            && CommonUtils.isNotEmpty(paymentItemVO.getCaseTypeCodeValue())
            && CommonUtils.isNotEmpty(paymentItemVO.getCaseYearValue())
            && CommonUtils.isNotEmpty(paymentItemVO.getAccountTypeCodeValue())) {
            CaseAccountInfoVO caseAccountInfoVO = caseAccountClientService.findByAccountNumber(BusinessUtils
                .genAccountNumber(paymentItemVO.getCaseTypeCodeValue(), paymentItemVO.getAccountTypeCodeValue(),
                    paymentItemVO.getCaseNoValue(), paymentItemVO.getCaseYearValue()));
            if (caseAccountInfoVO != null) {
                paymentItemVO.setAccount(caseAccountInfoVO);
            }
        }
        log.info("searchAccountInfo() end");
    }

    public void saveVoucherItem() {
        log.info("saveVoucherItem() start -");
        if (!paymentItemVO.getAccount().getCurrency().getCurcyId()
            .equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getCurrencyId())) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EQUAL,
                MsgParamCodeConstant.ACCOUNT_CURRENCY, MsgParamCodeConstant.SELECTED_CURRENCY));
        } else if (paymentItemVO.getAccount().getLiquidCashAmount().compareTo(paymentItemVO.getAmount()) < 0) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_LIQUID_CASH_NOT_ENOUGH));
        } else {
            if (paymentItemVO.getVoucherItemNo() == null || paymentItemVO.getVoucherItemNo() == 0) {
                paymentItemVO.setVoucherItemNo(CommonUtils.isNotEmpty(paymentItemVOs) ? paymentItemVOs.size() + 1 : 1);
            }
            paymentItemVO.setStatus(CoreConstant.STATUS_ACTIVE);
            paymentItemVOs.add(paymentItemVO);
            calculateTotalItemAmount();
            isSecondApproverRequired();
            hideComponent("accountDialog");
        }
        log.info("saveVoucherItem() end -");
    }

    public void deleteVoucherItem() {
        log.info("deleteVoucherItem() start -");
        if (paymentItemVO.getVoucherItemId() == null || paymentItemVO.getVoucherItemId() == 0) {
            paymentItemVOs.remove(paymentItemVO);
        }
        paymentItemVO.setStatus(CoreConstant.STATUS_DELETE);
        delItemVOs.add(paymentItemVO);

        Integer voucherItemNo = paymentItemVO.getVoucherItemNo();
        for (int i = voucherItemNo - 1; i < paymentItemVOs.size(); i++) {
            paymentItemVOs.get(i).setVoucherItemNo(i + 1);;
        }

        calculateTotalItemAmount();
        isSecondApproverRequired();
        log.info("deleteVoucherItem() start -");
    }

    public void onAnalysisCodeSelect(SelectEvent event) {
        log.info("onAnalysisCodeSelect() start");
        AnalysisCodeVO analysisCode = (AnalysisCodeVO)event.getObject();
        paymentItemVO.setAnalysisCode(analysisCode.getAnalysisCode());
        paymentItemVO.setAnalysisCodeId(analysisCode.getAnalysisCodeId());
        log.info("onAnalysisCodeSelect() end");
    }

    public void selectControlAccount() {
        log.info("selectControlAccount() start");
        for (ControlAccountVO account : creditAccountVOs) {
            if (account.getCtlAcId()
                .equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getControlAccount().getCtlAcId()))
                paymentVoucherVO.getPaymentVoucherBasicInformation().setControlAccount(account);
            break;
        }
        log.info("selectControlAccount() start");
    }

    public void prepareAccount() {
        log.info("prepareAccount() start");
        selectedAnalysisCode.setAnalysisCode(paymentItemVO.getAnalysisCode());
        selectedAnalysisCode.setAnalysisCodeId(paymentItemVO.getAnalysisCodeId());
        log.info("prepareAccount() end");
    }

    public void handleAccountCreateDialogClose() {
        log.info("handleAccountCreateDialogClose() start");
        paymentItemVO = new PaymentVoucherAccountItemVO();
        log.info("handleAccountCreateDialogClose() end");
    }

    public void calculateTotalItemAmount() {
        log.info("calculateTotalItemAmount() start");
        paymentVoucherVO.getPaymentVoucherBasicInformation().setVoucherTotalAmount(paymentItemVOs.stream()
            .reduce(BigDecimal.ZERO, (sum, item) -> sum = sum.add(item.getAmount()), (a, b) -> a = a.add(b)));
        log.info("calculateTotalItemAmount() start");
    }

    public void isSecondApproverRequired() {
        log.info("isSecondApproverRequired() start");
        selectedFirstApprover = null;
        firstApproverList.stream().forEach(item -> {
            if (item.getPostId().equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getFirstApproverId())) {
                selectedFirstApprover = item;
            }
        });
        if (selectedFirstApprover != null
            && paymentVoucherVO.getPaymentVoucherBasicInformation().getVoucherTotalAmount()
                .compareTo(new BigDecimal(selectedFirstApprover.getRank().getVoucherLimit().toString())) >= 0) {
            requireSecondApprover = true;
        } else {
            requireSecondApprover = false;
            paymentVoucherVO.getPaymentVoucherBasicInformation().setSecondApproverId(null);
        }
        log.info("isSecondApproverRequired() start");
    }

    /**
     * money transaction function
     */
    public void executeTransaction(List<PaymentVoucherAccountItemVO> itemList, String action) {
        log.info("executeTransaction() start ");
        if (CommonUtils.isNotEmpty(itemList)) {
            switch (WorkFlowAction.getByCode(action)) {
                case Submit:
                    itemList.stream().forEach(item -> {
                        item.getAccount().setOnHoldAmountCr(item.getAmount());
                    });
                    selectControlAccount.setLiquidCashAmount(
                        paymentVoucherVO.getPaymentVoucherBasicInformation().getVoucherTotalAmount());
                    break;

                case Approve:
                    itemList.stream().forEach(item -> {
                        item.getAccount().setOnHoldAmountCr(BigDecimal.ZERO);
                        item.getAccount()
                            .setLiquidCashAmount(item.getAccount().getLiquidCashAmount().subtract(item.getAmount()));
                    });
                    break;

                case SubmitFor2ndApprove:
                    itemList.stream().forEach(item -> {
                        if (requireSecondApprover) {
                            item.getAccount().setOnHoldAmountCr(BigDecimal.ZERO);
                            item.getAccount().setLiquidCashAmount(
                                item.getAccount().getLiquidCashAmount().subtract(item.getAmount()));
                        }
                    });
                    break;

                case Verify:
                    selectControlAccount.setOnHoldAmountCr(BigDecimal.ZERO);
                    selectControlAccount.setBalance(selectControlAccount.getBalance()
                        .subtract(paymentVoucherVO.getPaymentVoucherBasicInformation().getVoucherTotalAmount()));
                    break;

                case Reject:
                    itemList.stream().forEach(item -> {
                        item.getAccount().setOnHoldAmountCr(BigDecimal.ZERO);
                    });
                    selectControlAccount.setLiquidCashAmount(BigDecimal.ZERO);
                    break;

                case Reverse:
                    itemList.stream().forEach(item -> {
                        item.getAccount()
                            .setLiquidCashAmount(item.getAccount().getLiquidCashAmount().add(item.getAmount()));
                    });
                    selectControlAccount.setBalance(selectControlAccount.getBalance()
                        .add(paymentVoucherVO.getPaymentVoucherBasicInformation().getVoucherTotalAmount()));
                    break;

                default:
                    break;
            }
        }
        log.info("executeTransaction() end ");
    }

    // menuitem function
    public void submit() {
        log.info("submit() start ");
        if (validateSubmit()) {
            paymentVoucherVO.getPaymentVoucherBasicInformation().setSubmissionDate(appResourceUtils.getBusinessDate());

            voucherId = voucherClientService
                .submitPaymentVoucher(preparePaymentVoucherDetail(WorkFlowAction.Submit.getCode()));
            loadDetail(voucherId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SUBMIT_SUCCESS));
        }
        log.info("submit() end ");
    }

    private PaymentVoucherDetailVO preparePaymentVoucherDetail(String workFlowActionCode) {
        List<PaymentVoucherAccountItemVO> list = new ArrayList<>();
        list.addAll(delItemVOs);
        list.addAll(paymentItemVOs);
        if (CommonUtils.isBlank(paymentVoucherVO.getPaymentVoucherBasicInformation().getVoucherNo())) {
            paymentVoucherVO.getPaymentVoucherBasicInformation().setVoucherNo("");
        }
        paymentVoucherVO.setPaymentVoucherItems(list);
        PaymentVoucherBasicInformationVO paymentVoucherBasicInformation
            = paymentVoucherVO.getPaymentVoucherBasicInformation();
        SysWorkFlowRuleVO sysWorkFlowRuleVO
            = getAfterStatusByAction(paymentVoucherBasicInformation.getStatus(), workFlowActionCode);
        paymentVoucherBasicInformation.setStatus(sysWorkFlowRuleVO.getAfterStatus().getCodeValue());
        paymentVoucherBasicInformation.setSysWorkFlowRule(sysWorkFlowRuleVO);
        // executeTransaction(paymentVoucherVO.getPaymentVoucherItems(), WorkFlowAction.Submit.getCode());
        return paymentVoucherVO;
    }

    public boolean validateSubmit() {
        log.info("validateSubmit() start ");
        boolean hasFirstApprover = true;
        boolean hasGroupCode = true;
        boolean hasPayeeName = true;
        boolean hasCurrency = true;
        if (paymentVoucherVO.getPaymentVoucherBasicInformation().getFirstApproverId() == null
            || paymentVoucherVO.getPaymentVoucherBasicInformation().getFirstApproverId() == 0) {
            hasFirstApprover = false;
            Messages.addError(null, appResourceUtils.getRequiredMessage(MsgParamCodeConstant.FIRST_APPROVER));
        }
        if (CommonUtils.isBlank(paymentVoucherVO.getPaymentVoucherBasicInformation().getGroupCode())) {
            hasGroupCode = false;
            Messages.addError(null, appResourceUtils.getRequiredMessage(MsgParamCodeConstant.GROUP_CODE));
        }
        if (CommonUtils.isBlank(paymentVoucherVO.getPaymentVoucherBasicInformation().getPayeeName())) {
            hasPayeeName = false;
            Messages.addError(null, appResourceUtils.getRequiredMessage(MsgParamCodeConstant.PAYEE_NAME));
        }
        if (paymentVoucherVO.getPaymentVoucherBasicInformation().getCurrencyId() == null
            || paymentVoucherVO.getPaymentVoucherBasicInformation().getCurrencyId() == 0) {
            hasCurrency = false;
            Messages.addError(null, appResourceUtils.getRequiredMessage(MsgParamCodeConstant.CURRENCY));
        }

        List<String> cashNotEnough = new ArrayList<String>();
        List<String> currencyNotEqual = new ArrayList<String>();
        List<String> amountNotExist = new ArrayList<String>();
        List<String> analysisCodeNotExist = new ArrayList<String>();
        boolean hasEnoughCash = true;
        boolean hasSameCurrency = true;
        boolean hasAmount = true;
        boolean hasAnalysisCode = true;
        paymentItemVOs.stream().forEach(item -> {
            CaseAccountInfoVO account = item.getAccount();
            BigDecimal liquidCashAmount = account.getLiquidCashAmount().subtract(account.getOnHoldAmountCr());
            if (item.getAmount() != null && liquidCashAmount.compareTo(item.getAmount()) < 0) {
                cashNotEnough.add(account.getCaseAcNumber());
            }
            if (!item.getAccount().getCurrency().getCurcyId()
                .equals(paymentVoucherVO.getPaymentVoucherBasicInformation().getCurrencyId())) {
                currencyNotEqual.add(account.getCaseAcNumber());
            }
            if (item.getAmount() == null || item.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                amountNotExist.add(item.getAccount().getCaseAcNumber());
            }

            List<AnalysisCodeVO> code = analysisCodeClientService.findByAnalysisCode(item.getAnalysisCode());
            if (CommonUtils.isEmpty(code) || CommonUtils.isEmpty(
                code.stream().filter(temp -> temp.getVoucherType().getVoucherTypeCode().equals(ShroffConstant.VT_PAY))
                    .collect(Collectors.toList()))) {
                analysisCodeNotExist.add(item.getAccount().getCaseAcNumber());
            }
        });

        if (CommonUtils.isNotEmpty(cashNotEnough)) {
            hasEnoughCash = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_LIQUID_CASH_NOT_ENOUGH),
                    genCaseAccountNumberStr(cashNotEnough)));
        }

        if (CommonUtils.isNotEmpty(currencyNotEqual)) {
            hasSameCurrency = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EQUAL),
                    genCaseAccountNumberStr(currencyNotEqual) + " 's Currency",
                    appResourceUtils.getMessageParam(MsgParamCodeConstant.SELECTED_CURRENCY)));
        }

        if (CommonUtils.isNotEmpty(amountNotExist)) {
            hasAmount = false;
            Messages.addError(null, String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EXIST),
                genCaseAccountNumberStr(amountNotExist)));
        }

        if (CommonUtils.isNotEmpty(analysisCodeNotExist)) {
            hasAnalysisCode = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EXIST),
                    genCaseAccountNumberStr(analysisCodeNotExist) + " : "
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.ANALYSIS_CODE)));
        }

        boolean result = hasFirstApprover && hasGroupCode && hasPayeeName && hasCurrency && hasEnoughCash
            && hasSameCurrency && hasAmount && hasAnalysisCode;
        log.info("validateSubmit() end");
        return result;
    }

    public boolean validateVerify() {
        log.info("validateVerify() start ");
        boolean hasPaymentMethod = true;
        boolean hasCreditAccount = true;
        boolean hasPaymentDate = true;

        if (CommonUtils.isBlank(paymentVoucherVO.getPaymentVoucherBasicInformation().getPaymentMethod())) {
            hasCreditAccount = false;
            Messages.addGlobalError(appResourceUtils.getRequiredMessage(MsgParamCodeConstant.PAYMENT_METHOD));
        }

        ControlAccountVO controlAccount = paymentVoucherVO.getPaymentVoucherBasicInformation().getControlAccount();
        if (controlAccount == null || controlAccount.getCtlAcId() == null) {
            hasCreditAccount = false;
            Messages.addGlobalError(appResourceUtils.getRequiredMessage(MsgParamCodeConstant.CREDIT_ACCOUNT));
        }

        if (paymentVoucherVO.getPaymentVoucherBasicInformation().getVoucherDate() == null) {
            hasPaymentDate = false;
            Messages.addGlobalError(appResourceUtils.getRequiredMessage(MsgParamCodeConstant.VOUCHER_DATE));
        }

        boolean result = hasPaymentMethod && hasCreditAccount && hasPaymentDate;
        log.info("validateVerify() end");
        return result;
    }

    public void verify() {
        log.info("verify() start ");
        if (validateVerify()) {
            voucherId = voucherClientService
                .verifyPaymentVoucher(preparePaymentVoucherDetail(WorkFlowAction.Verify.getCode()));
            loadDetail(voucherId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_VERIFY_SUCCESS));
        }
        log.info("verify() end ");
    }

    public void reject() {
        log.info("reject() start ");
        voucherId
            = voucherClientService.approvePaymentVoucher(preparePaymentVoucherDetail(WorkFlowAction.Reject.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_REJECT_SUCCESS));
        log.info("reject() end ");
    }

    public void delete() {
        log.info("delete() start ");
        paymentVoucherVO.getPaymentVoucherItems().stream().forEach(item -> item.setStatus(CoreConstant.STATUS_DELETE));
        voucherId
            = voucherClientService.deletePaymentVoucher(preparePaymentVoucherDetail(WorkFlowAction.Delete.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_DELETE_SUCCESS));
        log.info("delete() start ");
    }

    public void reverse() {
        log.info("reverse() start ");
        voucherId
            = voucherClientService.reversePaymentVoucher(preparePaymentVoucherDetail(WorkFlowAction.Reverse.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_REVERSE_SUCCESS));
        log.info("reverse() end ");
    }

    public void approve() {
        log.info("approve() start ");
        voucherId
            = voucherClientService.approvePaymentVoucher(preparePaymentVoucherDetail(WorkFlowAction.Approve.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_APPROV_SUCCESS));
        log.info("approve() end ");
    }

    public void submitForSecondApprove() {
        log.info("submitForSecondApprove() start ");
        SysWorkFlowRuleVO sysWorkFlowRuleVO
            = getAfterStatusByAction(paymentVoucherVO.getPaymentVoucherBasicInformation().getStatus(),
                WorkFlowAction.SubmitFor2ndApprove.getCode());
        paymentVoucherVO.getPaymentVoucherBasicInformation()
            .setStatus(sysWorkFlowRuleVO.getAfterStatus().getCodeValue());
        executeTransaction(paymentVoucherVO.getPaymentVoucherItems(), WorkFlowAction.SubmitFor2ndApprove.getCode());
        save();
        log.info("submitForSecondApprove() end ");
    }

    @Override
    public Boolean validateConfirmImportAccount() {
        log.info("validateConfirmImportAccount() start ");
        Boolean result = true;
        List<String> accountNumbers = new ArrayList<String>();
        List<String> analysisCodeNotExist = new ArrayList<String>();
        for (PaymentVoucherAccountItemVO item : importPaymentItemList) {
            CaseAccountInfoVO account
                = caseAccountClientService.findByAccountNumber(item.getAccount().getCaseAcNumber());
            if (account == null) {
                accountNumbers.add(item.getAccount().getCaseAcNumber());
            } else {
                item.setAccount(account);
            }
            List<AnalysisCodeVO> code = analysisCodeClientService.findByAnalysisCode(item.getAnalysisCode());
            if (CommonUtils.isEmpty(code) || CommonUtils.isEmpty(
                code.stream().filter(temp -> temp.getVoucherType().getVoucherTypeCode().equals(ShroffConstant.VT_PAY))
                    .collect(Collectors.toList()))) {
                analysisCodeNotExist.add(item.getAccount().getCaseAcNumber());
            }
        }
        if (CommonUtils.isNotEmpty(accountNumbers)) {
            result = false;
            Messages.addGlobalError(String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EXIST),
                genCaseAccountNumberStr(accountNumbers)));
        }
        if (CommonUtils.isNotEmpty(analysisCodeNotExist)) {
            result = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EXIST),
                    genCaseAccountNumberStr(analysisCodeNotExist) + " : "
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.ANALYSIS_CODE)));
        }
        log.info("validateConfirmImportAccount() start ");
        return result;
    }

    @Override
    public void confirmImportAccount() {
        log.info("confirmImportAccount() start ");
        if (validateConfirmImportAccount()) {
            activeIndex = 0;
            isUploaded = false;
            importPaymentItemList.stream().forEach(item -> {
                if (item.getVoucherItemNo() == null || item.getVoucherItemNo() == 0) {
                    item.setVoucherItemNo(CommonUtils.isNotEmpty(paymentItemVOs) ? paymentItemVOs.size() + 1 : 1);
                }
            });
            paymentItemVOs.addAll(importPaymentItemList);
            calculateTotalItemAmount();
        }
        log.info("confirmImportAccount() end");
    }

    @Override
    public String getVoucherTypeCode() {
        return ShroffConstant.VT_PAY;
    }

    @Override
    public String getPrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_PVM;
    }

}
