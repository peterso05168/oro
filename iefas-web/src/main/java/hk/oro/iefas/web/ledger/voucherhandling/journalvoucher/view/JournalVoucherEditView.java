/**
 * 
 */
package hk.oro.iefas.web.ledger.voucherhandling.journalvoucher.view;

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
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.dividend.vo.ApproveHistoryVO;
import hk.oro.iefas.domain.organization.vo.ApproverVO;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.domain.shroff.vo.JournalTypeVO;
import hk.oro.iefas.domain.shroff.vo.JournalVoucherAccountItemVO;
import hk.oro.iefas.domain.shroff.vo.JournalVoucherBasicInformationVO;
import hk.oro.iefas.domain.shroff.vo.JournalVoucherDetailVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseAccountClientService;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.JournalTypeClientService;
import hk.oro.iefas.web.ledger.voucherhandling.common.service.VoucherClientService;
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
public class JournalVoucherEditView extends VoucherEditView {

    private static final long serialVersionUID = 1L;

    @Inject
    private JournalTypeClientService journalTypeClientService;

    @Inject
    private PostClientService postClientService;

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private CaseAccountClientService caseAccountClientService;

    @Inject
    private VoucherClientService voucherClientService;

    @Getter
    @Setter
    private JournalVoucherBasicInformationVO journalVoucherBasicInformation;

    @Getter
    @Setter
    private List<JournalTypeVO> journalTypes;

    @Getter
    @Setter
    private List<ApproverVO> approvers;

    @Getter
    @Setter
    private List<CurrencyBasicInfoVO> currencyBasicInfos;

    @Getter
    @Setter
    private JournalVoucherAccountItemVO journalVoucherAccountItem;

    @Getter
    @Setter
    private List<JournalVoucherAccountItemVO> journalVoucherAccountItemList;

    private List<JournalVoucherAccountItemVO> deletedAccountItemList;

    @Getter
    @Setter
    private BigDecimal totalAmountCr;

    @Getter
    @Setter
    private BigDecimal totalAmountDr;

    @Getter
    @Setter
    private List<ApproveHistoryVO> approveHistories;

    @PostConstruct
    private void init() {
        log.info("======JournalVoucherEditView init======");
        voucherId = Faces.getRequestParameter("voucherId", Integer.class);

        journalTypes = journalTypeClientService.findAll();
        approvers = postClientService.getFirstApprover(SecurityUtils.getCurrenDivisionId());
        currencyBasicInfos = currencyClientService.findAll();
        journalVoucherAccountItem = new JournalVoucherAccountItemVO();

        if (voucherId != null) {
            loadDetail(voucherId);
        } else {
            initActionButton();
            journalVoucherBasicInformation = new JournalVoucherBasicInformationVO();
            journalVoucherAccountItemList = new ArrayList<>();
            journalVoucherBasicInformation.setPreparerId(SecurityUtils.getCurrentPostId());
            journalVoucherBasicInformation.setStatus(sysWfInitialStatusVO.getStatus().getCodeValue());
            totalAmountCr = BigDecimal.ZERO;
            totalAmountDr = BigDecimal.ZERO;
            setIsSubmitted(false);
            setIsApproved(false);
        }

    }

    private void loadDetail(Integer voucherId) {
        JournalVoucherDetailVO journalVoucherDetailVO = voucherClientService.findJournalVoucher(voucherId);
        journalVoucherBasicInformation = journalVoucherDetailVO.getJournalVoucherBasicInformation();
        journalVoucherAccountItemList = journalVoucherDetailVO.getJournalVoucherAccountItems() == null
            ? new ArrayList<>() : journalVoucherDetailVO.getJournalVoucherAccountItems();
        approveHistories = journalVoucherDetailVO.getApproveHistories();
        setAction(journalVoucherDetailVO.getAction());
        deletedAccountItemList = new ArrayList<>();
        setIsSubmitted(CoreConstant.VOUCHER_STATUS_SUBMITTED.equals(journalVoucherBasicInformation.getStatus())
            || CoreConstant.VOUCHER_STATUS_APPROVED.equals(journalVoucherBasicInformation.getStatus())
            || CoreConstant.VOUCHER_STATUS_SUBMITTED_FOR_2ND_APPROVAL.equals(journalVoucherBasicInformation.getStatus())
            || CoreConstant.VOUCHER_STATUS_VERIFIED.equals(journalVoucherBasicInformation.getStatus()));
        setIsApproved(CoreConstant.VOUCHER_STATUS_APPROVED.equals(journalVoucherBasicInformation.getStatus())
            || CoreConstant.VOUCHER_STATUS_VERIFIED.equals(journalVoucherBasicInformation.getStatus()));
        // refreshActionButton(journalVoucherBasicInformation.getStatus());
        attachmentList = voucherAttachmentClientService.findVoucherAttachmentByVoucher(voucherId);
        calculateTotalItemAmount();
    }

    public void save() {
        log.info("save() start");
        if (CommonUtils.isNotEmpty(journalVoucherAccountItemList)) {
            voucherId
                = voucherClientService.saveJournalVoucher(prepareJournalVoucherDetail(WorkFlowAction.Save.getCode()));

            loadDetail(voucherId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
        } else {
            Messages.addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_VOUCHER_ACCOUNT_ITEM_REQUIRED));
        }
        log.info("save() end");
    }

    private JournalVoucherDetailVO prepareJournalVoucherDetail(String workFlowActionCode) {
        SysWorkFlowRuleVO sysWorkFlowRuleVO
            = getAfterStatusByAction(journalVoucherBasicInformation.getStatus(), workFlowActionCode);
        journalVoucherBasicInformation.setStatus(sysWorkFlowRuleVO.getAfterStatus().getCodeValue());
        journalVoucherBasicInformation.setSysWorkFlowRule(sysWorkFlowRuleVO);
        JournalVoucherDetailVO journalVoucherDetailVO = new JournalVoucherDetailVO();
        journalVoucherDetailVO.setJournalVoucherBasicInformation(journalVoucherBasicInformation);
        journalVoucherDetailVO.setJournalVoucherAccountItems(new ArrayList<>());
        if (CommonUtils.isNotEmpty(journalVoucherAccountItemList)) {
            journalVoucherDetailVO.getJournalVoucherAccountItems().addAll(journalVoucherAccountItemList);

            BigDecimal totalAmount = totalAmountDr.subtract(totalAmountCr);
            journalVoucherBasicInformation.setVoucherTotalAmount(totalAmount);

        } else {
            journalVoucherBasicInformation.setVoucherTotalAmount(BigDecimal.ZERO);
        }
        if (CommonUtils.isNotEmpty(deletedAccountItemList)) {
            journalVoucherDetailVO.getJournalVoucherAccountItems().addAll(deletedAccountItemList);
        }
        return journalVoucherDetailVO;
    }

    public void saveAccount() {
        log.info("saveAccount() start");
        if (journalVoucherAccountItem.getAccount() == null) {
            Messages.addError("accountCreateForm:msgs",
                appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.CASE_ACCOUNT));
            return;
        }

        journalVoucherAccountItem.setStatus(CoreConstant.STATUS_ACTIVE);
        if (journalVoucherAccountItem.getVoucherItemNo() == null) {
            journalVoucherAccountItem.setVoucherItemNo(this.journalVoucherAccountItemList.size() + 1);
        }
        if (!this.journalVoucherAccountItemList.contains(journalVoucherAccountItem)) {
            this.journalVoucherAccountItemList.add(journalVoucherAccountItem);
        }
        calculateTotalItemAmount();
        hideComponent("accountDialog");
        log.info("saveAccount() end");
    }

    public void deleteAccount() {
        log.info("deleteAccount() start");
        if (journalVoucherAccountItem != null) {
            journalVoucherAccountItem.setStatus(CoreConstant.VOUCHER_STATUS_DELETED);
            if (journalVoucherAccountItem.getVoucherItemId() != null) {
                this.deletedAccountItemList.add(journalVoucherAccountItem);
            }
            this.journalVoucherAccountItemList.remove(journalVoucherAccountItem);

            Integer voucherItemNo = journalVoucherAccountItem.getVoucherItemNo();

            for (int i = voucherItemNo - 1; i < journalVoucherAccountItemList.size(); i++) {
                journalVoucherAccountItemList.get(i).setVoucherItemNo(i + 1);;
            }
            calculateTotalItemAmount();
        }
        log.info("deleteAccount() end");
    }

    private void calculateTotalItemAmount() {
        log.info("calculateTotalItemAmount() start");
        if (CommonUtils.isNotEmpty(journalVoucherAccountItemList)) {
            totalAmountCr = BigDecimal.ZERO;
            totalAmountDr = BigDecimal.ZERO;

            journalVoucherAccountItemList.stream().forEach(item -> {
                if (item.getAmountDr() != null) {
                    totalAmountDr = totalAmountDr.add(item.getAmountDr());
                } else if (item.getAmountCr() != null) {
                    totalAmountCr = totalAmountCr.add(item.getAmountCr());
                }
            });
        }

        log.info("calculateTotalItemAmount() end");
    }

    public void handleAccountCreateDialogClose() {
        log.info("handleAccountCreateDialogClose() start");
        journalVoucherAccountItem = new JournalVoucherAccountItemVO();
        setSelectedAnalysisCode(new AnalysisCodeVO());
        log.info("handleAccountCreateDialogClose() end");
    }

    public void searchCaseInfo() {
        log.info("autoSearchAccount() start");
        if (CommonUtils.isNotEmpty(journalVoucherAccountItem.getCaseNoValue())
            && CommonUtils.isNotEmpty(journalVoucherAccountItem.getCaseTypeCodeValue())
            && CommonUtils.isNotEmpty(journalVoucherAccountItem.getCaseYearValue())
            && CommonUtils.isNotEmpty(journalVoucherAccountItem.getAccountTypeCodeValue())) {
            CaseAccountInfoVO caseAccountInfoVO = caseAccountClientService
                .findByAccountNumber(BusinessUtils.genAccountNumber(journalVoucherAccountItem.getCaseTypeCodeValue(),
                    journalVoucherAccountItem.getAccountTypeCodeValue(), journalVoucherAccountItem.getCaseNoValue(),
                    journalVoucherAccountItem.getCaseYearValue()));
            if (caseAccountInfoVO != null) {
                journalVoucherAccountItem.setAccount(caseAccountInfoVO);
                CaseVO caseInfo = caseAccountInfoVO.getCaseInfo();
                if (caseInfo != null) {
                    journalVoucherAccountItem.setCaseName(caseInfo.getCaseName());
                }
            }
        }
        log.info("autoSearchAccount() end");
    }

    public void onAnalysisCodeSelect(SelectEvent event) {
        log.info("onAnalysisCodeSelect() start");
        AnalysisCodeVO analysisCode = (AnalysisCodeVO)event.getObject();
        journalVoucherAccountItem.setAnalysisCodeId(analysisCode.getAnalysisCodeId());
        journalVoucherAccountItem.setAnalysisCode(analysisCode.getAnalysisCode());
        log.info("onAnalysisCodeSelect() end");
    }

    public void prepareAccount() {
        log.info("prepareAccount() start");
        getSelectedAnalysisCode().setAnalysisCode(journalVoucherAccountItem.getAnalysisCode());
        getSelectedAnalysisCode().setAnalysisCodeId(journalVoucherAccountItem.getAnalysisCodeId());
        log.info("prepareAccount() end");
    }

    public void submit() {
        log.info("submit() start ");
        if (validateSubmit()) {
            journalVoucherBasicInformation.setSubmissionDate(appResourceUtils.getBusinessDate());
            voucherId = voucherClientService
                .submitJournalVoucher(prepareJournalVoucherDetail(WorkFlowAction.Submit.getCode()));
            loadDetail(voucherId);
            setIsSubmitted(true);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SUBMIT_SUCCESS));
        }
        log.info("submit() end ");
    }

    public boolean validateSubmit() {
        log.info("validateSubmit() start ");
        boolean hasFirstApprover = true;
        boolean hasCurrency = true;
        boolean hasGroupCode = true;
        boolean hasVoucherItem = true;
        if (journalVoucherBasicInformation.getFirstApproverId() == null) {
            hasFirstApprover = false;
            Messages.addError(null, appResourceUtils.getRequiredMessage(MsgParamCodeConstant.FIRST_APPROVER));
        }
        if (journalVoucherBasicInformation.getCurrencyId() == null) {
            hasCurrency = false;
            Messages.addError(null, appResourceUtils.getRequiredMessage(MsgParamCodeConstant.CURRENCY));
        }
        if (CommonUtils.isBlank(journalVoucherBasicInformation.getGroupCode())) {
            hasGroupCode = false;
            Messages.addError(null, appResourceUtils.getRequiredMessage(MsgParamCodeConstant.GROUP_CODE));
        }

        if (CommonUtils.isEmpty(journalVoucherAccountItemList)) {
            hasVoucherItem = false;
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_VOUCHER_ACCOUNT_ITEM_REQUIRED));
        }

        List<String> cashNotEnough = new ArrayList<String>();
        List<String> currencyNotEqual = new ArrayList<String>();
        boolean hasEnoughCash = true;
        boolean hasSameCurrency = true;
        journalVoucherAccountItemList.stream().forEach(item -> {
            CaseAccountInfoVO account = item.getAccount();
            BigDecimal liquidCashAmount = account.getLiquidCashAmount().subtract(account.getOnHoldAmountCr());
            if (item.getAmountCr() != null && liquidCashAmount.compareTo(item.getAmountCr()) < 0) {
                cashNotEnough.add(account.getCaseAcNumber());
            }
            if (!item.getAccount().getCurrency().getCurcyId().equals(journalVoucherBasicInformation.getCurrencyId())) {
                currencyNotEqual.add(account.getCaseAcNumber());
            }
        });

        if (CommonUtils.isNotEmpty(cashNotEnough)) {
            StringBuilder cashMsg = new StringBuilder();
            hasEnoughCash = false;
            cashMsg.append(appResourceUtils.getMessageParam(MsgParamCodeConstant.CASE_ACCOUNT)).append(" ");
            cashNotEnough.stream().forEach(item -> {
                cashMsg.append(item).append(" ");
            });
            Messages.addError(null, String.format(
                appResourceUtils.getMessageContent(MsgCodeConstant.MSG_LIQUID_CASH_NOT_ENOUGH), cashMsg.toString()));
        }

        if (CommonUtils.isNotEmpty(currencyNotEqual)) {
            StringBuilder currencyMsg = new StringBuilder();
            hasSameCurrency = false;
            currencyMsg.append(appResourceUtils.getMessageParam(MsgParamCodeConstant.CASE_ACCOUNT)).append(" ");
            currencyNotEqual.stream().forEach(item -> {
                currencyMsg.append(item).append(" ");
            });
            Messages.addError(null, String.format(appResourceUtils.getMessageContent(MsgCodeConstant.MSG_NOT_EQUAL),
                currencyMsg.toString(), appResourceUtils.getMessageParam(MsgParamCodeConstant.SELECTED_CURRENCY)));
        }

        boolean hasTotalZero = true;
        if (!totalAmountDr.equals(totalAmountCr)) {
            hasTotalZero = false;
            Messages.addError(null, String.format(appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EQUAL,
                MsgParamCodeConstant.TOTAL_AMOUNT_DR, MsgParamCodeConstant.TOTAL_AMOUNT_CR)));
        }

        boolean result = hasFirstApprover && hasCurrency && hasGroupCode && hasEnoughCash && hasSameCurrency
            && hasTotalZero && hasVoucherItem;
        log.info("validateSubmit() end");
        return result;
    }

    public void approve() {
        log.info("approve() start ");
        voucherId
            = voucherClientService.approveJournalVoucher(prepareJournalVoucherDetail(WorkFlowAction.Approve.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_APPROV_SUCCESS));
        log.info("approve() end ");
    }

    public void reject() {
        log.info("reject() start ");
        voucherId
            = voucherClientService.rejectJournalVoucher(prepareJournalVoucherDetail(WorkFlowAction.Reject.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_REJECT_SUCCESS));
        log.info("reject() end ");
    }

    public void verify() {
        log.info("verify() start ");
        if (journalVoucherBasicInformation.getVoucherDate() == null) {
            Messages.addGlobalError(appResourceUtils.getRequiredMessage(MsgParamCodeConstant.VOUCHER_DATE));
        } else {
            voucherId = voucherClientService
                .verifyJournalVoucher(prepareJournalVoucherDetail(WorkFlowAction.Verify.getCode()));
            loadDetail(voucherId);
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_VERIFY_SUCCESS));
        }
        log.info("verify() end ");
    }

    public void reverse() {
        log.info("reverse() start ");
        voucherId
            = voucherClientService.reverseJournalVoucher(prepareJournalVoucherDetail(WorkFlowAction.Reverse.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_REVERSE_SUCCESS));
        log.info("reverse() end ");
    }

    public void delete() {
        log.info("reverse() start ");
        voucherId
            = voucherClientService.deleteJournalVoucher(prepareJournalVoucherDetail(WorkFlowAction.Delete.getCode()));
        loadDetail(voucherId);
        Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_DELETE_SUCCESS));
        log.info("reverse() end ");
    }

    @Override
    public String getVoucherTypeCode() {
        return ShroffConstant.VT_JOU;
    }

    @Override
    public String getPrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_JVM;
    }

    @Override
    public void confirmImportAccount() {
        log.info("confirmImportAccount() start ");
        if (validateConfirmImportAccount()) {
            activeIndex = 0;
            isUploaded = false;
            importJournalItemList.stream().forEach(item -> {
                if (item.getVoucherItemNo() == null || item.getVoucherItemNo() == 0) {
                    item.setVoucherItemNo(CommonUtils.isNotEmpty(journalVoucherAccountItemList)
                        ? journalVoucherAccountItemList.size() + 1 : 1);
                }
            });
            journalVoucherAccountItemList.addAll(importJournalItemList);
            calculateTotalItemAmount();
        }
        log.info("confirmImportAccount() end");
    }

    @Override
    public Boolean validateConfirmImportAccount() {
        log.info("validateConfirmImportAccount() start ");
        Boolean result = true;
        List<String> accountNumbers = new ArrayList<String>();
        List<String> crDrNumbers = new ArrayList<String>();
        List<String> analysisCodeNotExist = new ArrayList<String>();
        for (JournalVoucherAccountItemVO item : importJournalItemList) {
            CaseAccountInfoVO account
                = caseAccountClientService.findByAccountNumber(item.getAccount().getCaseAcNumber());
            if (account == null) {
                accountNumbers.add(item.getAccount().getCaseAcNumber());
            } else {
                if (item.getAmountCr() != null && item.getAmountCr().compareTo(BigDecimal.ZERO) != 0
                    && item.getAmountDr() != null && item.getAmountDr().compareTo(BigDecimal.ZERO) != 0) {
                    crDrNumbers.add(item.getAccount().getCaseAcNumber());
                } else {
                    item.setCaseName(account.getCaseInfo().getCaseName());
                    item.setAccount(account);
                }
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
        if (CommonUtils.isNotEmpty(crDrNumbers)) {
            result = false;
            Messages.addGlobalError(genCaseAccountNumberStr(crDrNumbers) + " : "
                + appResourceUtils.getMessage(MsgCodeConstant.MSG_INPUT_DR_OR_CR));
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

}
