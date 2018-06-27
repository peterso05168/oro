package hk.oro.iefas.web.funds.maintenance.fundsparameter.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.funds.vo.FundsParameterVO;
import hk.oro.iefas.domain.funds.vo.UpliftDateParameterVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.funds.maintenance.fundsparameter.service.FundsParameterClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class FundsParameterEditView implements Serializable {

    public static final String BA_DEFAULT_DEPOSIT_LIMIT = "BA_Default_Deposit_Limit";
    public static final String DT_LIST_OF_DAY = "DT_List_of_Day";
    public static final String CR_DEFAULT_PERIOD = "CR_DEFAULT_PERIOD";
    public static final String CR_MAX_PERIOD = "CR_MAX_PERIOD";
    public static final String CR_SURPLUS_THRESHOLD = "CR_SURPLUS_THRESHOLD";
    public static final String II_VOUCHER_PARTICULAR = "II_Voucher_Particular";
    public static final String FA_DAY_OF_PENDING_CHEQUE = "FA_Day_of_pending_cheque";
    public static final String FA_DEFAULT_BUFFER_VALUE = "FA_Default_Buffer_Value";
    public static final String FA_PLACE_DEPOSIT_ROUNDING = "FA_Place_Deposit_Rounding";
    public static final String MISC_INTER_BANK_TRANSFER_CHARGE = "MISC_Inter_Bank_Transfer_Charge";
    public static final String MISC_INTEREST_THRESHOLD = "MISC_INTEREST_THRESHOLD";
    public static final String UPLIFT_DAY_LIMIT_1 = "UPLIFT_DAY_LIMIT_1";
    public static final String UPLIFT_DAY_LIMIT_2 = "UPLIFT_DAY_LIMIT_2";
    public static final String UPLIFT_DAY_LIMIT_3 = "UPLIFT_DAY_LIMIT_3";
    public static final String UPLIFT_DAY_LIMIT_4 = "UPLIFT_DAY_LIMIT_4";
    public static final String UPLIFT_DAY_1 = "UPLIFT_DAY_1";
    public static final String UPLIFT_DAY_2 = "UPLIFT_DAY_2";
    public static final String UPLIFT_DAY_3 = "UPLIFT_DAY_3";
    public static final String UPLIFT_DAY_4 = "UPLIFT_DAY_4";
    public static final String UPLIFT_DAY_5 = "UPLIFT_DAY_5";

    public static final String ACTIVE_BOOLEAN = "1";
    public static final String INACTIVE_BOOLEAN = "0";

    public static final int MAX_SIZE_UPLIFT_DISPLAY = 5;
    public static final BigDecimal MIN_UPLIFT_AMOUNT = new BigDecimal("0");

    public static final BigDecimal MAX_UPLIFT_AMOUNT = new BigDecimal("999999999999");

    private static final long serialVersionUID = 1L;

    @Inject
    private FundsParameterClientService fundsParameterClientService;

    @Getter
    @Setter
    private String defaultDepositLimitValue;

    @Getter
    @Setter
    private String[] depositTargetValue;

    @Getter
    @Setter
    private String cashRequirementDefaultPeriodValue;

    @Getter
    @Setter
    private String cashRequirementMaximumPeriodValue;

    @Getter
    @Setter
    private String surplusThresholdValue;

    @Getter
    @Setter
    private String investmentVoucherParticularValue;

    @Getter
    @Setter
    private String dayOfPendingChequeValue;

    @Getter
    @Setter
    private String defaultBufferValue;

    @Getter
    @Setter
    private String placeDepositRoundingDecimalValue;

    @Getter
    @Setter
    private String interBankTransferChargeValue;

    @Getter
    @Setter
    private String interestThresholdForSeparateInvestmentValue;

    @Getter
    @Setter
    private List<UpliftDateParameterVO> upliftDateParameters;

    @Getter
    @Setter
    private UpliftDateParameterVO upliftDateParameter;

    private List<FundsParameterVO> fundsParameterVOs;

    @PostConstruct
    private void init() {
        log.info("======CurrencyRateEditView init======");
        loadParameter();
    }

    private void loadParameter() {
        fundsParameterVOs = fundsParameterClientService.loadParameter();
        convertBackendValueToDisplay();
    }

    private void convertBackendValueToDisplay() {
        upliftDateParameters = new ArrayList<>();
        for (int i = 0; i < MAX_SIZE_UPLIFT_DISPLAY; i++) {
            upliftDateParameters.add(new UpliftDateParameterVO());
        }
        for (FundsParameterVO fundsParameterVO : fundsParameterVOs) {
            switch (fundsParameterVO.getFundsParameterCode()) {
                case BA_DEFAULT_DEPOSIT_LIMIT:
                    defaultDepositLimitValue = fundsParameterVO.getFundsParameterValue();
                    break;
                case DT_LIST_OF_DAY:
                    if (fundsParameterVO.getFundsParameterValue() != null
                        && !"".equals(fundsParameterVO.getFundsParameterValue().trim())) {
                        depositTargetValue = fundsParameterVO.getFundsParameterValue().split("");
                    }
                    break;
                case CR_DEFAULT_PERIOD:
                    cashRequirementDefaultPeriodValue = fundsParameterVO.getFundsParameterValue();
                    break;
                case CR_MAX_PERIOD:
                    cashRequirementMaximumPeriodValue = fundsParameterVO.getFundsParameterValue();
                    break;
                case CR_SURPLUS_THRESHOLD:
                    surplusThresholdValue = fundsParameterVO.getFundsParameterValue();
                    break;
                case II_VOUCHER_PARTICULAR:
                    investmentVoucherParticularValue = fundsParameterVO.getFundsParameterValue();
                    break;
                case FA_DAY_OF_PENDING_CHEQUE:
                    dayOfPendingChequeValue = fundsParameterVO.getFundsParameterValue();
                    break;
                case FA_DEFAULT_BUFFER_VALUE:
                    defaultBufferValue = fundsParameterVO.getFundsParameterValue();
                    break;
                case FA_PLACE_DEPOSIT_ROUNDING:
                    placeDepositRoundingDecimalValue = fundsParameterVO.getFundsParameterValue();
                    break;
                case MISC_INTER_BANK_TRANSFER_CHARGE:
                    interBankTransferChargeValue = fundsParameterVO.getFundsParameterValue();
                    break;
                case MISC_INTEREST_THRESHOLD:
                    interestThresholdForSeparateInvestmentValue = fundsParameterVO.getFundsParameterValue();
                    break;
                case UPLIFT_DAY_LIMIT_1:
                    upliftDateParameters.get(0).setToValue(new BigDecimal(fundsParameterVO.getFundsParameterValue()));
                    break;
                case UPLIFT_DAY_LIMIT_2:
                    upliftDateParameters.get(1).setToValue(new BigDecimal(fundsParameterVO.getFundsParameterValue()));
                    break;
                case UPLIFT_DAY_LIMIT_3:
                    upliftDateParameters.get(2).setToValue(new BigDecimal(fundsParameterVO.getFundsParameterValue()));
                    break;
                case UPLIFT_DAY_LIMIT_4:
                    upliftDateParameters.get(3).setToValue(new BigDecimal(fundsParameterVO.getFundsParameterValue()));
                    break;
                case UPLIFT_DAY_1:
                    upliftDateParameters.get(0)
                        .setUpliftDay(Integer.parseInt(fundsParameterVO.getFundsParameterValue()));
                    break;
                case UPLIFT_DAY_2:
                    upliftDateParameters.get(1)
                        .setUpliftDay(Integer.parseInt(fundsParameterVO.getFundsParameterValue()));
                    break;
                case UPLIFT_DAY_3:
                    upliftDateParameters.get(2)
                        .setUpliftDay(Integer.parseInt(fundsParameterVO.getFundsParameterValue()));
                    break;
                case UPLIFT_DAY_4:
                    upliftDateParameters.get(3)
                        .setUpliftDay(Integer.parseInt(fundsParameterVO.getFundsParameterValue()));
                    break;
                case UPLIFT_DAY_5:
                    upliftDateParameters.get(4)
                        .setUpliftDay(Integer.parseInt(fundsParameterVO.getFundsParameterValue()));
                    break;
            }
        }
        for (int i = MAX_SIZE_UPLIFT_DISPLAY - 1; i >= 0; i--) {
            if (upliftDateParameters.get(i).getUpliftDay() == 0) {
                upliftDateParameters.remove(i);
            }
        }
        syncFromDateAndToDate(true);
    }

    public void syncFromDateAndToDate(boolean init) {
        if (CommonUtils.isNotEmpty(upliftDateParameters)) {
            boolean cannotAdd = (upliftDateParameters.size() == MAX_SIZE_UPLIFT_DISPLAY);

            upliftDateParameters.get(0).setFromValue(MIN_UPLIFT_AMOUNT);
            upliftDateParameters.get(0).setDisableAdd(cannotAdd);
            upliftDateParameters.get(0).setDisableDelete(true);

            for (int i = 1; i < upliftDateParameters.size(); i++) {
                upliftDateParameters.get(i).setFromValue(upliftDateParameters.get(i - 1).getToValue());
                if (!init) {
                    if (upliftDateParameters.get(i).getFromValue()
                        .compareTo(upliftDateParameters.get(i).getToValue()) > 0) {
                        upliftDateParameters.get(i).setToValue(upliftDateParameters.get(i).getFromValue());
                    }
                }
                upliftDateParameters.get(i).setDisableAdd(cannotAdd);
                upliftDateParameters.get(i).setDisableDelete(false);
            }
            upliftDateParameters.get(upliftDateParameters.size() - 1).setDisableAdd(cannotAdd);
            upliftDateParameters.get(upliftDateParameters.size() - 1).setDisableDelete(false);
            upliftDateParameters.get(upliftDateParameters.size() - 1).setLastColumn(true);
        }
        System.err.println("init parameters=====>" + upliftDateParameters.toString());
    }

    private void convertDisplayValueToBackend() {
        for (FundsParameterVO fundsParameterVO : fundsParameterVOs) {
            switch (fundsParameterVO.getFundsParameterCode()) {
                case BA_DEFAULT_DEPOSIT_LIMIT:
                    fundsParameterVO.setFundsParameterValue(defaultDepositLimitValue);
                    break;
                case DT_LIST_OF_DAY:
                    String tempTarget = "";
                    for (int i = 0; i < depositTargetValue.length; i++) {
                        tempTarget += depositTargetValue[i];
                    }
                    fundsParameterVO.setFundsParameterValue(tempTarget);
                    break;
                case CR_DEFAULT_PERIOD:
                    fundsParameterVO.setFundsParameterValue(cashRequirementDefaultPeriodValue);
                    break;
                case CR_MAX_PERIOD:
                    fundsParameterVO.setFundsParameterValue(cashRequirementMaximumPeriodValue);
                    break;
                case CR_SURPLUS_THRESHOLD:
                    fundsParameterVO.setFundsParameterValue(surplusThresholdValue);
                    break;
                case II_VOUCHER_PARTICULAR:
                    fundsParameterVO.setFundsParameterValue(investmentVoucherParticularValue);
                    break;
                case FA_DAY_OF_PENDING_CHEQUE:
                    fundsParameterVO.setFundsParameterValue(dayOfPendingChequeValue);
                    break;
                case FA_DEFAULT_BUFFER_VALUE:
                    fundsParameterVO.setFundsParameterValue(defaultBufferValue);
                    break;
                case FA_PLACE_DEPOSIT_ROUNDING:
                    fundsParameterVO.setFundsParameterValue(placeDepositRoundingDecimalValue);
                    break;
                case MISC_INTER_BANK_TRANSFER_CHARGE:
                    fundsParameterVO.setFundsParameterValue(interBankTransferChargeValue);
                    break;
                case MISC_INTEREST_THRESHOLD:
                    fundsParameterVO.setFundsParameterValue(interestThresholdForSeparateInvestmentValue);
                    break;
                case UPLIFT_DAY_LIMIT_1:
                    if (upliftDateParameters.size() > 1) {
                        fundsParameterVO.setFundsParameterValue(upliftDateParameters.get(0).getToValue().toString());
                    } else {
                        fundsParameterVO.setFundsParameterValue(String.valueOf(0));
                    }
                    break;
                case UPLIFT_DAY_LIMIT_2:
                    if (upliftDateParameters.size() > 2) {
                        fundsParameterVO.setFundsParameterValue(upliftDateParameters.get(1).getToValue().toString());
                    } else {
                        fundsParameterVO.setFundsParameterValue(String.valueOf(0));
                    }
                    break;
                case UPLIFT_DAY_LIMIT_3:
                    if (upliftDateParameters.size() > 3) {
                        fundsParameterVO.setFundsParameterValue(upliftDateParameters.get(2).getToValue().toString());
                    } else {
                        fundsParameterVO.setFundsParameterValue(String.valueOf(0));
                    }
                    break;
                case UPLIFT_DAY_LIMIT_4:
                    if (upliftDateParameters.size() > 4) {
                        fundsParameterVO.setFundsParameterValue(upliftDateParameters.get(3).getToValue().toString());
                    } else {
                        fundsParameterVO.setFundsParameterValue(String.valueOf(0));
                    }
                    break;
                case UPLIFT_DAY_1:
                    if (upliftDateParameters.size() > 0) {
                        fundsParameterVO.setFundsParameterValue(upliftDateParameters.get(0).getUpliftDay().toString());
                    } else {
                        fundsParameterVO.setFundsParameterValue(String.valueOf(0));
                    }
                    break;
                case UPLIFT_DAY_2:
                    if (upliftDateParameters.size() > 1) {
                        fundsParameterVO.setFundsParameterValue(upliftDateParameters.get(1).getUpliftDay().toString());
                    } else {
                        fundsParameterVO.setFundsParameterValue(String.valueOf(0));
                    }
                    break;
                case UPLIFT_DAY_3:
                    if (upliftDateParameters.size() > 2) {
                        fundsParameterVO.setFundsParameterValue(upliftDateParameters.get(2).getUpliftDay().toString());
                    } else {
                        fundsParameterVO.setFundsParameterValue(String.valueOf(0));
                    }
                    break;
                case UPLIFT_DAY_4:
                    if (upliftDateParameters.size() > 3) {
                        fundsParameterVO.setFundsParameterValue(upliftDateParameters.get(3).getUpliftDay().toString());
                    } else {
                        fundsParameterVO.setFundsParameterValue(String.valueOf(0));
                    }
                    break;
                case UPLIFT_DAY_5:
                    if (upliftDateParameters.size() > 4) {
                        fundsParameterVO.setFundsParameterValue(upliftDateParameters.get(4).getUpliftDay().toString());
                    } else {
                        fundsParameterVO.setFundsParameterValue(String.valueOf(0));
                    }
                    break;
            }
        }
    }

    public void addUpliftParameter() {
        if (upliftDateParameters != null && upliftDateParameters.size() > 0) {
            for (int i = 0; i < upliftDateParameters.size(); i++) {
                if (upliftDateParameters.get(i).equals(this.upliftDateParameter)) {
                    UpliftDateParameterVO newVO = new UpliftDateParameterVO();
                    upliftDateParameters.add(i + 1, newVO);
                }
            }
            syncFromDateAndToDate(false);
        }
    }

    public void deleteUpliftParameter() {
        if (upliftDateParameters != null && upliftDateParameters.size() > 0) {
            if (upliftDateParameters.contains(this.upliftDateParameter)) {
                upliftDateParameters.remove(this.upliftDateParameter);
            }
            syncFromDateAndToDate(false);
        }
    }

    public void syncFromDateAndToDate() {
        syncFromDateAndToDate(false);
    }

    public void updateParameter() {
        log.info("updateParameter() start ");

        convertDisplayValueToBackend();
        fundsParameterClientService.saveParameter(fundsParameterVOs);

        Messages.addGlobalInfo("Save Successfully!");
        loadParameter();
        log.info("updateParameter() end");
    }
}
