package hk.oro.iefas.web.dividend.creditorreg.interesttrial.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.ApplicationCodeTableEnum;
import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.PrivilegeConstant;
import hk.oro.iefas.core.constant.WorkFlowAction;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.dividend.vo.AdjIntTrialAccItemVO;
import hk.oro.iefas.domain.dividend.vo.AdjucationResultIntItemVO;
import hk.oro.iefas.domain.dividend.vo.AdjucationResultVO;
import hk.oro.iefas.domain.dividend.vo.DividendParameterVO;
import hk.oro.iefas.domain.dividend.vo.DividendScheduleItemVO;
import hk.oro.iefas.domain.dividend.vo.InterestTrialAdjudicationVO;
import hk.oro.iefas.domain.dividend.vo.SysApprovalWfVO;
import hk.oro.iefas.domain.system.vo.SysRejectReasonVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.WorkFlowBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.creditorreg.interesttrial.service.InterestTrialClientService;
import hk.oro.iefas.web.dividend.maintenance.otherparameter.service.DividendParameterClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
@ViewScoped
public class InterestTrialEditView extends WorkFlowBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private InterestTrialClientService interestTrialClientService;

    @Autowired
    private DividendParameterClientService dividendParameterClientService;

    @Autowired
    private CommonClientService commonClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private InterestTrialAdjudicationVO interestTrialAdjudicationVO;

    @Getter
    @Setter
    private DividendParameterVO dividendParameter;

    @Getter
    @Setter
    private AdjIntTrialAccItemVO editAdjIntTrialAccItemVO;

    @Getter
    @Setter
    private List<AdjIntTrialAccItemVO> adjIntTrialAccItemList = new ArrayList<>();

    @Getter
    @Setter
    private List<AdjucationResultIntItemVO> adjucationResultIntItemList;

    @Getter
    @Setter
    private boolean generateResult = false;

    @Getter
    @Setter
    private BigDecimal editContractualRate;

    @Getter
    @Setter
    private boolean toEditAdmittedAmount = true;

    @Getter
    @Setter
    private String remarkReson;

    @Getter
    @Setter
    private AdjucationResultIntItemVO editAdjucationResultIntItem;

    @Getter
    @Setter
    private BigDecimal editGenerateRate;

    @Getter
    @Setter
    private List<AdjucationResultIntItemVO> removeAdjResultIntItemList = new ArrayList<>();

    @Getter
    private List<SysRejectReasonVO> sysRejectReasons;

    @Getter
    private List<ApplicationCodeTableVO> addressTypes;

    @Getter
    private List<ApplicationCodeTableVO> natureOfClaims;

    @PostConstruct
    public void init() {
        log.info("init() - start");
        Integer interestTrialAdjudicationId
            = Faces.getRequestParameter(DividendWebConstant.INTEREST_TRIAL_ADJUDICATION_ID, Integer.class);
        if (interestTrialAdjudicationId != null && interestTrialAdjudicationId.intValue() > 0) {
            this.interestTrialAdjudicationVO
                = interestTrialClientService.searchInterestTrialById(interestTrialAdjudicationId);
        } else {
            this.interestTrialAdjudicationVO = Faces.getFlashAttribute(DividendWebConstant.INTEREST_TRIAL_ADJUDICATION);
            if (this.interestTrialAdjudicationVO != null) {
                this.interestTrialAdjudicationVO.setStatus(initStatus());
            }
        }
        List<DividendParameterVO> dividendParameterList = dividendParameterClientService.searchDividendParameter();
        if (dividendParameterList != null && !dividendParameterList.isEmpty()) {
            this.dividendParameter = dividendParameterList.get(0);
        }
        if (this.interestTrialAdjudicationVO != null) {
            this.adjucationResultIntItemList = this.interestTrialAdjudicationVO.getAdjucationResultIntItemList();
            this.adjIntTrialAccItemList = this.interestTrialAdjudicationVO.getAdjIntTrialAccItemList();
        }
        refreshActionButton(this.interestTrialAdjudicationVO.getStatus());
        getStatusDesc(this.interestTrialAdjudicationVO.getStatus());
        sysRejectReasons = commonClientService.searchRejectReasonList();
        addressTypes = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.ADT.name());
        natureOfClaims = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.NOC.name());
        log.info("init() - start");
    }

    // --------------------edit Interest tab start--------------------
    public void toEditInterestTab() {
        log.info("toEditInterestTab - start");
        String accountNumber = Faces.getRequestParameter("accountNumber");
        if (this.adjIntTrialAccItemList != null) {
            for (AdjIntTrialAccItemVO adjIntTrialAccItem : adjIntTrialAccItemList) {
                if (adjIntTrialAccItem.getAccountNumber().equals(accountNumber)) {
                    this.editAdjIntTrialAccItemVO
                        = DataUtils.copyProperties(adjIntTrialAccItem, AdjIntTrialAccItemVO.class);
                    break;
                }
            }
        }
        this.editContractualRate = null;
        if (this.editAdjIntTrialAccItemVO != null && this.editAdjIntTrialAccItemVO.getContractualRate() != null) {
            this.editContractualRate
                = this.editAdjIntTrialAccItemVO.getContractualRate().multiply(BigDecimal.valueOf(100));
        }
        showComponent("interestDialog");
        log.info("toEditInterestTab - end");
    }

    public void saveEditInterestTab() {
        log.info("toEditInterestTab - start");
        if (checkEditAdmittedAmount()) {
            calculateRate();
            for (AdjIntTrialAccItemVO adjIntTrialAccItem : adjIntTrialAccItemList) {
                if (adjIntTrialAccItem.getAccountNumber().equals(this.editAdjIntTrialAccItemVO.getAccountNumber())) {
                    adjIntTrialAccItem.setAdmAmount(this.editAdjIntTrialAccItemVO.getAdmAmount());
                    adjIntTrialAccItem.setContractualRate(this.editAdjIntTrialAccItemVO.getContractualRate());
                    adjIntTrialAccItem.setFinalRate(this.editAdjIntTrialAccItemVO.getFinalRate());
                    break;
                }
            }
            this.editAdjIntTrialAccItemVO = null;
            hideComponent("interestDialog");
        }
        log.info("toEditInterestTab - end");
    }

    public void calculateRate() {
        log.info("calculateRate - start");
        BigDecimal rate = null;
        if (dividendParameter != null && editAdjIntTrialAccItemVO != null) {
            BigDecimal paymentInterestRate = dividendParameter.getPaymentInterestRate();
            BigDecimal contractualRate = this.editContractualRate;
            if (contractualRate.compareTo(BigDecimal.ZERO) > 0) {
                contractualRate = contractualRate.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN);
            }
            this.editAdjIntTrialAccItemVO.setContractualRate(contractualRate);
            rate = paymentInterestRate.compareTo(contractualRate) > 0 ? paymentInterestRate : contractualRate;
            this.editAdjIntTrialAccItemVO.setFinalRate(rate);
        }
        log.info("calculateRate - end and Rate =" + rate);
    }

    public boolean checkEditAdmittedAmount() {
        if (this.editAdjIntTrialAccItemVO != null) {
            BigDecimal totalAdmittedAmount = this.interestTrialAdjudicationVO.getSumOfAppAdjRsltAmount();
            BigDecimal admittedAmount = this.editAdjIntTrialAccItemVO.getAdmAmount();
            if (totalAdmittedAmount != null && admittedAmount != null) {
                if (totalAdmittedAmount.compareTo(admittedAmount) < 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        appResourceUtils.getMessage(MsgCodeConstant.MSG_CALCULATE_ADMITTEDAMOUNT_ERROR), ""));
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkTotalAdmittedAmount() {
        log.info("checkTotalAdmittedAmount - start");
        boolean checkTotalAdmitted = true;
        if (this.interestTrialAdjudicationVO != null) {
            BigDecimal totalAdmittedAmount = this.interestTrialAdjudicationVO.getSumOfAppAdjRsltAmount();

            BigDecimal sumOfAdmittedAmount = BigDecimal.ZERO;
            for (AdjIntTrialAccItemVO adjIntTrialAccItem : adjIntTrialAccItemList) {
                if (adjIntTrialAccItem.getAdmAmount() == null) {
                    checkTotalAdmitted = false;
                    break;
                } else {
                    sumOfAdmittedAmount = sumOfAdmittedAmount.add(adjIntTrialAccItem.getAdmAmount());
                }
            }
            if (checkTotalAdmitted) {
                checkTotalAdmitted = totalAdmittedAmount.compareTo(sumOfAdmittedAmount) == 0;
            }
        }
        if (!checkTotalAdmitted) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_TOTAL_ADMITTEDAMOUNT_ERROR), ""));
        }
        log.info("checkTotalAdmittedAmount - end and checkTotalAdmitted =" + checkTotalAdmitted);
        return checkTotalAdmitted;
    }

    // --------------------edit Interest tab end----------------------
    public void showGenerateTable() {
        log.info("showGenerateTable() - start");
        if (checkTotalAdmittedAmount()) {
            if (CommonUtils.isNotEmpty(adjucationResultIntItemList)) {
                this.adjucationResultIntItemList.clear();
            }
            if (this.interestTrialAdjudicationVO != null) {
                Integer adjResultId = this.interestTrialAdjudicationVO.getAdjResultId();
                if (adjResultId != null && adjResultId.intValue() > 0) {
                    List<DividendScheduleItemVO> dividendScheduleItemList
                        = this.interestTrialClientService.searchDivScheduleItemByAdjResultId(adjResultId);
                    if (dividendScheduleItemList != null && !dividendScheduleItemList.isEmpty()) {
                        this.adjucationResultIntItemList = new ArrayList<>();
                        Date caseBringUpDate = null;
                        AdjucationResultVO adjucationResultVO = dividendScheduleItemList.get(0).getAdjResult();
                        if (adjucationResultVO != null) {
                            caseBringUpDate = adjucationResultVO.getCreditor().getCaseInfo().getBringUpDate();
                        }
                        TreeMap<Date,
                            List<DividendScheduleItemVO>> divScheduletMap = dividendScheduleItemList.stream()
                                .collect(Collectors.groupingBy(DividendScheduleItemVO::getPaymentEffectiveDate,
                                    TreeMap::new, Collectors.toList()));
                        BigDecimal sumAmount = BigDecimal.ZERO;
                        for (DividendScheduleItemVO dividendScheduleItem : dividendScheduleItemList) {
                            sumAmount = sumAmount.add(dividendScheduleItem.getDistributionAmount());
                        }
                        if (divScheduletMap != null && !divScheduletMap.isEmpty()) {
                            Integer paymentTime = divScheduletMap.keySet().size();
                            for (AdjIntTrialAccItemVO adjIntTrialAccItem : adjIntTrialAccItemList) {
                                Integer entryCount = 0;
                                for (Entry<Date, List<DividendScheduleItemVO>> entries : divScheduletMap.entrySet()) {
                                    entryCount++;
                                    BigDecimal totalDivScheduleAmount = BigDecimal.ZERO;
                                    // calculation sum of divScheduleitem Amount
                                    for (DividendScheduleItemVO divScheduleItem : entries.getValue()) {
                                        totalDivScheduleAmount
                                            = totalDivScheduleAmount.add(divScheduleItem.getDistributionAmount());
                                    }
                                    AdjucationResultIntItemVO adjucationResultIntItem = new AdjucationResultIntItemVO();
                                    adjucationResultIntItem.setAccountNumber(adjIntTrialAccItem.getAccountNumber());
                                    adjucationResultIntItem.setJudgmentRate(adjIntTrialAccItem.getFinalRate());
                                    adjucationResultIntItem.setTotalDivScheduleAmount(totalDivScheduleAmount);
                                    // calculation date
                                    Integer index = this.adjucationResultIntItemList.size();
                                    BigDecimal totalAmount = CommonUtils
                                        .getBigDecimal(this.interestTrialAdjudicationVO.getSumOfAppAdjRsltAmount());
                                    BigDecimal accountAmount
                                        = CommonUtils.getBigDecimal(adjIntTrialAccItem.getAdmAmount());
                                    BigDecimal principleAmount = BigDecimal.ZERO;
                                    BigDecimal interest = BigDecimal.ZERO;
                                    if (paymentTime.intValue() <= 1 || entryCount.intValue() == 1) {
                                        principleAmount = sumAmount.divide(totalAmount, 2, RoundingMode.HALF_DOWN)
                                            .multiply(accountAmount);
                                        adjucationResultIntItem.setFromDate(caseBringUpDate);
                                        adjucationResultIntItem.setToDate(entries.getKey());
                                    } else {
                                        Calendar fromCalendar = Calendar.getInstance();
                                        Date previousDate = adjucationResultIntItemList.get(index - 1).getToDate();
                                        fromCalendar.setTime(previousDate);
                                        fromCalendar.add(Calendar.DATE, +1);
                                        adjucationResultIntItem.setFromDate(fromCalendar.getTime());
                                        adjucationResultIntItem.setToDate(entries.getKey());
                                        BigDecimal subtractAmount = BigDecimal.ZERO;
                                        List<AdjucationResultIntItemVO> adjResultIntItemList
                                            = this.adjucationResultIntItemList.stream()
                                                .filter(t -> t.getAccountNumber()
                                                    .equals(adjucationResultIntItem.getAccountNumber()))
                                                .collect(Collectors.toList());
                                        for (AdjucationResultIntItemVO adjResultIntItem : adjResultIntItemList) {
                                            subtractAmount
                                                = subtractAmount.add(adjResultIntItem.getTotalDivScheduleAmount());
                                        }
                                        principleAmount = (sumAmount.subtract(subtractAmount))
                                            .divide(totalAmount, 2, RoundingMode.HALF_DOWN).multiply(accountAmount);
                                    }
                                    if (adjucationResultIntItem.getToDate()
                                        .after(adjucationResultIntItem.getFromDate())) {
                                        Integer dayDiffer = (int)((adjucationResultIntItem.getToDate().getTime()
                                            - adjucationResultIntItem.getFromDate().getTime()) / (1000 * 3600 * 24));
                                        interest = principleAmount.multiply(adjIntTrialAccItem.getFinalRate())
                                            .multiply(BigDecimal.valueOf(dayDiffer))
                                            .divide(BigDecimal.valueOf(365), 2, RoundingMode.HALF_DOWN);
                                    }
                                    adjucationResultIntItem.setPrincipleAmount(principleAmount);
                                    adjucationResultIntItem.setInterest(interest);
                                    adjucationResultIntItem.setStatus(CoreConstant.STATUS_ACTIVE);
                                    this.adjucationResultIntItemList.add(adjucationResultIntItem);
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("showGenerateTable() - end");
    }

    public void saveInterestTrial() {
        log.info("save - start");
        if (validateInterestTrial(false)) {
            formData();
            WorkFlowAction workFlowAction = WorkFlowAction.Save;
            executeAction(workFlowAction);
        }
        log.info("save - start");
    }

    private boolean validateInterestTrial(boolean checkGeneration) {
        boolean bl = checkTotalAdmittedAmount();
        if (bl) {
            if (checkGeneration && CommonUtils.isEmpty(adjucationResultIntItemList)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_SUBMIT_INTEREST_ERROR), ""));
                return false;
            }
        }
        return bl;
    }

    private void formData() {
        this.interestTrialAdjudicationVO.setAdjIntTrialAccItemList(this.adjIntTrialAccItemList);
        BigDecimal totalInterest = BigDecimal.ZERO;
        if (CommonUtils.isNotEmpty(adjucationResultIntItemList)) {
            List<AdjucationResultIntItemVO> list = adjucationResultIntItemList.stream()
                .filter(item -> !CoreConstant.STATUS_INACTIVE.equals(item.getStatus())).collect(Collectors.toList());
            if (CommonUtils.isNotEmpty(list)) {
                for (AdjucationResultIntItemVO adjIntTrialAdjResult : list) {
                    totalInterest = totalInterest.add(adjIntTrialAdjResult.getInterest());
                }
            }
            if (this.adjucationResultIntItemList.get(0).getAdjIntResultItemId() != null) {
                this.adjucationResultIntItemList.addAll(this.removeAdjResultIntItemList);
            }
            this.interestTrialAdjudicationVO.setAdjucationResultIntItemList(this.adjucationResultIntItemList);
        }
        this.interestTrialAdjudicationVO.setTotalIntAmount(totalInterest);
    }

    public void submitInterestTrial() {
        if (validateInterestTrial(true)) {
            formData();
            WorkFlowAction workFlowAction = WorkFlowAction.Submit;
            executeAction(workFlowAction);
        }
    }

    public void approveInterestTrial() {
        WorkFlowAction workFlowAction = WorkFlowAction.Approve;
        executeAction(workFlowAction);
    }

    public void rejectInterestTrial() {
        WorkFlowAction workFlowAction = WorkFlowAction.Reject;
        executeAction(workFlowAction);
        hideComponent("rejectDialog");
        this.remarkReson = null;
    }

    public String deleteInterestTrial() {
        if (this.interestTrialAdjudicationVO.getIntTrAdjId() != null
            && this.interestTrialAdjudicationVO.getIntTrAdjId().intValue() > 0) {
            WorkFlowAction workFlowAction = WorkFlowAction.Delete;
            executeAction(workFlowAction);
            return SEARCH_PAGE;
        }
        return null;
    }

    private void prepareData(String workFlowActionCode) {
        SysWorkFlowRuleVO sysWorkFlowRuleVO
            = getAfterStatusByAction(this.interestTrialAdjudicationVO.getStatus(), workFlowActionCode);
        this.interestTrialAdjudicationVO.setStatus(sysWorkFlowRuleVO.getAfterStatus().getCodeValue());
    }

    private void executeAction(WorkFlowAction workFlowAction) {
        if (!workFlowAction.equals(WorkFlowAction.Save) || (this.interestTrialAdjudicationVO.getIntTrAdjId() == null
            || this.interestTrialAdjudicationVO.getIntTrAdjId().intValue() <= 0)) {
            SysApprovalWfVO sysApprovalWf = getSysApprovalWf(workFlowAction, getWorkFlowPrivilegeCode(workFlowAction));
            sysApprovalWf.setRemark(this.remarkReson);
            this.interestTrialAdjudicationVO.setSysApprovalWf(sysApprovalWf);
        }

        prepareData(workFlowAction.getCode());
        Integer adjucationIntTrialAdjResultId
            = interestTrialClientService.saveInterestTrial(this.interestTrialAdjudicationVO);
        if (adjucationIntTrialAdjResultId != null && adjucationIntTrialAdjResultId.intValue() > 0) {
            this.interestTrialAdjudicationVO
                = interestTrialClientService.searchInterestTrialById(adjucationIntTrialAdjResultId);
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

    private void initData() {
        refreshActionButton(this.interestTrialAdjudicationVO.getStatus());
        this.interestTrialAdjudicationVO
            = interestTrialClientService.searchInterestTrialById(this.interestTrialAdjudicationVO.getIntTrAdjId());
        this.adjIntTrialAccItemList = this.interestTrialAdjudicationVO.getAdjIntTrialAccItemList();
        this.adjucationResultIntItemList = this.interestTrialAdjudicationVO.getAdjucationResultIntItemList();

        getStatusDesc(this.interestTrialAdjudicationVO.getStatus());
    }

    // ---------------------- edit Generate Tab start------------------
    public void editGenerateResult() {
        log.info("editGenerateResult - start");
        this.editGenerateRate = null;
        if (this.editAdjucationResultIntItem != null && this.editAdjucationResultIntItem.getJudgmentRate() != null) {
            this.editGenerateRate
                = this.editAdjucationResultIntItem.getJudgmentRate().multiply(BigDecimal.valueOf(100));
        }
        showComponent("generateResultDialog");
        log.info("editGenerateResult - end");
    }

    public void saveEditGenerateTab() {
        log.info("toEditInterestTab - start");
        BigDecimal editRate = this.editGenerateRate;
        if (editRate != null) {
            editRate = editRate.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN);
        }
        this.editAdjucationResultIntItem.setJudgmentRate(editRate);
        hideComponent("generateResultDialog");
        log.info("toEditInterestTab - end");
    }

    public void deleteGenerateResult() {
        log.info("deleteGenerateResult - start");
        if (this.editAdjucationResultIntItem != null) {
            this.adjucationResultIntItemList.remove(this.editAdjucationResultIntItem);
            Integer adjResultIntItemId = this.editAdjucationResultIntItem.getAdjIntResultItemId();
            if (adjResultIntItemId != null && adjResultIntItemId.intValue() > 0) {
                this.editAdjucationResultIntItem.setStatus(CoreConstant.STATUS_INACTIVE);
                this.removeAdjResultIntItemList.add(this.editAdjucationResultIntItem);
            }
            Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_DELETE_SUCCESS));
        }
        log.info("deleteGenerateResult - end");
    }
    // ---------------------- edit Generate Tab end------------------

    @Override
    protected String getStatusGroupCode() {
        return ApplicationCodeTableEnum.WFL.name();
    }

    @Override
    protected String getSubmitPrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_ITAS;
    }

    @Override
    protected String getApprovePrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_ITAA;
    }
}
