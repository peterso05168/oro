package hk.oro.iefas.web.dividend.creditorreg.adjudication.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.constant.PrivilegeConstant;
import hk.oro.iefas.core.constant.WorkFlowAction;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.vo.CurrencyBasicInfoVO;
import hk.oro.iefas.domain.casemgt.vo.AddressVO;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorSectionVO;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorVO;
import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.dividend.vo.AdjucationAccountVO;
import hk.oro.iefas.domain.dividend.vo.AdjucationApplyItemVO;
import hk.oro.iefas.domain.dividend.vo.AdjucationGroundVO;
import hk.oro.iefas.domain.dividend.vo.AdjucationItemVO;
import hk.oro.iefas.domain.dividend.vo.AdjucationVO;
import hk.oro.iefas.domain.dividend.vo.CreditorTypeVO;
import hk.oro.iefas.domain.dividend.vo.GroundCodeVO;
import hk.oro.iefas.domain.dividend.vo.PreAdjucationItemVO;
import hk.oro.iefas.domain.dividend.vo.SysApprovalWfVO;
import hk.oro.iefas.domain.system.vo.SysRejectReasonVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.WorkFlowBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.common.service.CommonDividendClientService;
import hk.oro.iefas.web.dividend.creditorreg.adjudication.service.AdjudicationClientService;
import hk.oro.iefas.web.dividend.maintenance.commoncreditor.service.DividendCommonCreditorClientService;
import hk.oro.iefas.web.dividend.maintenance.groundcode.service.GroundCodeClientService;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3163 $ $Date: 2018-06-15 16:09:30 +0800 (週五, 15 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class AdjudicationEditView extends WorkFlowBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Autowired
    private AdjudicationClientService adjudicationClientService;

    @Autowired
    private DividendCommonCreditorClientService dividendCommonCreditorClientService;

    @Autowired
    private CommonClientService commonClientService;

    @Autowired
    private GroundCodeClientService groundCodeClientService;

    @Autowired
    private CommonDividendClientService commonDividendClientService;

    @Autowired
    private CurrencyClientService currencyClientService;

    @Getter
    private List<CommonCreditorVO> commonCreditors;

    @Getter
    private List<CreditorTypeVO> creditorTypes;

    @Getter
    private List<CommonCreditorSectionVO> commonCreditorSections = new ArrayList<CommonCreditorSectionVO>();

    @Getter
    private List<ApplicationCodeTableVO> natureOfClaims = new ArrayList<ApplicationCodeTableVO>();

    @Getter
    private List<ApplicationCodeTableVO> addressTypes;

    @Getter
    private List<CurrencyBasicInfoVO> currencyInfos;

    @Getter
    @Setter
    private AdjucationVO adjucationVO;

    @Getter
    @Setter
    private List<AdjucationAccountVO> adjucationAccounts = new ArrayList<AdjucationAccountVO>();

    @Getter
    @Setter
    private AdjucationAccountVO adjucationAccountVO;

    @Getter
    @Setter
    private AdjucationApplyItemVO adjucationApplyItemVO;

    @Getter
    @Setter
    private AdjucationItemVO adjucationItem;

    @Getter
    private List<GroundCodeVO> groundCodes;
    @Getter
    private Map<String, Integer> groundCodesMap = new HashMap<String, Integer>();
    @Getter
    private List<GroundCodeVO> displayGroundCodes = new ArrayList<GroundCodeVO>();

    @Getter
    @Setter
    private String remarkReson;

    @Getter
    private BigDecimal total = BigDecimal.ZERO;

    @Getter
    private BigDecimal reject = BigDecimal.ZERO;

    @Getter
    @Setter
    private BigDecimal preAdmitted = BigDecimal.ZERO;
    @Getter
    @Setter
    private BigDecimal ordAdmitted = BigDecimal.ZERO;
    @Getter
    @Setter
    private BigDecimal defPreAdmitted = BigDecimal.ZERO;
    @Getter
    @Setter
    private BigDecimal defOrdAdmitted = BigDecimal.ZERO;

    @Getter
    @Setter
    private String[] selectedGroundCodes;

    @Getter
    private String status;

    @Getter
    private List<SysRejectReasonVO> sysRejectReasons;

    @PostConstruct
    private void init() {
        log.info("==============AdjudicationEditView init===================");
        Integer adjucationId = Faces.getRequestParameter(DividendWebConstant.ADJUCATION_ID, Integer.class);
        if (adjucationId != null && adjucationId.intValue() > 0) {
            adjucationVO = adjudicationClientService.searchAdjudication(adjucationId);
            List<AdjucationApplyItemVO> adjucationApplyItems = adjucationVO.getAdjucationApplyItems();
            if (CommonUtils.isNotEmpty(adjucationApplyItems)) {
                BigDecimal zero = BigDecimal.ZERO;
                adjucationApplyItems.forEach(item -> {
                    if (item.getForeignAmount() != null) {
                        total = total.add(item.getForeignAmount());
                        if (item.getForeignRate() != null) {
                            item.setAppHkAmount(item.getForeignRate().multiply(item.getForeignAmount()));
                            reject = reject.add(item.getAppHkAmount().subtract(item.getForeignAmount()));
                        } else {
                            item.setForeignAmount(zero);
                        }
                    } else {
                        item.setForeignAmount(zero);

                    }
                });
            }
            List<AdjucationItemVO> adjucationItems = adjucationVO.getAdjucationItems();
            if (CommonUtils.isNotEmpty(adjucationItems)) {
                adjucationItems.forEach(adjResultItem -> {
                    if (CoreConstant.STATUS_ACTIVE.equals(adjResultItem.getStatus())
                        && adjResultItem.getAdjudicationType() != null) {
                        switch (adjResultItem.getAdjudicationType().getAdjudicationTypeName()) {
                            case DividendConstant.ADJTYPE_PRE:
                                preAdmitted = adjResultItem.getAdmAmount();
                                break;
                            case DividendConstant.ADJTYPE_ORD:
                                ordAdmitted = adjResultItem.getAdmAmount();
                                break;
                            case DividendConstant.ADJTYPE_DEF_PRE:
                                defPreAdmitted = adjResultItem.getAdmAmount();
                                break;
                            case DividendConstant.ADJTYPE_DEF_ORD:
                                defOrdAdmitted = adjResultItem.getAdmAmount();
                                break;
                            default:
                                break;
                        }
                    }
                });
            }
            List<AdjucationGroundVO> groundNos = this.adjucationVO.getGroundNos();
            if (CommonUtils.isNotEmpty(groundNos)) {
                selectedGroundCodes = new String[groundNos.size()];
                for (int i = 0; i < groundNos.size(); i++) {
                    selectedGroundCodes[i] = String.valueOf(groundNos.get(i).getGroundCodeCode());
                }
            }
        } else {
            adjucationVO = adjudicationClientService.searchAdjudication(0);
            adjucationVO.setStatus(initStatus());
            CreditorVO creditorVO = new CreditorVO();
            creditorVO.setStatus(CoreConstant.STATUS_ACTIVE);
            creditorVO.setAddress(new AddressVO());
            CaseVO caseVO = Faces.getFlashAttribute(DividendWebConstant.CASE_VO);
            if (caseVO != null) {
                creditorVO.setCaseInfo(caseVO);
            }
            adjucationVO.setCreditor(creditorVO);

            adjucationVO.setCreditorType(new CreditorTypeVO());
        }

        initData();

        commonCreditors = dividendCommonCreditorClientService.searchAllActCommonCreditors();
        creditorTypes = commonDividendClientService.searchCreditorType();
        List<ApplicationCodeTableVO> list
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.NOC.name());
        if (CommonUtils.isNotEmpty(list)) {
            // exclude nature of claim "Interest"
            list.forEach(noc -> {
                if (!DividendConstant.INTEREST.equals(noc.getCodeValue())) {
                    natureOfClaims.add(noc);
                }
            });
        }
        addressTypes = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.ADT.name());
        // adjudicationTypes = commonDividendClientService.searchGroundCodeTypeList();
        currencyInfos = currencyClientService.findAll();

        groundCodes = groundCodeClientService.findAll();
        if (CommonUtils.isNotEmpty(groundCodes)) {
            groundCodes.forEach(code -> groundCodesMap.put(code.getGroundCodeCode(), code.getGroundCodeId()));
        }
        filterGroundCodes();

        sysRejectReasons = commonClientService.searchRejectReasonList();
    }

    private void initData() {
        refreshActionButton(adjucationVO.getStatus());
        getStatusDesc(adjucationVO.getStatus());
    }

    public void filterGroundCodes() {
        displayGroundCodes.clear();
        if (CommonUtils.isNotEmpty(groundCodes)) {
            displayGroundCodes = groundCodes.stream()
                .filter(item -> this.adjucationVO.getNatureOfClaim() != null
                    && this.adjucationVO.getCreditor().getCaseInfo().getCaseType().getCaseTypeId()
                        .equals(item.getCaseType().getCaseTypeId())
                    && this.adjucationVO.getNatureOfClaim().equals(item.getNatureOfClaim()))
                .collect(Collectors.toList());
        }
    }

    public void filterCommonCreditorSection() {
        commonCreditorSections.clear();
        CreditorVO creditor = adjucationVO.getCreditor();
        if (CommonUtils.isNotEmpty(commonCreditors) && creditor != null) {
            CommonCreditorVO commonCred = commonCreditors.stream()
                .filter(
                    commonCreditorVO -> commonCreditorVO.getCommonCreditorId().equals(creditor.getCommonCreditorId()))
                .findFirst().get();

            if (commonCred != null) {
                creditor.setCreditorNameEng(commonCred.getCommonCreditorName());
                creditor.setCreditorNameChi(commonCred.getCommonCreditorNameChinese());
                creditor.setPayeeName(commonCred.getPayeeName());
                creditor.setPayeeNameChinese(commonCred.getPayeeNameChinese());

                List<CommonCreditorSectionVO> sectionVOs = commonCred.getCommonCreditorSections();
                if (CommonUtils.isNotEmpty(sectionVOs)) {
                    commonCreditorSections.addAll(
                        sectionVOs.stream().filter(section -> CoreConstant.STATUS_ACTIVE.equals(section.getStatus()))
                            .collect(Collectors.toList()));
                }
            }
        }
    }

    // =========================View Account Numbers begin=========================
    public void viewAccountNumbers() {
        setAdjucationAccounts();
        showComponent("acctNumDialog");
    }

    public void cancelAccountNumbers() {
        setAdjucationAccounts();
        hideComponent("acctNumDialog");
    }

    private void setAdjucationAccounts() {
        List<AdjucationAccountVO> beforeList = adjucationVO.getAdjucationAccounts();
        this.adjucationAccounts.clear();
        if (CommonUtils.isNotEmpty(beforeList)) {
            adjucationAccounts = beforeList.stream().filter(acct -> CoreConstant.STATUS_ACTIVE.equals(acct.getStatus()))
                .collect(Collectors.toList());
        }
    }

    public void deleteAccountNumber() {
        this.adjucationAccounts.remove(this.adjucationAccountVO);
    }

    public void addAccountNumber() {
        // check if the num of account number is up to 6, show error message "Add up to 6 accounts"
        if (this.adjucationAccounts.size() < 6) {
            AdjucationAccountVO accountVO = new AdjucationAccountVO();
            accountVO.setStatus(CoreConstant.STATUS_ACTIVE);
            this.adjucationAccounts.add(accountVO);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_ADD_ACCOUNT_ERROR), ""));
        }
    }

    public void saveAccountNumber() {
        if (validateAccountNumbers()) {
            if (this.adjucationVO.getAdjucationAccounts() == null) {
                this.adjucationVO.setAdjucationAccounts(new ArrayList<AdjucationAccountVO>());
            }
            for (AdjucationAccountVO account : this.adjucationAccounts) {
                if (!this.adjucationVO.getAdjucationAccounts().contains(account)) {
                    this.adjucationVO.getAdjucationAccounts().add(account);
                }
            }

            for (AdjucationAccountVO account : this.adjucationVO.getAdjucationAccounts()) {
                if (!this.adjucationAccounts.contains(account)) {
                    account.setStatus(CoreConstant.STATUS_INACTIVE);
                }
            }
            hideComponent("acctNumDialog");
        }
    }

    private boolean validateAccountNumbers() {
        if (CommonUtils.isNotEmpty(this.adjucationAccounts)) {
            for (AdjucationAccountVO account : this.adjucationAccounts) {
                if (account.getAdjucationAccount() == null || "".equals(account.getAdjucationAccount().trim())) {
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, appResourceUtils
                            .getMessage(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.ACCOUNT_NUMBER), ""));
                    return false;
                }
            }
        }
        return true;
    }

    // =========================View Account Numbers end=========================

    private void formAdjucationVO() {
        List<AdjucationItemVO> adjucationItems = this.adjucationVO.getAdjucationItems();
        if (CommonUtils.isNotEmpty(adjucationItems)) {
            for (AdjucationItemVO adjResultItem : adjucationItems) {
                if (DividendConstant.ORDINARY.equals(this.adjucationVO.getNatureOfClaim())) {
                    switch (adjResultItem.getAdjudicationType().getAdjudicationTypeName()) {
                        case DividendConstant.ADJTYPE_PRE:
                            adjResultItem.setAdmAmount(preAdmitted);
                            break;
                        case DividendConstant.ADJTYPE_ORD:
                            adjResultItem.setAdmAmount(ordAdmitted);
                            break;
                        case DividendConstant.ADJTYPE_DEF_PRE:
                            adjResultItem.setAdmAmount(defPreAdmitted);
                            break;
                        case DividendConstant.ADJTYPE_DEF_ORD:
                            adjResultItem.setAdmAmount(defPreAdmitted);
                            break;
                        default:
                            break;
                    }
                } else {
                    adjResultItem.setStatus(CoreConstant.STATUS_INACTIVE);
                }

            }
        }
        if (CommonUtils.isNotEmpty(creditorTypes)) {
            CreditorTypeVO creditorType = creditorTypes.stream()
                .filter(
                    item -> item.getCreditorTypeId().equals(this.adjucationVO.getCreditorType().getCreditorTypeId()))
                .findFirst().get();
            this.adjucationVO.setCreditorType(creditorType);
        }

        if (CommonUtils.isNotEmpty(this.adjucationVO.getGroundNos())) {
            this.adjucationVO.getGroundNos().forEach(no -> no.setStatus(CoreConstant.STATUS_INACTIVE));
        } else {
            this.adjucationVO.setGroundNos(new ArrayList<AdjucationGroundVO>());
        }

        // TODO to show ground codes
        if (selectedGroundCodes != null && selectedGroundCodes.length > 0) {
            for (int i = 0; i < selectedGroundCodes.length; i++) {
                boolean exist = false;
                for (AdjucationGroundVO groundVO : this.adjucationVO.getGroundNos()) {
                    if (Integer.valueOf(selectedGroundCodes[i]).equals(groundVO.getGroundCodeCode())) {
                        groundVO.setStatus(CoreConstant.STATUS_ACTIVE);
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    Integer groundCodeId = groundCodesMap.get(selectedGroundCodes[i]);
                    if (groundCodeId != null) {
                        AdjucationGroundVO newGround = new AdjucationGroundVO();
                        newGround.setGroundCodeId(groundCodeId);
                        newGround.setStatus(CoreConstant.STATUS_ACTIVE);
                        this.adjucationVO.getGroundNos().add(newGround);
                    }
                }
            }
        }

        // set id of ground codes
        List<PreAdjucationItemVO> preAdjucationItems = this.adjucationVO.getPreAdjucationItems();
        if (CommonUtils.isNotEmpty(preAdjucationItems)) {
            preAdjucationItems.forEach(item -> {
                String[] ordGroCodes = item.getOrdGroundCodes();
                if (ordGroCodes != null && ordGroCodes.length > 0) {
                    String[] ordGroNos = new String[ordGroCodes.length];
                    for (int i = 0; i < ordGroCodes.length; i++) {
                        if (groundCodesMap.get(ordGroCodes[i]) != null) {
                            ordGroNos[i] = String.valueOf(groundCodesMap.get(ordGroCodes[i]));
                        }
                    }
                    item.setOrdGroundNos(ordGroNos);
                } else {
                    item.setOrdGroundNos(null);
                }

                String[] rejGroCodes = item.getRejGroundCodes();
                if (rejGroCodes != null && rejGroCodes.length > 0) {
                    String[] rejGroNos = new String[rejGroCodes.length];
                    for (int i = 0; i < rejGroCodes.length; i++) {
                        if (groundCodesMap.get(rejGroCodes[i]) != null) {
                            rejGroNos[i] = String.valueOf(groundCodesMap.get(rejGroCodes[i]));
                        }
                    }
                    item.setRejGroundNos(rejGroNos);
                } else {
                    item.setRejGroundNos(null);
                }
            });
        }
    }

    public void save() {
        formAdjucationVO();
        WorkFlowAction workFlowAction = WorkFlowAction.Save;
        executeAction(workFlowAction);
    }

    private void prepareData(String workFlowActionCode) {
        SysWorkFlowRuleVO sysWorkFlowRuleVO = getAfterStatusByAction(this.adjucationVO.getStatus(), workFlowActionCode);
        adjucationVO.setStatus(sysWorkFlowRuleVO.getAfterStatus().getCodeValue());
    }

    public void submit() {
        WorkFlowAction workFlowAction = WorkFlowAction.Submit;
        executeAction(workFlowAction);
    }

    public void approve() {
        WorkFlowAction workFlowAction = WorkFlowAction.Approve;
        executeAction(workFlowAction);
    }

    public void rejectAdjudication() {
        WorkFlowAction workFlowAction = WorkFlowAction.Reject;
        executeAction(workFlowAction);
        hideComponent("rejectDialog");
        this.remarkReson = null;
    }

    public String delete() {
        if (adjucationVO.getAdjucationId() != null && adjucationVO.getAdjucationId().intValue() > 0) {
            WorkFlowAction workFlowAction = WorkFlowAction.Delete;
            executeAction(workFlowAction);
            return SEARCH_PAGE;
        }
        return null;
    }

    private void executeAction(WorkFlowAction workFlowAction) {
        if (!workFlowAction.equals(WorkFlowAction.Save)
            || (this.adjucationVO.getAdjucationId() == null || this.adjucationVO.getAdjucationId().intValue() <= 0)) {
            SysApprovalWfVO sysApprovalWf = getSysApprovalWf(workFlowAction, getWorkFlowPrivilegeCode(workFlowAction));
            sysApprovalWf.setRemark(this.remarkReson);
            this.adjucationVO.setSysApprovalWf(sysApprovalWf);
        }

        prepareData(workFlowAction.getCode());
        Integer adjucationId = adjudicationClientService.saveAdjudication(this.adjucationVO);
        if (adjucationId != null && adjucationId.intValue() > 0) {
            this.adjucationVO = adjudicationClientService.searchAdjudication(adjucationId);
            if (workFlowAction.equals(WorkFlowAction.Delete)) {
                Faces.setFlashAttribute(DividendWebConstant.DELETE_MSG_CODE, getSuccessMsg(workFlowAction));
            } else {
                initData();
                Messages.addGlobalInfo(appResourceUtils.getMessage(getSuccessMsg(workFlowAction)));
            }
        } else {
            if (workFlowAction.equals(WorkFlowAction.Delete)) {
                Faces.setFlashAttribute(DividendWebConstant.DELETE_MSG_CODE, MsgCodeConstant.MSG_SAVE_FAIL);
            } else {
                Messages.addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
            }
        }
    }

    @Override
    protected String getStatusGroupCode() {
        return ApplicationCodeTableEnum.ADJ.name();
    }

    @Override
    protected String getSubmitPrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_ADJS;
    }

    @Override
    protected String getApprovePrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_ADJA;
    }
}
