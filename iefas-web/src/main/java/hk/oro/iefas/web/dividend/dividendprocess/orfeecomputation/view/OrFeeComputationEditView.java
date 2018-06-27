package hk.oro.iefas.web.dividend.dividendprocess.orfeecomputation.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
import hk.oro.iefas.core.exceptions.BusinessException;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.casemgt.vo.CaseVO;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.dividend.vo.ApprovedAdjucationResultItemVO;
import hk.oro.iefas.domain.dividend.vo.CalculatedCreditorDividendDistributionVO;
import hk.oro.iefas.domain.dividend.vo.CalculationOfDividendFeeVO;
import hk.oro.iefas.domain.dividend.vo.CaseFeeForDividendCalculationVO;
import hk.oro.iefas.domain.dividend.vo.CaseFeeMaintenanceVO;
import hk.oro.iefas.domain.dividend.vo.CreateDividendScheduleVO;
import hk.oro.iefas.domain.dividend.vo.CreateOrFeeComputationVO;
import hk.oro.iefas.domain.dividend.vo.CreditorTypeVO;
import hk.oro.iefas.domain.dividend.vo.DividendCalculationCreditorVO;
import hk.oro.iefas.domain.dividend.vo.DividendCalculationVO;
import hk.oro.iefas.domain.dividend.vo.GazetteVO;
import hk.oro.iefas.domain.dividend.vo.ProvisionsVO;
import hk.oro.iefas.domain.dividend.vo.ScheduleTypeVO;
import hk.oro.iefas.domain.dividend.vo.SysApprovalWfVO;
import hk.oro.iefas.domain.dividend.vo.ValidateOrFeeComputationVO;
import hk.oro.iefas.domain.system.vo.SysRejectReasonVO;
import hk.oro.iefas.domain.system.vo.SysWorkFlowRuleVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.service.CommonClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.WorkFlowBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.common.service.CommonDividendClientService;
import hk.oro.iefas.web.dividend.dividendprocess.orfeecomputation.service.OrFeeComputationClientService;
import hk.oro.iefas.web.dividend.maintenance.gazette.service.GazetteDescriptionClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3274 $ $Date: 2018-06-25 15:52:20 +0800 (週一, 25 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named(value = "feeComputationEditView")
@ViewScoped
public class OrFeeComputationEditView extends WorkFlowBean implements Serializable {

    private static final long serialVersionUID = 3893101115750356152L;

    @Autowired
    private OrFeeComputationClientService orFeeComputationClientService;

    @Autowired
    private CommonDividendClientService commonDividendClientService;

    @Autowired
    private GazetteDescriptionClientService gazetteDescriptionClientService;

    @Autowired
    private CommonClientService commonClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Getter
    @Setter
    private DividendCalculationVO dividendCalculationVO;

    @Getter
    private CaseVO vcase;

    @Getter
    @Setter
    private CalculationOfDividendFeeVO calculationOfDividendFee;

    @Getter
    @Setter
    private ProvisionsVO provisions;

    @Getter
    @Setter
    private CaseFeeForDividendCalculationVO caseFeeForDividendCalculation;

    @Getter
    private List<CaseFeeForDividendCalculationVO> displayCaseFeeForDividendCalculations
        = new ArrayList<CaseFeeForDividendCalculationVO>();

    @Getter
    @Setter
    private Boolean isBankruptcy = false;

    @Getter
    private List<CreditorTypeVO> creditorTypes;
    private Map<String, Integer> creditorTypesMap = new HashMap<>();

    @Getter
    private List<GazetteVO> gazettes;
    @Getter
    private Map<Integer, String> gazettesMap = new HashMap<Integer, String>();

    @Getter
    @Setter
    private String[] selectedCreditorTypes;

    private List<Integer> creditorTypeIds;

    @Getter
    @Setter
    private Integer activeIndex = 0;

    private List<CaseFeeMaintenanceVO> caseFeeMaintenancesForGazette;
    private List<CaseFeeMaintenanceVO> caseFeeMaintenancesForB4;

    @Getter
    private List<BigDecimal> minimumFees = new ArrayList<>();

    @Getter
    private String caseFeeTypeName;

    private Integer dividendCalculationId;

    @Getter
    @Setter
    private Integer caseFeeDesc;

    @Getter
    private BigDecimal totalCaseFee = BigDecimal.ZERO;

    @Getter
    private BigDecimal totalProvisions = BigDecimal.ZERO;

    @Getter
    private BigDecimal totalDistribution;

    @Getter
    @Setter
    private String remarkReson;

    @Getter
    private String status;

    @Getter
    private String paymentType;

    @Getter
    private BigDecimal totalFee;

    @Getter
    private BigDecimal balance;

    @Getter
    private List<SysRejectReasonVO> sysRejectReasons;

    @PostConstruct
    private void init() {
        log.info("ORFeeComputationEditView init");
        dividendCalculationId = Faces.getRequestParameter(DividendWebConstant.DIVIDEND_CALCULATION_ID, Integer.class);
        if (dividendCalculationId != null && dividendCalculationId.intValue() > 0) {
            dividendCalculationVO = orFeeComputationClientService.searchORFeeComputation(dividendCalculationId);
        } else {
            CreateOrFeeComputationVO createORFeeComputationVO
                = Faces.getFlashAttribute(DividendWebConstant.CREATE_ORFEE_COMPUTATION);
            if (createORFeeComputationVO != null) {
                dividendCalculationVO = orFeeComputationClientService.searchORFeeComputation(createORFeeComputationVO);
                dividendCalculationVO.setStatus(initStatus());
            }
        }
        distributions = dividendCalculationVO.getDistributions();

        initData();
        // get drop down list data
        creditorTypes = commonDividendClientService.searchCreditorType();
        if (CommonUtils.isNotEmpty(creditorTypes)) {
            creditorTypes.forEach(creditorTypeVO -> {
                creditorTypesMap.put(creditorTypeVO.getCreditorTypeName(), creditorTypeVO.getCreditorTypeId());
            });
        }
        gazettes = gazetteDescriptionClientService.findAll();
        if (CommonUtils.isNotEmpty(gazettes)) {
            gazettes.forEach(gazette -> {
                gazettesMap.put(gazette.getGazetteId(), gazette.getGazetteName());
            });
        }

        String caseFeeOfMinimumFee;
        if (isBankruptcy) {
            caseFeeTypeName = DividendConstant.DETAIL_OF_CASE_FEE_TYPE_B;
            caseFeeOfMinimumFee = DividendConstant.MINIMUM_FEE_OF_CASE_FEE_TYPE_B;
            caseFeeMaintenancesForB4
                = orFeeComputationClientService.findCaseFeeMainsByType(DividendConstant.CASE_FEE_TYPE_B4);
        } else {
            caseFeeTypeName = DividendConstant.DETAIL_OF_CASE_FEE_TYPE_L;
            caseFeeOfMinimumFee = DividendConstant.MINIMUM_FEE_OF_CASE_FEE_TYPE_L;
        }
        List<CaseFeeMaintenanceVO> miniFeeMaintenances
            = orFeeComputationClientService.findCaseFeeMainsByType(caseFeeOfMinimumFee);
        if (CommonUtils.isNotEmpty(miniFeeMaintenances)) {
            miniFeeMaintenances.forEach(feeMain -> {
                if (feeMain.getFeeAmount() != null) {
                    minimumFees.add(CommonUtils.getBigDecimal(feeMain.getFeeAmount()));
                    Date currentDate = DateUtils.getCurrentDate();
                    if (currentDate.compareTo(feeMain.getPeriodFrom()) >= 0
                        && currentDate.compareTo(feeMain.getPeriodTo()) <= 0) {
                        calculationOfDividendFee.setMinOrFee(feeMain.getFeeAmount());
                    }
                }
            });
        }
        // drop down list of gazette pop up
        caseFeeMaintenancesForGazette = orFeeComputationClientService.findCaseFeeMainsByType(caseFeeTypeName);

        sysRejectReasons = commonClientService.searchRejectReasonList();
    }

    private void initData() {
        refreshActionButton(dividendCalculationVO.getStatus());

        String creditorTypeStr = dividendCalculationVO.getCreditorTypeStr();
        if (CommonUtils.isNotBlank(creditorTypeStr)) {
            selectedCreditorTypes = dividendCalculationVO.getCreditorTypeStr().split(",");
        }
        vcase = dividendCalculationVO.getVcase();
        calculationOfDividendFee = dividendCalculationVO.getCalculationOfDividendFee();

        String format = new DecimalFormat("0.00")
            .format(CommonUtils.getBigDecimal(calculationOfDividendFee.getDistributedToCreditors()));
        calculationOfDividendFee.setDistributedToCreditors(new BigDecimal(format));
        provisions = dividendCalculationVO.getProvisions();

        if (DividendConstant.CASE_TYPE_B.equals(vcase.getCaseType().getCaseTypeCode())) {
            isBankruptcy = true;
        }

        calculateTotalProvisions();
        calculateTotalDistribution();

        filterGazettes();

        getStatusDesc(dividendCalculationVO.getStatus());

        List<ApplicationCodeTableVO> paymentTypeList
            = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.DPA.name());
        if (CommonUtils.isNotEmpty(paymentTypeList)) {
            paymentType = paymentTypeList.stream()
                .filter(item -> item.getCodeValue().equals(this.dividendCalculationVO.getPaymentType())).findFirst()
                .get().getCodeDesc();
        }

        calculateTotalFee();
        calculateBalance();
    }

    public void changeB4Date() {
        if (validateB4Date()) {
            this.calculationOfDividendFee.setBb4OrderAndPeriodChgs(getB4Charge());
        }
    }

    private boolean validateB4Date() {
        Date start = this.calculationOfDividendFee.getBb4StartInterimRec();
        Date end = this.calculationOfDividendFee.getBb4EndInterimRec();
        if (start == null) {
            // FacesContext.getCurrentInstance().addMessage(null,
            // new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select start interim receiver", ""));
            return false;
        }

        if (end == null) {
            // FacesContext.getCurrentInstance().addMessage(null,
            // new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select end interim receiver", ""));
            return false;
        }

        if (end.before(start)) {
            // FacesContext.getCurrentInstance().addMessage(null,
            // new FacesMessage(FacesMessage.SEVERITY_ERROR, "End date can't be earlier than start date", ""));
            this.calculationOfDividendFee.setBb4EndInterimRec(start);
            // return false;
        }

        return true;
    }

    private BigDecimal getB4Charge() {
        if (CommonUtils.isNotEmpty(caseFeeMaintenancesForB4)) {
            Date start = this.calculationOfDividendFee.getBb4StartInterimRec();
            Date end = this.calculationOfDividendFee.getBb4EndInterimRec();

            List<CaseFeeMaintenanceVO> caseFeeMains = null;
            if (start != null && end != null) {
                caseFeeMains = caseFeeMaintenancesForB4.stream()
                    .filter(caseFeeMaintenanceVO -> (end.compareTo(caseFeeMaintenanceVO.getPeriodTo()) <= 0)
                        && (start.compareTo(caseFeeMaintenanceVO.getPeriodFrom()) >= 0)
                        && caseFeeMaintenanceVO.getFeeAmount() != null)
                    .collect(Collectors.toList());
                // Collections.sort(caseFeeMaintenancesForB4, new Comparator<CaseFeeMaintenanceVO>() {
                // @Override
                // public int compare(CaseFeeMaintenanceVO o1, CaseFeeMaintenanceVO o2) {
                // return o1.getPeriodTo().compareTo(o2.getPeriodTo());
                // }
                // });
            }
            if (CommonUtils.isNotEmpty(caseFeeMains)) {
                return caseFeeMains.get(0).getFeeAmount();
            }
        }
        return BigDecimal.ZERO;
    }

    public void calculateTotalProvisions() {
        totalProvisions = BigDecimal.ZERO;
        if (provisions != null) {
            totalProvisions = CommonUtils.add(totalProvisions, provisions.getNoticsPrePayGazCharge(),
                provisions.getNoticsPrePayAdvCharge(), provisions.getNoticsDivGazCharge(),
                provisions.getCosRelCourtFee(), provisions.getCosRelGazCharge(), provisions.getCosRelOrFee(),
                provisions.getOthersPetTaxCostDep(), provisions.getOthersOthers(), provisions.getOldBalance());

            if (isBankruptcy) {
                totalProvisions = CommonUtils.add(totalProvisions, provisions.getNoticsFullPayGazCharge(),
                    provisions.getNoticsFullPayAdvCharge(), provisions.getIntDisPubExaGazCharge(),
                    provisions.getIntDisPubExaOrFee(), provisions.getRecAnnGazAdvCharge(),
                    provisions.getRecAnnGazOrFee(), provisions.getDisGazAdvCharge(), provisions.getDisGazOrFee(),
                    provisions.getOthersDisMonRefLand(), provisions.getOthersStoSeiDoc());
            } else {
                totalProvisions = CommonUtils.add(totalProvisions,
                    CommonUtils.getBigDecimal(provisions.getNoticsRetCapGazCharge()));
            }
        }
    }

    public void calculateTotalDistribution() {
        totalDistribution = BigDecimal.ZERO;
        List<CalculatedCreditorDividendDistributionVO> calculatedCreditorDividendDistributions
            = dividendCalculationVO.getCalculatedCreditorDividendDistributions();
        if (CommonUtils.isNotEmpty(calculatedCreditorDividendDistributions)) {
            calculatedCreditorDividendDistributions.forEach(distribution -> {
                totalDistribution = CommonUtils.add(totalDistribution, distribution.getDistAmount());
            });
        }
    }

    private Map<Integer, Map<String, ApprovedAdjucationResultItemVO>> distributions;

    public void changeCreditorType() {
        creditorTypeIds = null;
        boolean hasError = false;
        if (selectedCreditorTypes != null && selectedCreditorTypes.length > 0) {
            creditorTypeIds = new ArrayList<>();
            BigDecimal commonPrecentage = null;
            for (String selectedCreditorType : selectedCreditorTypes) {
                Integer integer = creditorTypesMap.get(selectedCreditorType);
                if (integer != null && integer.intValue() > 0) {
                    creditorTypeIds.add(integer);
                    Map<String, ApprovedAdjucationResultItemVO> map = distributions.get(integer);
                    if (map != null) {
                        for (String key : map.keySet()) {
                            if (commonPrecentage == null) {
                                commonPrecentage = map.get(key).getPercentPaid();
                                break;
                            }
                        }

                        for (String key : map.keySet()) {
                            // all adjType should have the same percentage in a creditor type
                            if (!map.get(key).getPercentPaid().equals(commonPrecentage)) {
                                hasError = true;
                                break;
                            }
                        }
                    }
                }
                if (hasError) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Creditor type checked has different percentage, please check again", ""));
                    break;
                }
            }
        }

        if (hasError) {
            creditorTypeIds = null;
            selectedCreditorTypes = null;
        }
        updateDistribution();
        calculateTotalDistribution();
    }

    private void updateDistribution() {
        List<CalculatedCreditorDividendDistributionVO> calculatedCreditorDividendDistributions
            = this.dividendCalculationVO.getCalculatedCreditorDividendDistributions();
        BigDecimal zero = BigDecimal.ZERO;
        for (CalculatedCreditorDividendDistributionVO calculatedCreditorDividendDistributionVO : calculatedCreditorDividendDistributions) {
            calculatedCreditorDividendDistributionVO.setAmountPaid(zero);
            calculatedCreditorDividendDistributionVO.setDistAmount(zero);
            calculatedCreditorDividendDistributionVO.setPercent(zero);
        }
        if (CommonUtils.isNotEmpty(creditorTypeIds)) {
            for (Integer credTypeId : creditorTypeIds) {
                Map<String, ApprovedAdjucationResultItemVO> map = distributions.get(credTypeId);
                if (map != null) {
                    for (CalculatedCreditorDividendDistributionVO calculatedCreditorDividendDistributionVO : calculatedCreditorDividendDistributions) {
                        String adjudicationTypeName
                            = calculatedCreditorDividendDistributionVO.getAdjudicationType().getAdjudicationTypeName();

                        ApprovedAdjucationResultItemVO item = map.get(adjudicationTypeName);
                        if (item != null) {
                            calculatedCreditorDividendDistributionVO.setAmountPaid(CommonUtils
                                .add(calculatedCreditorDividendDistributionVO.getAmountPaid(), item.getAmountPaid()));
                            calculatedCreditorDividendDistributionVO.setDistAmount(CommonUtils.add(
                                calculatedCreditorDividendDistributionVO.getDistAmount(), item.getAdmittedAmount()));
                            calculatedCreditorDividendDistributionVO.setPercent(item.getPercentPaid());
                        } else {
                            throw new BusinessException(
                                "miss Approved adjudication result item of type ['" + adjudicationTypeName + "']");
                        }

                    }

                }
            }
        }
        this.dividendCalculationVO.setCalculatedCreditorDividendDistributions(calculatedCreditorDividendDistributions);
    }

    public void popUpCreateGazette() {
        caseFeeForDividendCalculation = new CaseFeeForDividendCalculationVO();
        caseFeeDesc = null;
        showComponent("editDialog");
    }

    public void createHandleClose() {
        caseFeeForDividendCalculation = null;
    }

    public void changeGazetteFee() {
        caseFeeForDividendCalculation.setCaseFeeAmount(getFeeAmount());
    }

    private BigDecimal getFeeAmount() {
        if (CommonUtils.isNotEmpty(caseFeeMaintenancesForGazette)) {
            Date caseFeeDate = caseFeeForDividendCalculation.getCaseFeeDate();

            List<CaseFeeMaintenanceVO> caseFeeMains = caseFeeMaintenancesForGazette.stream()
                .filter(caseFeeMaintenanceVO -> (caseFeeDate.compareTo(caseFeeMaintenanceVO.getPeriodTo()) <= 0)
                    && (caseFeeDate.compareTo(caseFeeMaintenanceVO.getPeriodFrom()) >= 0)
                    && caseFeeMaintenanceVO.getFeeAmount() != null)
                .collect(Collectors.toList());
            if (CommonUtils.isNotEmpty(caseFeeMains)) {
                return caseFeeMains.get(0).getFeeAmount();
            }
        }
        return BigDecimal.ZERO;
    }

    public void saveGazetteCreate() {
        if (calculationOfDividendFee.getCaseFeeForDividendCalculations() == null) {
            calculationOfDividendFee.setCaseFeeForDividendCalculations(new ArrayList<>());
        }
        this.caseFeeForDividendCalculation.setCaseFeeDesc(caseFeeDesc);
        this.caseFeeForDividendCalculation.setStatus(CoreConstant.STATUS_ACTIVE);
        if (!calculationOfDividendFee.getCaseFeeForDividendCalculations()
            .contains(this.caseFeeForDividendCalculation)) {
            calculationOfDividendFee.getCaseFeeForDividendCalculations().add(this.caseFeeForDividendCalculation);
        }
        filterGazettes();
        hideComponent("editDialog");
    }

    public void deleteGazette() {
        for (CaseFeeForDividendCalculationVO caseFeeVO : calculationOfDividendFee.getCaseFeeForDividendCalculations()) {
            if (caseFeeVO.equals(this.caseFeeForDividendCalculation)) {
                caseFeeVO.setStatus(CoreConstant.STATUS_INACTIVE);
                break;
            }
        }
        filterGazettes();
    }

    private void filterGazettes() {
        displayCaseFeeForDividendCalculations.clear();
        totalCaseFee = BigDecimal.ZERO;
        if (CommonUtils.isNotEmpty(calculationOfDividendFee.getCaseFeeForDividendCalculations())) {
            calculationOfDividendFee.getCaseFeeForDividendCalculations().forEach(caseFee -> {
                if (CoreConstant.STATUS_ACTIVE.equals(caseFee.getStatus())) {
                    displayCaseFeeForDividendCalculations.add(caseFee);
                    totalCaseFee = CommonUtils.add(totalCaseFee, caseFee.getCaseFeeAmount());
                }
            });
        }
    }

    public void showBIV() {
        showComponent("viewDialog");
    }

    public void changeParticipantNum() {
        if (isBankruptcy) {
            this.calculationOfDividendFee
                .setBb5TotalParticipants(CommonUtils.add(this.calculationOfDividendFee.getBb5NumberOfCreditor(),
                    this.calculationOfDividendFee.getBb5NumberOfDedtor()));
            this.calculationOfDividendFee.setBb5ParticipantsUncharged(
                CommonUtils.subtract(this.calculationOfDividendFee.getBb5TotalParticipants(),
                    this.calculationOfDividendFee.getBb5ParticipantsCharged()));
        } else {
            this.calculationOfDividendFee
                .setLbivTotalParticipants(CommonUtils.add(this.calculationOfDividendFee.getLbivCredNumber(),
                    this.calculationOfDividendFee.getLbivDebtNumber(), this.calculationOfDividendFee.getLbivMembers()));
            this.calculationOfDividendFee.setLbivParticipantsUncharged(
                CommonUtils.subtract(this.calculationOfDividendFee.getLbivTotalParticipants(),
                    this.calculationOfDividendFee.getLbivParticipantsCharged()));
        }
        calculateTotalFee();
    }

    public void changeActualAssets() {
        if (isBankruptcy) {
            BigDecimal assetsUncharged = CommonUtils.getBigDecimal(this.calculationOfDividendFee.getBb9ActualAssets())
                .subtract(CommonUtils.getBigDecimal(this.calculationOfDividendFee.getBb9ActualAssetsCharged()));
            this.calculationOfDividendFee.setBb9AssetsUncharged(
                assetsUncharged.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : assetsUncharged);
        } else {
            BigDecimal assetsUncharged = CommonUtils.getBigDecimal(this.calculationOfDividendFee.getLbiActualAssets())
                .subtract(CommonUtils.getBigDecimal(this.calculationOfDividendFee.getLbiActualAssetsCharged()));
            this.calculationOfDividendFee.setLbiAssetsUncharged(
                assetsUncharged.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : assetsUncharged);
        }
        calculateTotalFee();
    }

    public void calculateTotalFee() {
        BigDecimal calculatedFee;
        if (isBankruptcy) {
            calculatedFee = CommonUtils.add(this.calculationOfDividendFee.getBb1TotalReaFee(),
                this.calculationOfDividendFee.getBb2AprovedCompo(),
                this.calculationOfDividendFee.getBb2UnaprpvedCompo(),
                this.calculationOfDividendFee.getBb4OrderAndPeriodChgs(),
                this.calculationOfDividendFee.getBb5StatFeeUncharged(),
                this.calculationOfDividendFee.getBb6MeetingFee(), this.calculationOfDividendFee.getBb9AssetsUncharged(),
                this.calculationOfDividendFee.getOthersAmount());
        } else {
            calculatedFee = CommonUtils.add(this.calculationOfDividendFee.getLbiAssetsUncharged(),
                this.calculationOfDividendFee.getLbiiPrevLiquFee(), this.calculationOfDividendFee.getLbiiiSpecManFee(),
                this.calculationOfDividendFee.getLbivStatFeeUncharged(),
                this.calculationOfDividendFee.getLbivRealizationFee(),
                this.calculationOfDividendFee.getLbvDebeDistFee(), this.calculationOfDividendFee.getLbviSecuCredFee(),
                this.calculationOfDividendFee.getLbviiPrevSpecDutiFee(),
                this.calculationOfDividendFee.getOthersAmount());
        }
        this.calculationOfDividendFee.setCalculatedOrFee(calculatedFee);

        if (calculatedFee.compareTo(CommonUtils.getBigDecimal(this.calculationOfDividendFee.getMinOrFee())) < 0) {
            if (this.calculationOfDividendFee.getAlreadyChargedOrFee() == null
                || this.calculationOfDividendFee.getAlreadyChargedOrFee().intValue() == 0) {
                totalFee = this.calculationOfDividendFee.getMinOrFee();
            } else {
                totalFee = this.calculationOfDividendFee.getAlreadyChargedOrFee();
            }
        } else {
            totalFee = CommonUtils.subtract(this.calculationOfDividendFee.getCalculatedOrFee(),
                this.calculationOfDividendFee.getAlreadyChargedOrFee());
        }
    }

    public void calculateBalance() {
        // balance = Case in Hand -Total Official Receiver's Fee - Total Provisions - Total Distribution
        balance = CommonUtils.subtract(this.dividendCalculationVO.getCaseInHand(), totalFee, totalProvisions,
            totalDistribution);
    }

    public void changeToProvision() {
        if (Messages.isEmpty()) {
            this.activeIndex = 1;
        }
    }

    public void changeToDetail() {
        if (Messages.isEmpty()) {
            this.activeIndex = 0;
        }
    }

    public void changeToDistribution() {
        if (Messages.isEmpty()) {
            this.activeIndex = 2;
        }
    }

    public void calculateAmountPaid(CalculatedCreditorDividendDistributionVO distribution) {
        distribution.setDistAmount(CommonUtils.multiply(false, distribution.getPercent(), distribution.getAmountPaid(),
            new BigDecimal("0.01")));
        calculateTotalDistribution();
    }

    public void saveORFeeComputation() {
        if (validateORFeeComputation()) {
            dividendCalculationVO.setVcase(vcase);
            calculationOfDividendFee.setCaseFeeTypeName(caseFeeTypeName);
            dividendCalculationVO.setCalculationOfDividendFee(calculationOfDividendFee);
            dividendCalculationVO.setProvisions(provisions);
            setCreditors();
            WorkFlowAction workFlowAction = WorkFlowAction.Save;
            executeAction(workFlowAction);
        }
    }

    private void prepareData(String workFlowActionCode) {
        SysWorkFlowRuleVO sysWorkFlowRuleVO
            = getAfterStatusByAction(this.dividendCalculationVO.getStatus(), workFlowActionCode);
        dividendCalculationVO.setStatus(sysWorkFlowRuleVO.getAfterStatus().getCodeValue());
    }

    private boolean validateORFeeComputation() {
        boolean bl = true;
        calculateBalance();
        if (this.balance == null || balance.intValue() < 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_BALANCE_IS_NEGATIVE), ""));
            bl = false;
        }
        ValidateOrFeeComputationVO validateOrFeeComputationVO = new ValidateOrFeeComputationVO();
        validateOrFeeComputationVO.setCaseId(vcase.getCaseId());
        validateOrFeeComputationVO.setDividendCalId(dividendCalculationVO.getDividendCalculationId());
        validateOrFeeComputationVO.setCredTypes(creditorTypeIds);
        validateOrFeeComputationVO.setCreateValidation(false);
        if (!orFeeComputationClientService.validateCaseCreatable(validateOrFeeComputationVO)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_CREATE_COMPUTATION_CREDTYPE_ERROR), ""));
            bl = false;
        }

        List<CalculatedCreditorDividendDistributionVO> calculatedCreditorDividendDistributions
            = dividendCalculationVO.getCalculatedCreditorDividendDistributions();
        if (CommonUtils.isNotEmpty(calculatedCreditorDividendDistributions)) {
            List<CalculatedCreditorDividendDistributionVO> hasPercentage = calculatedCreditorDividendDistributions
                .stream().filter(item -> (BigDecimal.ZERO.compareTo(CommonUtils.getBigDecimal(item.getPercent())) < 0))
                .collect(Collectors.toList());
            if (CommonUtils.isEmpty(hasPercentage)) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        appResourceUtils.getMessage(MsgCodeConstant.MSG_IS_MANDATORY, MsgParamCodeConstant.PERCENTAGE),
                        ""));
                bl = false;
            }
        }
        return bl;
    }

    private void setCreditors() {
        List<DividendCalculationCreditorVO> dividendCalculationCreditors
            = dividendCalculationVO.getDividendCalculationCreditors();
        if (selectedCreditorTypes == null || selectedCreditorTypes.length <= 0) {
            if (CommonUtils.isNotEmpty(dividendCalculationCreditors)) {
                dividendCalculationCreditors.forEach(creType -> {
                    creType.setStatus(CoreConstant.STATUS_INACTIVE);
                });
            }
        } else {
            if (CommonUtils.isEmpty(dividendCalculationCreditors)) {
                List<DividendCalculationCreditorVO> list = new ArrayList<DividendCalculationCreditorVO>();
                for (String selectedCreditorType : selectedCreditorTypes) {
                    DividendCalculationCreditorVO dividendCalculationCreditor = new DividendCalculationCreditorVO();
                    dividendCalculationCreditor.setCreditorTypeId(creditorTypesMap.get(selectedCreditorType));
                    dividendCalculationCreditor.setStatus(CoreConstant.STATUS_ACTIVE);
                    list.add(dividendCalculationCreditor);
                }
                dividendCalculationVO.setDividendCalculationCreditors(list);
            } else {
                boolean existed = false;
                for (String selectedCreditorType : selectedCreditorTypes) {
                    for (DividendCalculationCreditorVO dividendCalculationCreditorVO : dividendCalculationCreditors) {
                        if (creditorTypesMap.get(selectedCreditorType)
                            .equals(dividendCalculationCreditorVO.getCreditorTypeId())) {
                            existed = true;
                            break;
                        }
                    }
                    if (!existed) {
                        DividendCalculationCreditorVO dividendCalculationCreditor = new DividendCalculationCreditorVO();
                        dividendCalculationCreditor.setCreditorTypeId(creditorTypesMap.get(selectedCreditorType));
                        dividendCalculationCreditor.setStatus(CoreConstant.STATUS_ACTIVE);
                        dividendCalculationCreditors.add(dividendCalculationCreditor);
                    }
                    existed = false;
                }

                existed = false;
                for (DividendCalculationCreditorVO dividendCalculationCreditorVO : dividendCalculationCreditors) {
                    for (String selectedCreditorType : selectedCreditorTypes) {
                        if (creditorTypesMap.get(selectedCreditorType)
                            .equals(dividendCalculationCreditorVO.getCreditorTypeId())) {
                            existed = true;
                            break;
                        }
                    }
                    if (!existed) {
                        dividendCalculationCreditorVO.setStatus(CoreConstant.STATUS_INACTIVE);
                    }
                    existed = false;
                }
            }
        }
    }

    public void submitORFeeComputation() {
        WorkFlowAction workFlowAction = WorkFlowAction.Submit;
        executeAction(workFlowAction);
    }

    public void approveORFeeComputation() {
        WorkFlowAction workFlowAction = WorkFlowAction.Approve;
        executeAction(workFlowAction);
    }

    public String deleteORFeeComputation() {
        if (this.dividendCalculationId != null && dividendCalculationId.intValue() > 0) {
            WorkFlowAction workFlowAction = WorkFlowAction.Delete;
            executeAction(workFlowAction);
            return SEARCH_PAGE;
        }
        return null;
    }

    public void rejectORFeeComputation() {
        WorkFlowAction workFlowAction = WorkFlowAction.Reject;
        executeAction(workFlowAction);
        hideComponent("rejectDialog");
        this.remarkReson = null;
    }

    private void executeAction(WorkFlowAction workFlowAction) {
        if (!WorkFlowAction.Save.equals(workFlowAction)
            || (this.dividendCalculationVO.getDividendCalculationId() == null
                || this.dividendCalculationVO.getDividendCalculationId().intValue() <= 0)) {
            SysApprovalWfVO sysApprovalWf = getSysApprovalWf(workFlowAction, getWorkFlowPrivilegeCode(workFlowAction));
            sysApprovalWf.setRemark(this.remarkReson);
            this.dividendCalculationVO.setSysApprovalWf(sysApprovalWf);
        }

        prepareData(workFlowAction.getCode());
        dividendCalculationId = orFeeComputationClientService.saveORFeeComputation(dividendCalculationVO);
        if (dividendCalculationId != null) {
            dividendCalculationVO = orFeeComputationClientService.searchORFeeComputation(dividendCalculationId);
            if (WorkFlowAction.Delete.equals(workFlowAction)) {
                Faces.setFlashAttribute(DividendWebConstant.DELETE_MSG_CODE, getSuccessMsg(workFlowAction));
            } else {
                initData();
                Messages.addGlobalInfo(appResourceUtils.getMessage(getSuccessMsg(workFlowAction)));
            }
        } else {
            if (WorkFlowAction.Delete.equals(workFlowAction)) {
                Faces.setFlashAttribute(DividendWebConstant.DELETE_MSG_CODE, MsgCodeConstant.MSG_SAVE_FAIL);
            } else {
                Messages.addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL));
            }
        }
    }

    @Override
    protected String getStatusGroupCode() {
        return ApplicationCodeTableEnum.OCS.name();
    }

    @Override
    protected String getSubmitPrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_OEFCS;
    }

    @Override
    protected String getApprovePrivilegeCode() {
        return PrivilegeConstant.PRIVILEGE_OEFCA;
    }

    public void createSchedule() {
        CreateDividendScheduleVO createDividendScheduleVO = new CreateDividendScheduleVO();
        CaseNumberVO caseNumberVO = new CaseNumberVO();
        caseNumberVO.setCaseType(vcase.getCaseType().getCaseTypeCode());
        caseNumberVO.setCaseSequence(vcase.getCaseNo());
        caseNumberVO.setCaseYear(vcase.getCaseYear());
        createDividendScheduleVO.setCaseNumber(caseNumberVO);
        createDividendScheduleVO.setPaymentEffectiveDate(this.dividendCalculationVO.getPaymentDate());
        createDividendScheduleVO.setScheduleType(new ScheduleTypeVO());

        try {
            ApplicationCodeTableVO schdType;
            List<ApplicationCodeTableVO> dividendScheduleTypeList
                = appResourceUtils.getApplicationCodeByGroup(ApplicationCodeTableEnum.DST.name());
            if (DividendConstant.DIVIDEND_TYPE_DIVIDEND.equals(this.dividendCalculationVO.getPaymentType())) {
                schdType = dividendScheduleTypeList.stream()
                    .filter(item -> DividendConstant.SCHEDULE_TYPE_DS.equals(item.getCodeValue())).findFirst().get();
            } else {
                schdType = dividendScheduleTypeList.stream()
                    .filter(item -> DividendConstant.SCHEDULE_TYPE_IS.equals(item.getCodeValue())).findFirst().get();
            }
            createDividendScheduleVO.getScheduleType().setScheduleTypeName(schdType.getCodeDesc());
        } catch (Exception e) {
            throw new BusinessException("missed 'DST' group code in application code list ");
        }

        Faces.setFlashAttribute(DividendWebConstant.CREATE_DIVIDEND_SCHEDULE_VO, createDividendScheduleVO);

        try {
            ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
            eContext.redirect(eContext.getApplicationContextPath()
                + "/oro/admin/dividend/payment-dividend-processing/dividend-interest-schedule/edit.xhtml");
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
