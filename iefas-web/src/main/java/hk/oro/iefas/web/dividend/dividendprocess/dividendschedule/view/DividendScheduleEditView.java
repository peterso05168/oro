package hk.oro.iefas.web.dividend.dividendprocess.dividendschedule.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.DividendConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.PrivilegeConstant;
import hk.oro.iefas.core.constant.WorkFlowAction;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.dividend.vo.AdjudicationTypeVO;
import hk.oro.iefas.domain.dividend.vo.ApprovedAdjucationResultItemVO;
import hk.oro.iefas.domain.dividend.vo.CreateDividendScheduleVO;
import hk.oro.iefas.domain.dividend.vo.CreditorTypeVO;
import hk.oro.iefas.domain.dividend.vo.DividendChequeVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleCreditorVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleDistVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleItemVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleVO;
import hk.oro.iefas.domain.dividend.vo.SysApprovalWfVO;
import hk.oro.iefas.domain.dividend.vo.WithheldReasonVO;
import hk.oro.iefas.domain.shroff.vo.ChequeVO;
import hk.oro.iefas.domain.system.vo.SysRejectReasonVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.casemgt.casedetailenquiry.service.CaseClientService;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.WorkFlowBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.common.service.CommonDividendClientService;
import hk.oro.iefas.web.dividend.dividendprocess.dividendcheque.service.impl.DividendChequeClientService;
import hk.oro.iefas.web.dividend.dividendprocess.dividendschedule.service.DividendScheduleClientService;
import hk.oro.iefas.web.dividend.maintenance.withheldreasons.service.WithheldReasonsClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class DividendScheduleEditView extends WorkFlowBean implements Serializable {

    private static final long serialVersionUID = 2279221204024609088L;

    @Inject
    private CaseClientService caseClientService;

    @Inject
    private CommonDividendClientService commonDividendClientService;

    @Inject
    private DividendScheduleClientService dividendScheduleClientService;

    @Inject
    private WithheldReasonsClientService withheldReasonsClientService;

    @Inject
    private DividendChequeClientService dividendChequeClientService;

    @Inject
    private CommonClientService commonClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private CaseNumberVO caseNumberVO;

    @Getter
    @Setter
    private String caseName;

    @Getter
    private Map<String, String> pledgeTypes = new LinkedHashMap<>();

    @Getter
    private Map<String, String> dividendScheduleTypes = new LinkedHashMap<>();

    @Getter
    @Setter
    private DividendScheduleVO dividendScheduleVO = new DividendScheduleVO();

    @Getter
    @Setter
    private List<CreditorTypeVO> creditorTypeList;

    @Getter
    @Setter
    private List<DividendScheduleCreditorVO> dividendScheduleCreditorList = new ArrayList<>();

    @Getter
    @Setter
    private List<DividendScheduleCreditorVO> showDividendScheduleCreditorList = new ArrayList<>();

    @Getter
    @Setter
    private DividendScheduleCreditorVO editDividendScheduleCreditor;

    @Getter
    @Setter
    private String[] selectValues;

    @Getter
    @Setter
    private String[] selectFirstGroupValues;

    @Getter
    @Setter
    private String[] selectSecondGroupValues;

    @Getter
    @Setter
    private String editRemarks;

    @Getter
    @Setter
    private List<CreditorVO> creditorList = new ArrayList<>();

    @Getter
    @Setter
    private List<CreditorVO> creditorSelectList = new ArrayList<>();

    @Getter
    @Setter
    private List<DividendScheduleItemVO> editDividendScheduleItemList = new ArrayList<>();

    @Getter
    @Setter
    private List<DividendScheduleItemVO> dividendScheduleItemShowList = new ArrayList<>();

    @Getter
    @Setter
    private DividendScheduleItemVO editDividendScheduleItem;

    @Getter
    @Setter
    private BigDecimal preferentialAmount = BigDecimal.ZERO;

    @Getter
    @Setter
    private BigDecimal deferredPreferentialAmount = BigDecimal.ZERO;

    @Getter
    @Setter
    private BigDecimal ordinaryAmount = BigDecimal.ZERO;

    @Getter
    @Setter
    private BigDecimal deferredOrdinaryAmount = BigDecimal.ZERO;

    @Getter
    @Setter
    private List<WithheldReasonVO> withheldReasonList;

    @Getter
    @Setter
    private BigDecimal totalDistributeAmount = BigDecimal.ZERO;

    @Getter
    @Setter
    private BigDecimal claimedAmount = BigDecimal.ZERO;

    @Getter
    @Setter
    private BigDecimal agreedAmount = BigDecimal.ZERO;

    @Getter
    @Setter
    private List<ApprovedAdjucationResultItemVO> approvedAdjucationResultItemList;

    @Getter
    @Setter
    private List<DividendChequeVO> dividendChequeList = new ArrayList<>();

    @Getter
    @Setter
    private Date dateOfNotice;

    @Getter
    @Setter
    private Integer startChequeNumber;

    @Getter
    @Setter
    private Integer endCheuqeNumber;

    @Getter
    @Setter
    private String chequeCreditorType;

    @Getter
    @Setter
    private DividendChequeVO dividendChequeVO;

    @Getter
    private List<SysRejectReasonVO> sysRejectReasons;

    @Getter
    @Setter
    private String remarkReson;

    @Getter
    private Map<String, String> statusMap = new HashMap<String, String>();

    @Getter
    private Map<String, String> natureOfClaimMap = new HashMap<String, String>();

    @PostConstruct
    private void init() {
        log.info("DividendScheduleEditView init start-");
        if (sysWorkFlowRuleVOs == null) {
            sysWorkFlowRuleVOs = new ArrayList<>();
        } else {
            sysWorkFlowRuleVOs.addAll(sysWorkFlowRuleClientService.findByPrivilegeCode(getConfirmPrivilegeCode()));
        }
        Integer divScheduleId = Faces.getRequestParameter(DividendWebConstant.DIVSCHEDULE_ID, Integer.class);
        CaseVO caseInfo = null;
        if (divScheduleId != null && divScheduleId.intValue() > 0) {
            this.dividendScheduleVO = this.dividendScheduleClientService.searchDividendSchedule(divScheduleId);
            if (this.dividendScheduleVO != null) {
                caseInfo = caseClientService.findOne(this.dividendScheduleVO.getCaseId());
                if (caseInfo != null) {
                    this.caseName = caseInfo.getCaseName();
                    this.caseNumberVO = new CaseNumberVO(caseInfo.getCaseType().getCaseTypeCode(), caseInfo.getCaseNo(),
                        caseInfo.getCaseYear());
                }
                this.dividendScheduleCreditorList = this.dividendScheduleVO.getDividendScheduleCreditorList();
                this.editDividendScheduleItemList = this.dividendScheduleVO.getDividendScheduleItems();
            }
            showDivScheCreditorList();
            fillShowDivScheItemList();
        } else {
            CreateDividendScheduleVO createDividendScheduleVO
                = Faces.getFlashAttribute(DividendWebConstant.CREATE_DIVIDEND_SCHEDULE_VO);
            if (createDividendScheduleVO != null) {
                this.caseNumberVO = createDividendScheduleVO.getCaseNumber();
                if (caseNumberVO != null) {
                    caseInfo = caseClientService.findByCaseNumber(this.caseNumberVO);
                    if (caseInfo != null) {
                        this.caseName = caseInfo.getCaseName();
                        this.dividendScheduleVO.setCaseId(caseInfo.getCaseId());
                    }
                }
                dividendScheduleVO.setPaymentEffectiveDate(createDividendScheduleVO.getPaymentEffectiveDate());
                dividendScheduleVO.setScheduleType(createDividendScheduleVO.getScheduleType().getScheduleTypeName());
                dividendScheduleVO.setStatus(initStatus());
            }
        }
        refreshActionButton(dividendScheduleVO.getStatus());

        List<ApplicationCodeTableVO> pledgeTypeList
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.DPT.name());
        if (CommonUtils.isNotEmpty(pledgeTypeList)) {
            this.pledgeTypes.clear();
            pledgeTypeList
                .forEach(type -> this.pledgeTypes.put(type.getCodeDesc(), String.valueOf(type.getCodeValue())));
        }

        List<ApplicationCodeTableVO> dividendScheduleTypeList
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.DST.name());
        if (CommonUtils.isNotEmpty(dividendScheduleTypeList)) {
            this.dividendScheduleTypes.clear();
            dividendScheduleTypeList.forEach(
                type -> this.dividendScheduleTypes.put(type.getCodeDesc(), String.valueOf(type.getCodeValue())));
        }
        // init DivSchedultCred param
        this.creditorTypeList = this.commonDividendClientService.searchCreditorType();
        // init DivSchedultItem param
        if (caseInfo != null) {
            this.creditorList = this.dividendScheduleClientService.searchCreditorByCaseId(caseInfo.getCaseId());
        }
        this.withheldReasonList = this.withheldReasonsClientService.searchWithheldReasonList();
        sysRejectReasons = commonClientService.searchRejectReasonList();
        initParamMap();
        calculateTotalDistributeAmount();
        log.info("DividendScheduleEditView init end-");
    }

    public void initParamMap() {
        log.info("initParamMap init start -");
        List<ApplicationCodeTableVO> statusVOs
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.DSS.name());
        if (CommonUtils.isNotEmpty(statusVOs)) {
            statusVOs.forEach(item -> {
                statusMap.put(item.getCodeValue(), item.getCodeDesc());
            });
        }

        List<ApplicationCodeTableVO> natureOfClaimList
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.NOC.name());
        if (CommonUtils.isNotEmpty(statusVOs)) {
            natureOfClaimList.forEach(item -> {
                natureOfClaimMap.put(item.getCodeValue(), item.getCodeDesc());
            });
        }
        log.info("initParamMap init end -");
    }

    // --------------------------Creditor Type start-----------------------------
    public void showDivScheCreditorList() {
        log.info("DividendScheduleEditView start-");
        this.showDividendScheduleCreditorList.clear();
        if (dividendScheduleCreditorList != null && !dividendScheduleCreditorList.isEmpty()) {
            this.showDividendScheduleCreditorList = dividendScheduleCreditorList.stream()
                .filter(t -> t.getStatus().equals(CoreConstant.STATUS_ACTIVE)).collect(Collectors.toList());
        }
        log.info("DividendScheduleEditView end");
    }

    public void toEditRemarks() {
        log.info("editSelectValues - start");
        this.editRemarks = null;
        StringBuilder remarksBuilder = new StringBuilder();
        remarksBuilder.append(DividendWebConstant.DIVSCHEDULE_REMARKS_HEAD);
        if (selectValues != null && selectValues.length > 0) {
            remarksBuilder.append(this.selectValues[0]);
        }
        if (selectFirstGroupValues != null && selectFirstGroupValues.length > 0) {
            for (String str : selectFirstGroupValues) {
                if (str != null && str != "") {
                    if (remarksBuilder.toString().trim().equals(DividendWebConstant.DIVSCHEDULE_REMARKS_HEAD.trim())) {
                        remarksBuilder.append(str);
                    } else {
                        remarksBuilder.append(DividendWebConstant.DIVSCHEDULE_REMARKS_HEAD + str);
                    }
                }
            }
        }
        if (selectSecondGroupValues != null && selectSecondGroupValues.length > 0) {
            for (String str : selectSecondGroupValues) {
                if (str != null && str != "") {
                    if (remarksBuilder.toString().trim().equals(DividendWebConstant.DIVSCHEDULE_REMARKS_HEAD.trim())) {
                        remarksBuilder.append(str);
                    } else {
                        remarksBuilder.append(DividendWebConstant.DIVSCHEDULE_REMARKS_HEAD + str);
                    }
                }
            }
        }
        String scheduleType = this.dividendScheduleVO.getScheduleType();
        String prefix = null;
        if (scheduleType != null && (scheduleType.equals(DividendWebConstant.DIVSCHEDULE_SCHEDULETYPE_DS)
            || scheduleType.equals(DividendWebConstant.DIVSCHEDULE_SCHEDULETYPE_IS))) {
            Integer credTypeId = this.editDividendScheduleCreditor.getAdjCredType().getCreditorTypeId();
            if (credTypeId != null && credTypeId.intValue() > 0) {
                BigDecimal percent = dividendScheduleClientService.findCredTypePercentageByCredTypeId(credTypeId);
                prefix = DividendWebConstant.DIVSCHEDULE_REMARKS_LINK_OF + percent
                    + DividendWebConstant.DIVSCHEDULE_REMARKS_LINK_PERCENT;
                remarksBuilder.append(prefix);
            }
        }
        this.editRemarks = remarksBuilder.toString();
        log.info("editSelectValues - end");
    }

    public void toAddCreditorType() {
        log.info("saveEditCreditorType - start");
        this.editDividendScheduleCreditor = new DividendScheduleCreditorVO();
        this.editDividendScheduleCreditor.setAdjCredType(new CreditorTypeVO());
        selectValues = null;
        selectFirstGroupValues = null;
        selectSecondGroupValues = null;
        this.editRemarks = null;
        showComponent("dividendEditDialog");
        log.info("saveEditCreditorType - end");
    }

    public void toEditCreditorType() {
        log.info("toEditCreditorType - start");
        if (this.editDividendScheduleCreditor != null) {
            this.selectValues = this.editDividendScheduleCreditor.getSelectValues();
            this.selectFirstGroupValues = this.editDividendScheduleCreditor.getSelectFirstGroupValues();
            this.selectSecondGroupValues = this.editDividendScheduleCreditor.getSelectSecondGroupValues();
            showComponent("dividendEditDialog");
        }
        log.info("toEditCreditorType - end");
    }

    public void saveEditCreditorType() {
        log.info("saveEditCreditorType - start");
        if (this.editDividendScheduleCreditor != null) {
            this.showDividendScheduleCreditorList.remove(this.editDividendScheduleCreditor);
            if (!checkExistCreditorType(this.editDividendScheduleCreditor.getAdjCredType().getCreditorTypeId())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_CREDITOR_TYPE_ALREADY_EXIST_ERROR), ""));
            } else {
                toEditRemarks();
                this.editDividendScheduleCreditor.setSelectValues(this.selectValues);
                this.editDividendScheduleCreditor.setSelectFirstGroupValues(this.selectFirstGroupValues);
                this.editDividendScheduleCreditor.setSelectSecondGroupValues(this.selectSecondGroupValues);
                Integer creditorTypeId = this.editDividendScheduleCreditor.getAdjCredType().getCreditorTypeId();
                if (creditorTypeId != null && creditorTypeId.intValue() > 0) {
                    for (CreditorTypeVO type : creditorTypeList) {
                        if (type.getCreditorTypeId().equals(creditorTypeId)) {
                            this.editDividendScheduleCreditor
                                .setAdjCredType(DataUtils.copyProperties(type, CreditorTypeVO.class));
                            break;
                        }
                    }
                }
                if (this.dividendScheduleCreditorList.contains(this.editDividendScheduleCreditor)) {
                    Integer index = this.dividendScheduleCreditorList.indexOf(this.editDividendScheduleCreditor);
                    this.dividendScheduleCreditorList.set(index, this.editDividendScheduleCreditor);
                } else {
                    // create
                    this.editDividendScheduleCreditor.setStatus(CoreConstant.STATUS_ACTIVE);
                    this.dividendScheduleCreditorList.add(this.editDividendScheduleCreditor);
                }
                this.editDividendScheduleCreditor.setRemark(this.editRemarks);
                showDivScheCreditorList();
                hideComponent("dividendEditDialog");
            }

        }
        log.info("saveEditCreditorType - end");
    }

    public boolean checkExistCreditorType(Integer creditorTypeId) {
        log.info("checkExistCreditorType - strart");
        boolean flag = true;
        if (creditorTypeId == null || creditorTypeId.intValue() <= 0) {
            flag = false;
        } else {
            for (DividendScheduleCreditorVO creditor : this.showDividendScheduleCreditorList) {
                if (creditor.getAdjCredType().getCreditorTypeId().equals(creditorTypeId)) {
                    flag = false;
                    break;
                }
            }
        }
        log.info("checkExistCreditorType - end and return flag = " + flag);
        return flag;
    }

    public void deleteCreditorType() {
        log.info("deleteCreditorType - start");
        if (this.editDividendScheduleCreditor != null) {
            Integer scheduleCredId = this.editDividendScheduleCreditor.getScheduleCredId();
            if (scheduleCredId != null && scheduleCredId.intValue() > 0) {
                this.editDividendScheduleCreditor.setStatus(CoreConstant.STATUS_INACTIVE);
            } else {
                this.dividendScheduleCreditorList.remove(this.editDividendScheduleCreditor);
            }
            this.showDividendScheduleCreditorList.remove(this.editDividendScheduleCreditor);
        }
        log.info("deleteCreditorType - end");
    }
    // --------------------------Creditor Type end------------------------------

    // --------------------------create DivScheduleItem Start-------------------
    public void filterCreditorList() {
        log.info("filterCreditorList - start");
        this.creditorSelectList.clear();
        if (this.creditorList != null) {
            List<Integer> creditorTypeList = null;
            if (this.dividendScheduleCreditorList != null && !this.dividendScheduleCreditorList.isEmpty()) {
                creditorTypeList = this.dividendScheduleCreditorList.stream()
                    .map(t -> t.getAdjCredType().getCreditorTypeId()).collect(Collectors.toList());
            }
            if (creditorTypeList != null && !creditorTypeList.isEmpty()) {
                for (CreditorVO creditor : this.creditorList) {
                    CreditorTypeVO creditorTypeVO = creditor.getCreditorType();
                    if (creditorTypeVO != null) {
                        Integer creditorTypeId = creditorTypeVO.getCreditorTypeId();
                        if (creditorTypeList.indexOf(creditorTypeId) >= 0) {
                            List<ApprovedAdjucationResultItemVO> appAdjResultItemList
                                = dividendScheduleClientService.findByCreditor(creditor.getCreditorId());
                            if (appAdjResultItemList != null && !appAdjResultItemList.isEmpty()) {
                                this.creditorSelectList.add(creditor);
                            }
                        }
                    }
                }
            }
        }
        log.info("filterCreditorList - end");
    }

    public void toAddDivScheItem() {
        log.info("toAddDivScheItem - start");
        filterCreditorList();
        this.editDividendScheduleItem = new DividendScheduleItemVO();
        this.editDividendScheduleItem.setCreditor(new CreditorVO());
        this.editDividendScheduleItem.setWithheldReason(new WithheldReasonVO());
        this.preferentialAmount = BigDecimal.ZERO;
        this.deferredPreferentialAmount = BigDecimal.ZERO;
        this.ordinaryAmount = BigDecimal.ZERO;
        this.deferredOrdinaryAmount = BigDecimal.ZERO;
        showComponent("createDivScheItemDialog");
        log.info("toAddDivScheItem - end");
    }

    public void toEditDivScheItem() {
        log.info("toEditDivScheItem - start");
        filterCreditorList();
        if (this.editDividendScheduleItem != null) {
            this.agreedAmount = this.editDividendScheduleItem.getDistributionAmount();
            List<DividendScheduleDistVO> dividendScheduleDistList
                = this.editDividendScheduleItem.getDividendScheduleDistList();
            if (dividendScheduleDistList != null && !dividendScheduleDistList.isEmpty()) {
                for (DividendScheduleDistVO dist : dividendScheduleDistList) {
                    ApprovedAdjucationResultItemVO appAdjResultItem = dist.getAppAdjResultItem();
                    if (appAdjResultItem != null) {
                        switch (appAdjResultItem.getAdjudicationType().getAdjudicationTypeName()) {
                            case DividendConstant.ADJTYPE_PRE:
                                this.preferentialAmount = dist.getAmountPaid();
                                break;
                            case DividendConstant.ADJTYPE_DEF_PRE:
                                this.deferredPreferentialAmount = dist.getAmountPaid();
                                break;
                            case DividendConstant.ADJTYPE_ORD:
                                this.ordinaryAmount = dist.getAmountPaid();
                                break;
                            case DividendConstant.ADJTYPE_DEF_ORD:
                                this.deferredOrdinaryAmount = dist.getAmountPaid();
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        showComponent("createDivScheItemDialog");
        log.info("toEditDivScheItem - end");
    }

    public void fillShowDivScheItemList() {
        log.info("fillShowDivScheItemList - start");
        if (this.editDividendScheduleItemList != null && !this.editDividendScheduleItemList.isEmpty()) {
            this.dividendScheduleItemShowList = this.editDividendScheduleItemList.stream()
                .filter(item -> item.getStatus().equals(CoreConstant.STATUS_ACTIVE)).collect(Collectors.toList());
        } else {
            this.dividendScheduleItemShowList.clear();
        }
        log.info("fillShowDivScheItemList - end dividendScheduleItemShowList :" + this.dividendScheduleItemShowList);
    }

    public void saveDivScheItem() {
        log.info("saveDivScheItem - start");
        String schedultType = this.dividendScheduleVO.getScheduleType();
        if (this.editDividendScheduleItem != null) {
            this.dividendScheduleItemShowList.remove(this.editDividendScheduleItem);
            Integer creditorId = this.editDividendScheduleItem.getCreditor().getCreditorId();
            if (creditorId == null || checkDivScheItemCredExist(creditorId)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_CREDITOR_ALREADY_EXIST_ERROR), ""));
                return;
            }
            calculateClaimAmount(creditorId);
            Integer withheldResonId = this.editDividendScheduleItem.getWithheldReason().getWithheldReasonId();
            if (withheldResonId != null && withheldResonId.intValue() > 0) {
                for (WithheldReasonVO withheldReason : this.withheldReasonList) {
                    if (withheldReason.getWithheldReasonId().equals(withheldResonId)) {
                        this.editDividendScheduleItem
                            .setWithheldReason(DataUtils.copyProperties(withheldReason, WithheldReasonVO.class));
                        break;
                    }
                }
            }
            for (CreditorVO creditor : this.creditorList) {
                if (creditor.getCreditorId().equals(creditorId)) {
                    this.editDividendScheduleItem.setCreditor(creditor);
                    this.editDividendScheduleItem.setNatureOfClaim(creditor.getNatureOfClaim());
                    break;
                }
            }
            this.editDividendScheduleItem.setDistributionAmount(this.agreedAmount);
            if (this.claimedAmount.compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal percentage = this.editDividendScheduleItem.getDistributionAmount().divide(this.claimedAmount,
                    4, RoundingMode.HALF_EVEN);
                this.editDividendScheduleItem.setDistributionPercentage(percentage);
            }
            Map<String, BigDecimal> parameters = new HashMap<>();
            if (schedultType.equals("DS") || schedultType.equals("ADS")) {
                parameters.put(DividendConstant.ADJTYPE_PRE, this.preferentialAmount);
                parameters.put(DividendConstant.ADJTYPE_DEF_PRE, this.deferredPreferentialAmount);
                parameters.put(DividendConstant.ADJTYPE_ORD, this.ordinaryAmount);
                parameters.put(DividendConstant.ADJTYPE_DEF_ORD, this.deferredOrdinaryAmount);
            } else {
                parameters.put(DividendConstant.ADJTYPE_INT, this.editDividendScheduleItem.getDistributionAmount());
            }
            if (!checkMaxAmount(parameters)) {
                return;
            }
            if (this.editDividendScheduleItemList.contains(this.editDividendScheduleItem)) {
                Integer index = this.editDividendScheduleItemList.indexOf(this.editDividendScheduleItem);
                List<DividendScheduleDistVO> dividendScheduleDistList
                    = this.editDividendScheduleItem.getDividendScheduleDistList();

                if (dividendScheduleDistList != null && !dividendScheduleDistList.isEmpty()) {
                    for (DividendScheduleDistVO distVO : dividendScheduleDistList) {
                        String type = distVO.getAppAdjResultItem().getAdjudicationType().getAdjudicationTypeName();
                        distVO.setAmountPaid(parameters.get(type));
                    }
                    this.editDividendScheduleItem.setDividendScheduleDistList(dividendScheduleDistList);
                }
                this.editDividendScheduleItemList.set(index, this.editDividendScheduleItem);
            } else {
                this.editDividendScheduleItem.setTotalClaimAmount(this.claimedAmount);
                List<DividendScheduleDistVO> dividendScheduleDistList = createDivScheDistList(parameters);;
                this.editDividendScheduleItem.setDividendScheduleDistList(dividendScheduleDistList);
                this.editDividendScheduleItem.setStatus(CoreConstant.STATUS_ACTIVE);
                this.editDividendScheduleItemList.add(this.editDividendScheduleItem);
                this.dividendScheduleItemShowList.add(this.editDividendScheduleItem);
            }
            fillShowDivScheItemList();
            calculateTotalDistributeAmount();
            hideComponent("createDivScheItemDialog");
        }
        log.info("saveDivScheItem - end");
    }

    public boolean checkDivScheItemCredExist(Integer creditorId) {
        log.info("saveDivScheItem - start");
        boolean flag = false;
        if (this.editDividendScheduleItemList != null && !this.editDividendScheduleItemList.isEmpty()) {
            for (DividendScheduleItemVO divScheItem : this.dividendScheduleItemShowList) {
                CreditorVO creditorVO = divScheItem.getCreditor();
                if (creditorVO != null) {
                    if (creditorId.equals(creditorVO.getCreditorId())) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        log.info("saveDivScheItem - end and flag = " + flag);
        return flag;
    }

    public boolean checkMaxAmount(Map<String, BigDecimal> parameters) {
        log.info("saveDivScheItem - start and parameters =" + parameters);
        boolean flag = true;
        if (this.approvedAdjucationResultItemList != null && !this.approvedAdjucationResultItemList.isEmpty()) {
            for (ApprovedAdjucationResultItemVO appAdjResultItem : this.approvedAdjucationResultItemList) {
                if (appAdjResultItem.getAdmittedAmount() != null) {
                    if (parameters != null && !parameters.isEmpty()) {
                        BigDecimal checkValue
                            = parameters.get(appAdjResultItem.getAdjudicationType().getAdjudicationTypeName());
                        if (checkValue != null) {
                            BigDecimal remainderAmount
                                = appAdjResultItem.getAdmittedAmount().subtract(appAdjResultItem.getAmountPaid());
                            if (remainderAmount.compareTo(BigDecimal.ZERO) != 0) {
                                if (checkValue.compareTo(remainderAmount) > 0) {
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                                        FacesMessage.SEVERITY_ERROR,
                                        appResourceUtils.getMessage(MsgCodeConstant.MSG_AMOUNT_MORE_THAN_TOTAL_ERROR),
                                        ""));
                                    flag = false;
                                    break;
                                }
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        appResourceUtils.getMessage(MsgCodeConstant.MSG_AMOUNT_OF_TOTAL_IS_ZERO_ERROR),
                                        ""));
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        log.info("saveDivScheItem - end");
        return flag;
    }

    // calculate one divScheitem total
    public BigDecimal calculateTotalAmount() {
        log.info("calculateTotalAmount - start ");
        BigDecimal result = BigDecimal.ZERO;
        result = result.add(this.preferentialAmount).add(this.deferredPreferentialAmount).add(this.ordinaryAmount)
            .add(this.deferredOrdinaryAmount);
        this.agreedAmount = result;
        log.info("calculateTotalAmount - end and result = " + result);
        return result;
    }

    // calculate All divScheitem total
    public void calculateTotalDistributeAmount() {
        log.info("calculateTotalDistributeAmount - start ");
        if (this.editDividendScheduleItemList != null && !this.editDividendScheduleItemList.isEmpty()) {
            for (DividendScheduleItemVO divScheItemVO : this.editDividendScheduleItemList) {
                if (divScheItemVO.getDistributionAmount() != null) {
                    totalDistributeAmount = totalDistributeAmount.add(divScheItemVO.getDistributionAmount());
                }
            }
        }
        log.info("calculateTotalDistributeAmount - end ");
    }

    public void calculateClaimAmount(Integer creditorId) {
        log.info("calculateClaimAmount - start");
        this.claimedAmount = BigDecimal.ZERO;
        String scheType = this.dividendScheduleVO.getScheduleType();
        this.approvedAdjucationResultItemList = dividendScheduleClientService.findByCreditor(creditorId);
        if (approvedAdjucationResultItemList == null) {
            this.approvedAdjucationResultItemList = new ArrayList<>();
        }
        if (scheType.equals("ADS") || scheType.equals("DS")) {
            for (ApprovedAdjucationResultItemVO appAdjResultItem : this.approvedAdjucationResultItemList) {
                if (appAdjResultItem.getAdmittedAmount() != null) {
                    this.claimedAmount = this.claimedAmount.add(appAdjResultItem.getAdmittedAmount());
                }
            }
        } else {
            this.claimedAmount = dividendScheduleClientService.searchTotalInterestAmount(creditorId);
        }
        log.info("calculateClaimAmount - end");
    }

    public List<DividendScheduleDistVO> createDivScheDistList(Map<String, BigDecimal> parameters) {
        log.info("createDivScheDist - start and parameters =" + parameters);
        List<DividendScheduleDistVO> result = null;
        if (parameters != null && !parameters.isEmpty()) {
            result = new ArrayList<>();
            for (String str : parameters.keySet()) {
                DividendScheduleDistVO dividendScheduleDist = new DividendScheduleDistVO();
                dividendScheduleDist.setAmountPaid(parameters.get(str));
                BigDecimal percentPaid = null;
                for (ApprovedAdjucationResultItemVO appAdjResultItemVO : this.approvedAdjucationResultItemList) {
                    if (appAdjResultItemVO.getAdjudicationType().getAdjudicationTypeName().equals(str)) {
                        if (appAdjResultItemVO.getAdmittedAmount().compareTo(BigDecimal.ZERO) != 0) {
                            percentPaid = parameters.get(str).divide(appAdjResultItemVO.getAdmittedAmount(), 4,
                                RoundingMode.HALF_EVEN);
                        } else {
                            percentPaid = BigDecimal.ZERO;
                        }
                        break;
                    }
                }
                dividendScheduleDist.setPercentPaid(percentPaid);
                ApprovedAdjucationResultItemVO appAdjResultItem = new ApprovedAdjucationResultItemVO();
                AdjudicationTypeVO adjudicationType = new AdjudicationTypeVO();
                adjudicationType.setAdjudicationTypeName(str);
                appAdjResultItem.setAdjudicationType(adjudicationType);
                dividendScheduleDist.setStatus(CoreConstant.STATUS_ACTIVE);
                dividendScheduleDist.setAppAdjResultItem(appAdjResultItem);
                result.add(dividendScheduleDist);
            }
        }
        log.info("createDivScheDist - end and return result=" + result);
        return result;
    }

    public void deleteDivSchedultItem() {
        log.info("deleteDivSchedultItem - start ");
        if (this.editDividendScheduleItem != null) {
            Integer index = this.editDividendScheduleItemList.indexOf(this.editDividendScheduleItem);
            if (index >= 0) {
                Integer dividendScheduleItemId = this.editDividendScheduleItem.getDividendScheduleItemId();
                if (dividendScheduleItemId != null && dividendScheduleItemId.intValue() > 0) {
                    this.editDividendScheduleItem.setStatus(CoreConstant.STATUS_INACTIVE);
                } else {
                    this.editDividendScheduleItemList.remove(this.editDividendScheduleItem);
                }
                fillShowDivScheItemList();
            }
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_DELETE_SUCCESS));
        }
        log.info("deleteDivSchedultItem - end ");
    }

    // --------------------------create DivScheduleItem end-------------------

    public void toEditPrintCheque() {
        log.info("toEditPrintCheque - start");
        if (this.editDividendScheduleItemList != null && !this.editDividendScheduleItemList.isEmpty()) {
            if (this.dividendScheduleCreditorList != null) {
                StringBuffer buffer = new StringBuffer();
                for (DividendScheduleCreditorVO divSchCred : this.dividendScheduleCreditorList) {
                    buffer.append(divSchCred.getAdjCredType().getCreditorTypeName() + ",");
                }
                this.chequeCreditorType = buffer.substring(0, buffer.length() - 1);
            }
            List<Integer> divScheduleItemIdList = this.editDividendScheduleItemList.stream()
                .map(t -> t.getDividendScheduleItemId()).collect(Collectors.toList());
            this.dividendChequeList = dividendChequeClientService.searchDivChequeListByDivScheId(divScheduleItemIdList);
            if (this.dividendChequeList != null && !this.dividendChequeList.isEmpty()) {
                List<ChequeVO> chequeList = this.dividendChequeList.stream().map(t -> t.getCheque())
                    .sorted(Comparator.comparing(ChequeVO::getChequeNo)).collect(Collectors.toList());
                if (chequeList != null && !chequeList.isEmpty()) {
                    this.dateOfNotice = chequeList.get(0).getChequeDate();
                    this.startChequeNumber = chequeList.get(0).getChequeNo();
                    this.endCheuqeNumber = chequeList.get(chequeList.size() - 1).getChequeNo();
                }
            }
            showComponent("printChequeDialog");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_PRINT_CHEQUE_ERROR), ""));
        }
        log.info("toEditPrintCheque - end ");
    }

    public void toPrintCheque() {
        log.info("toPrintCheque - start");
        if (this.dateOfNotice == null || this.startChequeNumber == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_PRINT_CHEQUE_PARAMETER_ERROR), ""));
            return;
        }
        boolean flag = false;
        Integer increase = this.startChequeNumber;
        if (this.dividendChequeList != null && !this.dividendChequeList.isEmpty()) {
            flag = this.dividendChequeList.get(0).getDividendChequeId() > 0;
        }
        if (flag) {
            for (DividendChequeVO dividendCheque : this.dividendChequeList) {
                ChequeVO chequeVO = dividendCheque.getCheque();
                if (chequeVO != null && dividendCheque.getStatus().equals(CoreConstant.CHEQUE_STATUS_REISSUED)) {
                    chequeVO.setChequeDate(this.dateOfNotice);
                    chequeVO.setChequeNo(increase);
                    this.endCheuqeNumber = increase;
                    increase++;
                    dividendCheque.setCheque(chequeVO);
                    dividendCheque.setStatus(CoreConstant.CHEQUE_STATUS_PRINTED);
                }
            }
        } else {
            // create
            this.dividendChequeList.clear();
            for (DividendScheduleItemVO divScheduleItem : this.editDividendScheduleItemList) {
                DividendChequeVO divChequeVO = new DividendChequeVO();
                ChequeVO chequeVO = new ChequeVO();
                if (this.dividendScheduleCreditorList != null) {
                    for (DividendScheduleCreditorVO divSchCred : this.dividendScheduleCreditorList) {
                        Integer creditorTypeId = divSchCred.getAdjCredType().getCreditorTypeId();
                        if (divScheduleItem.getCreditor().getCreditorType().getCreditorTypeId()
                            .equals(creditorTypeId)) {
                            chequeVO.setRemark(divSchCred.getRemark());
                            break;
                        }
                    }
                }
                chequeVO.setChequeDate(this.dividendScheduleVO.getPaymentEffectiveDate());
                BigDecimal chequeAmount
                    = divScheduleItem.getDistributionAmount().subtract(divScheduleItem.getWithheldAmount());
                chequeVO.setChequeAmount(chequeAmount);
                chequeVO.setChequeDate(this.dateOfNotice);
                chequeVO.setPayee(divScheduleItem.getCreditor().getCreditorNameEng());
                Integer chequeNumber = increase;
                chequeVO.setChequeNo(chequeNumber);
                chequeVO.setStatus(CoreConstant.CHEQUE_STATUS_PRINTED);
                divChequeVO.setDividendScheduleItem(divScheduleItem);
                divChequeVO.setCheque(chequeVO);
                divChequeVO.setStatus(CoreConstant.CHEQUE_STATUS_PRINTED);
                this.endCheuqeNumber = increase;
                increase++;
                this.dividendChequeList.add(divChequeVO);
            }
        }

        log.info("toPrintCheque - end");
    }

    public void saveCheque() {
        log.info("saveCheque - start");
        if (!checkChequeNumberNotEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_PRINT_CHEQUE_PARAMETER_ERROR), ""));
            return;
        }
        if (this.dividendChequeList != null && !this.dividendChequeList.isEmpty()) {
            boolean result = dividendChequeClientService.saveDividendChequeList(this.dividendChequeList);
            if (result) {
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS), "");
                List<Integer> divScheduleItemIdList = this.editDividendScheduleItemList.stream()
                    .map(t -> t.getDividendScheduleItemId()).collect(Collectors.toList());
                this.dividendChequeList
                    = dividendChequeClientService.searchDivChequeListByDivScheId(divScheduleItemIdList);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL), ""));
            }
        }
        log.info("saveCheque - end");
    }

    public void checkExistChequeNumber() {
        log.info("checkExistChequeNumber - start");
        Integer existTime = 0;
        boolean flag = false;
        if (this.dividendChequeVO != null) {
            for (DividendChequeVO divChequeVO : this.dividendChequeList) {
                if (divChequeVO.getCheque().getChequeNo().equals(this.dividendChequeVO.getCheque().getChequeNo())) {
                    existTime++;
                }
            }
            if (existTime > 1) {
                flag = true;
            }
        }
        if (flag) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_CHEQUE_NUMBER_EXIST_ERROR), ""));
            return;
        }
        log.info("checkExistChequeNumber - end");
    }

    public boolean checkChequeNumberNotEmpty() {
        log.info("checkChequeNumberNotEmpty - start");
        boolean flag = false;
        if (this.dividendChequeList != null && !this.dividendChequeList.isEmpty()) {
            for (DividendChequeVO dividendChequeVO : this.dividendChequeList) {
                Integer chequeNum = dividendChequeVO.getCheque().getChequeNo();
                if (chequeNum != null) {
                    flag = true;
                    break;
                }
            }
        }
        log.info("checkChequeNumberNotEmpty - end");
        return flag;
    }

    private void initData() {
        log.info("initData - start");
        if (this.dividendScheduleVO != null) {
            refreshActionButton(this.dividendScheduleVO.getStatus());
            this.dividendScheduleCreditorList = this.dividendScheduleVO.getDividendScheduleCreditorList();
            showDivScheCreditorList();
            this.editDividendScheduleItemList = this.dividendScheduleVO.getDividendScheduleItems();
            fillShowDivScheItemList();
        }
        log.info("initData - end");
    }

    @Override
    protected String getStatusGroupCode() {
        return ApplicationCodeTableEnum.DSS.name();
    }

    @Override
    protected String getSubmitPrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_DSS;
    }

    @Override
    protected String getApprovePrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_DSA;
    }

    protected String getConfirmPrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_DSC;
    }

    public void saveDividendSchedule() {
        log.info("save - start");
        WorkFlowAction workFlowAction = WorkFlowAction.Save;
        executeAction(workFlowAction);
        log.info("save - start");
    }

    public void confirmDividendSchedule() {
        WorkFlowAction workFlowAction = WorkFlowAction.Confirm;
        executeAction(workFlowAction);
    }

    public void approveDividendSchedule() {
        WorkFlowAction workFlowAction = WorkFlowAction.Approve;
        executeAction(workFlowAction);
    }

    public void rejectDividendSchedule() {
        WorkFlowAction workFlowAction = WorkFlowAction.Reject;
        executeAction(workFlowAction);
        hideComponent("rejectDialog");
        this.remarkReson = null;
    }

    public String deleteDividendSchedule() {
        if (this.dividendScheduleVO.getDividendScheduleId() != null
            && this.dividendScheduleVO.getDividendScheduleId().intValue() > 0) {
            WorkFlowAction workFlowAction = WorkFlowAction.Delete;
            executeAction(workFlowAction);
            return SEARCH_PAGE;
        }
        return null;
    }

    private void prepareData(String workFlowActionCode) {
        SysWorkFlowRuleVO sysWorkFlowRuleVO
            = getAfterStatusByAction(this.dividendScheduleVO.getStatus(), workFlowActionCode);
        this.dividendScheduleVO.setStatus(sysWorkFlowRuleVO.getAfterStatus().getCodeValue());
    }

    private void executeAction(WorkFlowAction workFlowAction) {
        if (this.dividendScheduleVO != null) {
            if (!workFlowAction.equals(WorkFlowAction.Save) || (this.dividendScheduleVO.getDividendScheduleId() == null
                || this.dividendScheduleVO.getDividendScheduleId().intValue() <= 0)) {
                SysApprovalWfVO sysApprovalWf
                    = getSysApprovalWf(workFlowAction, getWorkFlowPrivilegeCode(workFlowAction));
                sysApprovalWf.setRemark(this.remarkReson);
                this.dividendScheduleVO.setSysApprovalWf(sysApprovalWf);
            }
            prepareData(workFlowAction.getCode());
            if (this.editDividendScheduleItemList != null) {
                dividendScheduleVO.setDividendScheduleItems(this.editDividendScheduleItemList);
            }
            if (this.dividendScheduleCreditorList != null) {
                dividendScheduleVO.setDividendScheduleCreditorList(this.dividendScheduleCreditorList);
            }
            Integer result = this.dividendScheduleClientService.saveDividendSchedule(this.dividendScheduleVO);
            if (result != null && result.intValue() > 0) {
                this.dividendScheduleVO = dividendScheduleClientService.searchDividendSchedule(result);
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
    }
}
