package hk.oro.iefas.web.ledger.voucherhandling.receiptvoucher.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.selectonemenu.SelectOneMenu;
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
import hk.oro.iefas.domain.dividend.vo.ApproveHistoryVO;
import hk.oro.iefas.domain.dividend.vo.SysApprovalWfVO;
import hk.oro.iefas.domain.organization.vo.ApproverVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.ChequeVO;
import hk.oro.iefas.domain.shroff.vo.ControlAccountVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherAccountItemVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherBasicInformationVO;
import hk.oro.iefas.domain.shroff.vo.ReceiptVoucherDetailVO;
import hk.oro.iefas.domain.shroff.vo.SearchAnalysisCodeCriteriaVO;
import hk.oro.iefas.domain.shroff.vo.VoucherTypeVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.ledger.chequehandling.incomingcheque.service.ChequeClientService;
import hk.oro.iefas.web.ledger.maintenance.controlaccount.service.ControlAccountClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.view.VoucherEditView;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3051 $ $Date: 2018-06-11 21:15:42 +0800 (Mon, 11 Jun 2018) $
 * @author $Author: george.wu $
 */
@Slf4j
@Named
@ViewScoped
public class ReceiptVoucherEditView extends VoucherEditView {

    private static final long serialVersionUID = 1L;

    @Inject
    private PostClientService postClientService;

    @Inject
    private ControlAccountClientService controlAccountClientService;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private ChequeClientService chequeClientService;

    @Getter
    @Setter
    private boolean caseBtnDisable;

    @Getter
    @Setter
    private ReceiptVoucherDetailVO receiptVoucherDetailVO;

    @Getter
    @Setter
    private ReceiptVoucherBasicInformationVO receiptVoucherBasicInformation;

    @Getter
    @Setter
    private List<ReceiptVoucherAccountItemVO> receiptVoucherAccountItemList;

    @Getter
    @Setter
    private List<ReceiptVoucherAccountItemVO> deleteAccountItemList = new ArrayList<ReceiptVoucherAccountItemVO>();

    @Getter
    @Setter
    private ReceiptVoucherAccountItemVO receiptVoucherAccountItem = new ReceiptVoucherAccountItemVO();

    @Getter
    @Setter
    private List<ControlAccountVO> controlAccountVOs;

    @Getter
    @Setter
    private ControlAccountVO selectControlAccount;

    @Getter
    @Setter
    private List<ApproverVO> approvers;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfos;

    @Getter
    @Setter
    private List<ApplicationCodeTableVO> receiveMethods;

    @Getter
    @Setter
    private AnalysisCodeVO selectedAnalysisCode;

    @Getter
    @Setter
    private List<SysApprovalWfVO> workflowList;

    @Getter
    @Setter
    private List<ApproveHistoryVO> approveHistories;

    @Getter
    @Setter
    private Integer incomingChequeId;

    @Getter
    @Setter
    private ChequeVO chequeVO;

    @PostConstruct
    private void init() {
        log.info("======receiptVoucherEditView init======");
        currencyBasicInfos = currencyClientService.findAll();
        controlAccountVOs = controlAccountClientService.findAllControlAccounts();
        approvers = postClientService.getFirstApprover(SecurityUtils.getCurrenDivisionId());
        receiveMethods = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.RVM.name());
        voucherId = Faces.getRequestParameter("voucherId", Integer.class);
        incomingChequeId = Faces.getRequestParameter("incomingChequeId", Integer.class);
        log.debug("voucherId: " + voucherId);
        if (voucherId != null) {
            loadDetail(voucherId);
        } else {
            initActionButton();
            receiptVoucherDetailVO = new ReceiptVoucherDetailVO();
            receiptVoucherBasicInformation = new ReceiptVoucherBasicInformationVO();
            receiptVoucherDetailVO.setReceiptVoucherBasicInformation(receiptVoucherBasicInformation);
            VoucherTypeVO type = new VoucherTypeVO();
            type.setVoucherTypeCode(ShroffConstant.VT_REC);
            receiptVoucherBasicInformation.setVoucherType(type);
            receiptVoucherBasicInformation.setControlAccount(new ControlAccountVO());
            receiptVoucherBasicInformation.setIsHardCopy(false);
            receiptVoucherBasicInformation.setVoucherTotalAmount(BigDecimal.ZERO);
            receiptVoucherBasicInformation.setStatus(CoreConstant.VOUCHER_STATUS_DRAFT);
            receiptVoucherBasicInformation.setPreparerId(SecurityUtils.getCurrentPostId());
            receiptVoucherAccountItemList = new ArrayList<ReceiptVoucherAccountItemVO>();
            setIsSubmitted(false);
            setIsApproved(false);
            setIsVerified(false);
            caseBtnDisable = true;
        }

        if (incomingChequeId != null) {
            chequeVO = chequeClientService.getIncomingChequeDetail(incomingChequeId);
            receiptVoucherAccountItem.setChequeDate(chequeVO.getChequeDate());
            receiptVoucherAccountItem.setChequeNo(chequeVO.getChequeNo().toString());
        }
    }

    public void cancelImport() {
        log.info("cancelImport() start ");

        accountFile = null;
        importReceiptItemList = new ArrayList<>();
        isUploaded = false;

        log.info("cancelImport() end ");
    }

    @Override
    public String getVoucherTypeCode() {
        return ShroffConstant.VT_REC;
    }

    @Override
    public String getPrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_RVM;
    }

    @Override
    public Boolean validateConfirmImportAccount() {
        log.info("validateConfirmImportAccount() start ");
        Boolean result = true;

        List<String> accountNumbers = new ArrayList<String>();
        List<String> analysisCodes = new ArrayList<String>();
        List<String> chequeNumbers = new ArrayList<String>();
        List<String> chequeDates = new ArrayList<String>();

        for (ReceiptVoucherAccountItemVO item : importReceiptItemList) {
            CaseAccountInfoVO account
                = caseAccountClientService.findByAccountNumber(item.getAccount().getCaseAcNumber());
            if (account == null) {
                accountNumbers.add(item.getAccount().getCaseAcNumber());
            }

            SearchAnalysisCodeCriteriaVO criteria = new SearchAnalysisCodeCriteriaVO();
            criteria.setAnalysisCode(item.getAnalysisCode());
            criteria.setVoucherTypeCodeValue(ShroffConstant.VT_REC);
            Boolean isExisted = analysisCodeClientService.isAnalysisCodeExistedByCriteria(criteria);
            if (!isExisted) {
                analysisCodes.add(item.getAccount().getCaseAcNumber());
            }

            ChequeVO cheque = chequeClientService.getChequeDetailByChequeNo(item.getChequeNo());
            if (cheque == null) {
                chequeNumbers.add(item.getAccount().getCaseAcNumber());
            } else if (cheque.getChequeDate().compareTo(item.getChequeDate()) != 0) {
                chequeDates.add(item.getAccount().getCaseAcNumber());
            }

        }

        if (CommonUtils.isNotEmpty(accountNumbers)) {
            result = false;
            Messages.addGlobalError(String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EXIST),
                genCaseAccountNumberStr(accountNumbers)));
        }

        if (CommonUtils.isNotEmpty(analysisCodes)) {
            result = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EXIST),
                    genCaseAccountNumberStr(analysisCodes) + " : "
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.ANALYSIS_CODE)));
        }

        if (CommonUtils.isNotEmpty(chequeNumbers)) {
            result = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EXIST),
                    genCaseAccountNumberStr(chequeNumbers) + " : "
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.CHEQUE_NUMBER)));
        }

        if (CommonUtils.isNotEmpty(chequeDates)) {
            result = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EQUAL),
                    genCaseAccountNumberStr(chequeDates) + " : "
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.IMPORTED_RECORD)
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.CHEQUE_DATE),
                    appResourceUtils.getMessageParam(MsgParamCodeConstant.CURRENT_CHEQUE)
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.CHEQUE_DATE)));
        }

        log.info("validateConfirmImportAccount() end ");
        return result;
    }

    private void calculateTotalItemAmount() {
        log.info("calculateTotalItemAmount() start");
        receiptVoucherBasicInformation.setVoucherTotalAmount(receiptVoucherAccountItemList.stream()
            .reduce(BigDecimal.ZERO, (sum, item) -> sum = sum.add(item.getVoucherAmount()), (a, b) -> a = a.add(b)));
        log.info("calculateTotalItemAmount() end");
    }

    @Override
    public void confirmImportAccount() {
        log.info("confirmImportAccount() start ");
        if (validateConfirmImportAccount()) {
            activeIndex = 0;
            isUploaded = false;
            importReceiptItemList.stream().forEach(item -> {
                if (item.getVoucherItemNo() == null || item.getVoucherItemNo() == 0) {
                    item.setVoucherItemNo(CommonUtils.isNotEmpty(receiptVoucherAccountItemList)
                        ? receiptVoucherAccountItemList.size() + 1 : 1);
                }
            });
            receiptVoucherAccountItemList.addAll(importReceiptItemList);
            calculateTotalItemAmount();
        }

        log.info("confirmImportAccount() end");
    }

    public void toggleCaseButtonStatus(AjaxBehaviorEvent event) {
        log.info("toggleCaseButtonStatus() start");
        SelectOneMenu selectOneMenu = (SelectOneMenu)event.getComponent();
        if (selectOneMenu.getValue() != null) {
            caseBtnDisable = false;
        } else {
            caseBtnDisable = true;
        }
        log.info("toggleCaseButtonStatus() end");
    }

    public void deleteAccount() {
        log.info("deleteAccount() start");

        if (receiptVoucherAccountItem != null) {
            receiptVoucherAccountItem.setStatus(CoreConstant.STATUS_DELETE);
            if (receiptVoucherAccountItem.getVoucherItemId() != null) {
                deleteAccountItemList.add(receiptVoucherAccountItem);
            }
            receiptVoucherAccountItemList.remove(receiptVoucherAccountItem);

            // Integer voucherItemNo = receiptVoucherAccountItem.getVoucherItemNo();

            for (int i = 1; i < receiptVoucherAccountItemList.size() - 1; i++) {
                receiptVoucherAccountItemList.get(i).setVoucherItemNo(i);
            }

            calculateTotalItemAmount();
        }
        log.info("deleteAccount() end");
    }

    public void prepareAccount() {
        log.info("prepareAccount() start");

        selectedAnalysisCode = new AnalysisCodeVO();
        selectedAnalysisCode.setAnalysisCode(receiptVoucherAccountItem.getAnalysisCode());
        selectedAnalysisCode.setAnalysisCodeId(receiptVoucherAccountItem.getAnalysisCodeId());
        log.info("prepareAccount() end");
    }

    public void handleAccountCreateDialogClose() {
        log.info("handleAccountCreateDialogClose() start");
        receiptVoucherAccountItem = new ReceiptVoucherAccountItemVO();
        if (chequeVO != null) {
            receiptVoucherAccountItem.setChequeNo(chequeVO.getChequeNo().toString());
            receiptVoucherAccountItem.setChequeDate(chequeVO.getChequeDate());
        }
        selectedAnalysisCode = new AnalysisCodeVO();
        log.info("handleAccountCreateDialogClose() end");
    }

    public void saveAccount() {
        log.info("saveAccount() start");
        if (!receiptVoucherAccountItem.getAccount().getCurrency().getCurcyId()
            .equals(receiptVoucherBasicInformation.getCurrencyId())) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EQUAL,
                MsgParamCodeConstant.ACCOUNT_CURRENCY, MsgParamCodeConstant.SELECTED_CURRENCY));
        } else {
            if (receiptVoucherAccountItem.getVoucherItemNo() == null
                || receiptVoucherAccountItem.getVoucherItemNo() == 0) {
                receiptVoucherAccountItem.setVoucherItemNo(CommonUtils.isNotEmpty(receiptVoucherAccountItemList)
                    ? receiptVoucherAccountItemList.size() + 1 : 1);
            }
            receiptVoucherAccountItem.setStatus(CoreConstant.STATUS_ACTIVE);
            receiptVoucherAccountItemList.add(receiptVoucherAccountItem);
            calculateTotalItemAmount();
            hideComponent("accountDialog");
        }
        log.info("saveAccount() end");
    }

    public void onControlAccountSelect() {
        log.info("onControlAccountSelect() start");
        for (ControlAccountVO account : controlAccountVOs) {
            if (account.getCtlAcId()
                .equals(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getControlAccount().getCtlAcId()))
                receiptVoucherDetailVO.getReceiptVoucherBasicInformation().setControlAccount(account);
            break;
        }
        log.info("onControlAccountSelect() end");
    }

    public void onAnalysisCodeSelect(SelectEvent event) {
        log.info("onAnalysisCodeSelect() start");
        AnalysisCodeVO analysisCode = (AnalysisCodeVO)event.getObject();
        receiptVoucherAccountItem.setAnalysisCodeId(analysisCode.getAnalysisCodeId());
        receiptVoucherAccountItem.setAnalysisCode(analysisCode.getAnalysisCode());
        log.info("onAnalysisCodeSelect() end");
    }

    public void searchAccountInfo() {
        log.info("searchAccountInfo() start");
        if (CommonUtils.isNotEmpty(receiptVoucherAccountItem.getCaseNoValue())
            && CommonUtils.isNotEmpty(receiptVoucherAccountItem.getCaseTypeCodeValue())
            && CommonUtils.isNotEmpty(receiptVoucherAccountItem.getCaseYearValue())
            && CommonUtils.isNotEmpty(receiptVoucherAccountItem.getAccountTypeCodeValue())) {
            CaseAccountInfoVO caseAccountInfoVO = caseAccountClientService
                .findByAccountNumber(BusinessUtils.genAccountNumber(receiptVoucherAccountItem.getCaseTypeCodeValue(),
                    receiptVoucherAccountItem.getAccountTypeCodeValue(), receiptVoucherAccountItem.getCaseNoValue(),
                    receiptVoucherAccountItem.getCaseYearValue()));
            if (caseAccountInfoVO != null) {
                receiptVoucherAccountItem.setAccount(caseAccountInfoVO);
            }
        }
        log.info("searchAccountInfo() end");
    }

    public void deleteVoucher() {
        log.info("deleteVoucher() start");
        receiptVoucherDetailVO.getReceiptVoucherAccountItems().stream()
            .forEach(item -> item.setStatus(CoreConstant.STATUS_DELETE));
        voucherId
            = voucherClientService.deleteReceiptVoucher(prepareReceiptVoucherDetailVO(WorkFlowAction.Delete.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_DELETE_SUCCESS));
        log.info("deleteVoucher() end - ");
    }

    private ReceiptVoucherDetailVO prepareReceiptVoucherDetailVO(String workFlowActionCode) {
        List<ReceiptVoucherAccountItemVO> list = new ArrayList<ReceiptVoucherAccountItemVO>();
        list.addAll(deleteAccountItemList);
        list.addAll(receiptVoucherAccountItemList);
        if (CommonUtils.isBlank(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getVoucherNo())) {
            receiptVoucherDetailVO.getReceiptVoucherBasicInformation().setVoucherNo("");
        }

        receiptVoucherDetailVO.setReceiptVoucherAccountItems(list);

        ReceiptVoucherBasicInformationVO receiptVoucherBasicInformationVO
            = receiptVoucherDetailVO.getReceiptVoucherBasicInformation();
        SysWorkFlowRuleVO sysWorkFlowRuleVO
            = getAfterStatusByAction(receiptVoucherBasicInformationVO.getStatus(), workFlowActionCode);
        receiptVoucherBasicInformationVO.setStatus(sysWorkFlowRuleVO.getAfterStatus().getCodeValue());
        receiptVoucherBasicInformationVO.setSysWorkFlowRule(sysWorkFlowRuleVO);
        return receiptVoucherDetailVO;
    }

    public void reverseVoucher() {
        log.info("reverseVoucher start()");
        voucherId = voucherClientService
            .reverseReceiptVoucher(prepareReceiptVoucherDetailVO(WorkFlowAction.Reverse.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_REVERSE_SUCCESS));
        log.info("reverseVoucher end()");
    }

    public void rejectVoucher() {
        log.info("rejectVoucher start()");
        voucherId
            = voucherClientService.rejectReceiptVoucher(prepareReceiptVoucherDetailVO(WorkFlowAction.Reject.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_REJECT_SUCCESS));
        log.info("rejectVoucher end()");
    }

    public boolean validateVerify() {
        log.info("validateVerify() start ");
        boolean hasReceiveMethod = true;
        boolean hasDeditAccount = true;
        boolean hasVoucherDate = true;

        if (CommonUtils.isBlank(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getReceiveMethod())) {
            hasReceiveMethod = false;
            Messages.addGlobalError(appResourceUtils.getRequiredMessage(MsgParamCodeConstant.RECEIVE_METHOD));
        }

        ControlAccountVO controlAccount
            = receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getControlAccount();
        if (controlAccount == null || controlAccount.getCtlAcId() == null) {
            hasDeditAccount = false;
            Messages.addGlobalError(appResourceUtils.getRequiredMessage(MsgParamCodeConstant.DEBIT_ACCOUNT));
        }

        if (receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getVoucherDate() == null) {
            hasVoucherDate = false;
            Messages.addGlobalError(appResourceUtils.getRequiredMessage(MsgParamCodeConstant.VOUCHER_DATE));
        }

        boolean result = hasReceiveMethod && hasDeditAccount && hasVoucherDate;
        log.info("validateVerify() end");
        return result;
    }

    public void verifyVoucher() {
        log.info("verifyVoucher start()");
        if (validateVerify()) {
            voucherId = voucherClientService
                .verifyReceiptVoucher(prepareReceiptVoucherDetailVO(WorkFlowAction.Verify.getCode()));
            loadDetail(voucherId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_VERIFY_SUCCESS));
        }
        log.info("verifyVoucher end()");
    }

    public void approveVoucher() {
        log.info("approveVoucher start()");
        voucherId = voucherClientService
            .approveReceiptVoucher(prepareReceiptVoucherDetailVO(WorkFlowAction.Approve.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_APPROV_SUCCESS));
        log.info("approveVoucher end()");
    }

    private boolean validateSubmit() {
        boolean result = true;
        if (receiptVoucherBasicInformation.getFirstApproverId() == null) {
            Messages.addError(null, appResourceUtils.getRequiredMessage(MsgParamCodeConstant.VOUCHER_APPROVER));
            result = false;
        }

        if (receiptVoucherBasicInformation.getGroupCode() == null) {
            Messages.addError(null, appResourceUtils.getRequiredMessage(MsgParamCodeConstant.GROUP_CODE));
            result = false;
        }

        if (receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getCurrencyId() == null
            || receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getCurrencyId() == 0) {
            result = false;
            Messages.addError(null, appResourceUtils.getRequiredMessage(MsgParamCodeConstant.CURRENCY));
        }

        List<String> currencyNotEqual = new ArrayList<String>();
        List<String> amountNotExist = new ArrayList<String>();
        List<String> analysisCodeNotExist = new ArrayList<String>();
        List<String> chequeNumberNotExist = new ArrayList<String>();
        List<String> chequeDateNotEqual = new ArrayList<String>();

        if (receiptVoucherAccountItemList != null && receiptVoucherAccountItemList.size() > 0) {
            for (ReceiptVoucherAccountItemVO item : receiptVoucherAccountItemList) {
                CaseAccountInfoVO account = item.getAccount();
                if (!item.getAccount().getCurrency().getCurcyId()
                    .equals(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getCurrencyId())) {
                    currencyNotEqual.add(account.getCaseAcNumber());
                }
                if (item.getVoucherAmount() == null || item.getVoucherAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    amountNotExist.add(item.getAccount().getCaseAcNumber());
                }

                List<AnalysisCodeVO> code = analysisCodeClientService.findByAnalysisCode(item.getAnalysisCode());
                if (CommonUtils.isEmpty(code) || CommonUtils.isEmpty(code.stream()
                    .filter(temp -> temp.getVoucherType().getVoucherTypeCode().equals(ShroffConstant.VT_PAY))
                    .collect(Collectors.toList()))) {
                    analysisCodeNotExist.add(item.getAccount().getCaseAcNumber());
                }

                ChequeVO cheque = chequeClientService.getChequeDetailByChequeNo(item.getChequeNo());
                if (cheque == null) {
                    chequeNumberNotExist.add(item.getAccount().getCaseAcNumber());
                } else if (cheque.getChequeDate().compareTo(item.getChequeDate()) != 0) {
                    chequeDateNotEqual.add(item.getAccount().getCaseAcNumber());
                }
            }
        }

        if (CommonUtils.isNotEmpty(currencyNotEqual)) {
            result = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EQUAL),
                    genCaseAccountNumberStr(currencyNotEqual) + " 's Currency",
                    appResourceUtils.getMessageParam(MsgParamCodeConstant.SELECTED_CURRENCY)));
        }

        if (CommonUtils.isNotEmpty(amountNotExist)) {
            result = false;
            Messages.addError(null, String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EXIST),
                genCaseAccountNumberStr(amountNotExist)));
        }

        if (CommonUtils.isNotEmpty(analysisCodeNotExist)) {
            result = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EXIST),
                    genCaseAccountNumberStr(analysisCodeNotExist) + " : "
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.ANALYSIS_CODE)));
        }

        if (CommonUtils.isNotEmpty(chequeNumberNotExist)) {
            result = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EXIST),
                    genCaseAccountNumberStr(chequeNumberNotExist) + " : "
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.CHEQUE_NUMBER)));
        }

        if (CommonUtils.isNotEmpty(chequeDateNotEqual)) {
            result = false;
            Messages.addError(null,
                String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EQUAL),
                    genCaseAccountNumberStr(chequeDateNotEqual) + " : "
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.IMPORTED_RECORD)
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.CHEQUE_DATE),
                    appResourceUtils.getMessageParam(MsgParamCodeConstant.CURRENT_CHEQUE)
                        + appResourceUtils.getMessageParam(MsgParamCodeConstant.CHEQUE_DATE)));
        }

        return result;
    }

    public void submitVoucher() {
        log.info("submitVoucher start()");

        boolean isSaveAvailable = true;
        if (voucherId == null) {
            isSaveAvailable = checkSaveVoucherAvailable();
        }
        boolean isSubmitAvailable = validateSubmit();
        if (!isSaveAvailable || !isSubmitAvailable) {
            return;
        }

        receiptVoucherDetailVO.getReceiptVoucherBasicInformation()
            .setSubmissionDate(appResourceUtils.getBusinessDate());
        voucherId
            = voucherClientService.submitReceiptVoucher(prepareReceiptVoucherDetailVO(WorkFlowAction.Submit.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SUBMIT_SUCCESS));

        log.info("submitVoucher() end");
    }

    private boolean checkSaveVoucherAvailable() {
        boolean isAvailable = true;
        if (receiptVoucherAccountItemList == null || receiptVoucherAccountItemList.size() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_VOUCHER_ACCOUNT_ITEM_REQUIRED), ""));
            isAvailable = false;
        }
        return isAvailable;
    }

    public void save() {
        log.info("save() start");

        if (CommonUtils.isNotEmpty(receiptVoucherAccountItemList)) {
            voucherId
                = voucherClientService.saveReceiptVoucher(prepareReceiptVoucherDetailVO(WorkFlowAction.Save.getCode()));
            if (voucherId == null) {
                Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
                setSaveSuccessed(false);
            } else {
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
                setSaveSuccessed(true);
            }

            loadDetail(voucherId);
        } else {
            Messages.addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_VOUCHER_ACCOUNT_ITEM_REQUIRED));
        }

        log.info("save() end");
    }

    private void loadDetail(Integer voucherId) {
        receiptVoucherDetailVO = voucherClientService.findReceiptVoucher(voucherId);
        if (receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getControlAccount() == null) {
            receiptVoucherDetailVO.getReceiptVoucherBasicInformation().setControlAccount(new ControlAccountVO());
        }
        receiptVoucherBasicInformation = receiptVoucherDetailVO.getReceiptVoucherBasicInformation();
        receiptVoucherAccountItemList = CommonUtils.isEmpty(receiptVoucherDetailVO.getReceiptVoucherAccountItems())
            ? new ArrayList<ReceiptVoucherAccountItemVO>() : receiptVoucherDetailVO.getReceiptVoucherAccountItems();
        deleteAccountItemList = new ArrayList<ReceiptVoucherAccountItemVO>();
        approveHistories = receiptVoucherDetailVO.getApproveHistories();
        attachmentList = voucherAttachmentClientService.findVoucherAttachmentByVoucher(voucherId);

        setIsSubmitted(CoreConstant.VOUCHER_STATUS_SUBMITTED
            .equals(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getStatus())
            || CoreConstant.VOUCHER_STATUS_APPROVED
                .equals(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getStatus())
            || CoreConstant.VOUCHER_STATUS_SUBMITTED_FOR_2ND_APPROVAL
                .equals(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getStatus())
            || CoreConstant.VOUCHER_STATUS_VERIFIED
                .equals(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getStatus()));
        setIsApproved(CoreConstant.VOUCHER_STATUS_APPROVED
            .equals(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getStatus())
            || CoreConstant.VOUCHER_STATUS_VERIFIED
                .equals(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getStatus()));
        setIsVerified(CoreConstant.VOUCHER_STATUS_VERIFIED
            .equals(receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getStatus()));

        if (receiptVoucherDetailVO.getReceiptVoucherBasicInformation().getCurrencyId() != null) {
            caseBtnDisable = false;
        }

        setAction(receiptVoucherDetailVO.getAction());
    }

}
