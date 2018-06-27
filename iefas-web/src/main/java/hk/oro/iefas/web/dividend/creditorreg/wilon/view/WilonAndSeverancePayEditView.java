package hk.oro.iefas.web.dividend.creditorreg.wilon.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.domain.casemgt.vo.CreditorVO;
import hk.oro.iefas.domain.common.vo.ApplicationCodeTableVO;
import hk.oro.iefas.domain.common.vo.CaseNumberVO;
import hk.oro.iefas.domain.dividend.vo.DividendParameterVO;
import hk.oro.iefas.domain.dividend.vo.WilonAndSeverancePayVO;
import hk.oro.iefas.web.common.constant.DividendWebConstant;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.dividend.creditorreg.wilon.service.WilonAndSeveranceClientService;
import hk.oro.iefas.web.dividend.maintenance.otherparameter.service.DividendParameterClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class WilonAndSeverancePayEditView extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private WilonAndSeveranceClientService wilonAndSeveranceClientService;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Autowired
    private DividendParameterClientService dividendParameterClientService;

    @Getter
    @Setter
    private List<ApplicationCodeTableVO> employeeTypeList = new ArrayList<>();

    @Getter
    @Setter
    private WilonAndSeverancePayVO wilonAndSeverancePay;

    @Getter
    @Setter
    private CaseNumberVO caseNumber;

    @Getter
    @Setter
    private String caseFeeType;

    @Getter
    @Setter
    private boolean reportReady = false;

    @Getter
    @Setter
    private String selectedEmployeeType = "";

    private DividendParameterVO dividendParameterVO;

    @PostConstruct
    private void init() {
        log.info("==============WilonAndSeverancePayEditView init===================");
        List<DividendParameterVO> dividendParameterList = this.dividendParameterClientService.searchDividendParameter();
        if (dividendParameterList != null && dividendParameterList.size() > 0) {
            this.dividendParameterVO = dividendParameterList.get(0);
        }
        Integer wilonAndSevrnPayId = Faces.getRequestParameter(DividendWebConstant.WILONANDSEVRNPAY_Id, Integer.class);
        if (wilonAndSevrnPayId != null && wilonAndSevrnPayId.intValue() > 0) {
            this.wilonAndSeverancePay = wilonAndSeveranceClientService.searchWILONAndSeverancePay(wilonAndSevrnPayId);
            fillWILONAndSeveranceData();
        } else {
            this.wilonAndSeverancePay = new WilonAndSeverancePayVO();
            Integer creditorId = Faces.getFlashAttribute(DividendWebConstant.CREDITOR_ID);
            if (creditorId != null && creditorId.intValue() > 0) {
                CreditorVO creditorVO = wilonAndSeveranceClientService.searchCreditorById(creditorId);
                if (creditorVO != null) {
                    this.wilonAndSeverancePay.setCaseCred(creditorVO);
                    this.wilonAndSeverancePay.setCaseInfo(creditorVO.getCaseInfo());
                }
            }
        }

        this.employeeTypeList = appResourceUtils.getApplicationCodeByGroup("EMT");
        log.info("==============WilonAndSeverancePayEditView init End===================");
    }

    public void saveWILONAndSeverancePay() {
        log.info("saveWILONAndSeverancePay() start");
        log.info("WilonAndSeverancePay: " + wilonAndSeverancePay);
        if (this.wilonAndSeverancePay != null) {
            Integer result = wilonAndSeveranceClientService.saveWILONAndSeverancePay(wilonAndSeverancePay);
            if (result != null && result.intValue() > 0) {
                Messages.addGlobalInfo(appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_SUCCESS));
                this.wilonAndSeverancePay = wilonAndSeveranceClientService.searchWILONAndSeverancePay(result);
                fillWILONAndSeveranceData();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_SAVE_FAIL), ""));
            }
        }
        log.info("saveWILONAndSeverancePay() end");
    }

    public void fillWILONAndSeveranceData() {
        log.info("fillWILONAndSeveranceData() - start");
        if (this.wilonAndSeverancePay != null) {
            String employeeType = this.wilonAndSeverancePay.getEmployeeType();
            BigDecimal wageRate = this.wilonAndSeverancePay.getWageRate();
            Date appointmentDate = this.wilonAndSeverancePay.getAppointmentDate();
            Date laidOffDate = this.wilonAndSeverancePay.getLaidOffDate();
            if (employeeType != null && wageRate != null && appointmentDate != null && laidOffDate != null) {
                // Wilon
                BigDecimal totalWilon = employeeType.equals(DividendWebConstant.WILONANDSEVRNPAY_EMPLOYEE_TYPE_M)
                    ? wageRate : wageRate.multiply(this.dividendParameterVO.getWilonPeriodDay());
                BigDecimal maxAmount = this.dividendParameterVO.getMaxAmount();
                wilonAndSeverancePay.setWilonPref(totalWilon.compareTo(maxAmount) < 1 ? totalWilon : maxAmount);
                wilonAndSeverancePay.setWilonOrdi(totalWilon.subtract(wilonAndSeverancePay.getWilonPref()));
                wilonAndSeverancePay.setTotalWilon(totalWilon);
                // SEVERANCE
                if (this.dividendParameterVO != null) {
                    // Date Calculation
                    Calendar appointmentCalendar = Calendar.getInstance();
                    appointmentCalendar.setTime(appointmentDate);
                    Calendar laidOffCalendar = Calendar.getInstance();
                    laidOffCalendar.setTime(laidOffDate);
                    BigDecimal dayDifferent;
                    BigDecimal yearDifferent;
                    Integer laidOffDateOfYear = laidOffCalendar.get(Calendar.DAY_OF_YEAR);
                    Integer appointmentDateOfYear = appointmentCalendar.get(Calendar.DAY_OF_YEAR);
                    if (laidOffDateOfYear >= appointmentDateOfYear) {
                        yearDifferent = BigDecimal
                            .valueOf(laidOffCalendar.get(Calendar.YEAR) - appointmentCalendar.get(Calendar.YEAR));
                        dayDifferent = BigDecimal.valueOf(laidOffDateOfYear - appointmentDateOfYear + 1);
                    } else {
                        yearDifferent = BigDecimal
                            .valueOf(laidOffCalendar.get(Calendar.YEAR) - appointmentCalendar.get(Calendar.YEAR) - 1);
                        dayDifferent = BigDecimal.valueOf(365 - (appointmentDateOfYear - laidOffDateOfYear));
                    }
                    BigDecimal dateDifferentRate
                        = (dayDifferent.divide(BigDecimal.valueOf(365), 10, RoundingMode.HALF_DOWN)).add(yearDifferent);
                    BigDecimal periodWithoutPay = this.dividendParameterVO.getSevrnPeriodWoPay();
                    BigDecimal severanceMaxAmount = this.dividendParameterVO.getSevrnMaxAmount();
                    if (employeeType.equals(DividendWebConstant.WILONANDSEVRNPAY_EMPLOYEE_TYPE_M)) {
                        // employeeType.equals("M")
                        // Yearly Calculation
                        BigDecimal maximumMonthlyRate = this.dividendParameterVO.getSevrnMaxMonRate();
                        BigDecimal comparisonMonthlyRate
                            = wageRate.compareTo(maximumMonthlyRate) > 0 ? maximumMonthlyRate : wageRate;
                        BigDecimal monthlyFractionRate;
                        if (this.dividendParameterVO.getSevrnMonFractionRateDen().compareTo(BigDecimal.ZERO) != 0) {
                            monthlyFractionRate = dividendParameterVO.getSevrnMonFractionRateNom()
                                .divide(dividendParameterVO.getSevrnMonFractionRateDen(), 10, RoundingMode.HALF_DOWN);
                        } else {
                            monthlyFractionRate = BigDecimal.ZERO;
                        }
                        BigDecimal yearlyCalculation
                            = comparisonMonthlyRate.multiply(monthlyFractionRate).multiply(dateDifferentRate);
                        BigDecimal totalSever;
                        if (yearDifferent.compareTo(periodWithoutPay) >= 0) {
                            totalSever = (yearlyCalculation.compareTo(severanceMaxAmount)) > 0 ? severanceMaxAmount
                                : yearlyCalculation;
                        } else {
                            totalSever = BigDecimal.ZERO;
                        }
                        BigDecimal sevrnMaxAmountPre = this.dividendParameterVO.getSevrnMaxAmountPre();
                        BigDecimal severPref
                            = totalSever.compareTo(sevrnMaxAmountPre) > 0 ? sevrnMaxAmountPre : totalSever;
                        BigDecimal severOrdi = totalSever.subtract(severPref);
                        this.wilonAndSeverancePay.setTotalSever(totalSever);
                        this.wilonAndSeverancePay.setSeverPref(severPref);
                        this.wilonAndSeverancePay.setSeverOrdi(severOrdi);
                    } else {
                        // employeeType.equals("D")
                        // Daily Calculation
                        BigDecimal maximumDailyRate = this.dividendParameterVO.getSevrnMaxDayRate();
                        BigDecimal comparisonMonthlyRate
                            = wageRate.compareTo(maximumDailyRate) > 0 ? maximumDailyRate : wageRate;
                        BigDecimal dailyCalculation = comparisonMonthlyRate
                            .multiply(this.dividendParameterVO.getSevrnDaiFractionRate()).multiply(dateDifferentRate);
                        BigDecimal totalSever;
                        if (yearDifferent.compareTo(periodWithoutPay) >= 0) {
                            totalSever = (dailyCalculation.compareTo(severanceMaxAmount)) > 0 ? severanceMaxAmount
                                : dailyCalculation;
                        } else {
                            totalSever = BigDecimal.ZERO;
                        }
                        BigDecimal sevrnMaxAmountPre = this.dividendParameterVO.getSevrnMaxAmountPre();
                        BigDecimal severPref
                            = totalSever.compareTo(sevrnMaxAmountPre) > 0 ? sevrnMaxAmountPre : totalSever;
                        BigDecimal severOrdi = totalSever.subtract(severPref);
                        this.wilonAndSeverancePay.setTotalSever(totalSever);
                        this.wilonAndSeverancePay.setSeverPref(severPref);
                        this.wilonAndSeverancePay.setSeverOrdi(severOrdi);
                    }
                }
            }
        }
        log.info("fillWILONAndSeveranceData() - end");
    }

    public void reportReady() {
        log.info("reportReady() start");
        log.info("reportReady() end");
    }
}
