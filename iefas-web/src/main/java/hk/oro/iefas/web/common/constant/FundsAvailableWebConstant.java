package hk.oro.iefas.web.common.constant;

import lombok.Getter;

/**
 * @version $Revision: 3026 $ $Date: 2018-06-11 10:53:19 +0800 (週一, 11 六月 2018) $
 * @author $Author: vicki.huang $
 */
public class FundsAvailableWebConstant {
    public static enum InvestmentTypeConstant {
        BG1("BG1", 1), BB_BA("BB/BA", 2), LG2("LG2", 3), LG3("LG3", 4), LL_LS_LV("LL/LS/LV", 5), EG("EG", 6),
        IVA("IVA", 7);

        @Getter
        private String code;
        @Getter
        private Integer id;

        private InvestmentTypeConstant(String code, Integer id) {
            this.code = code;
            this.id = id;
        }

        public static Integer getIdByCode(String code) {
            for (InvestmentTypeConstant constant : InvestmentTypeConstant.values()) {
                if (constant.getCode().equals(code)) {
                    return constant.getId();
                }
            }
            return null;
        }

        public static String getCodeById(Integer id) {
            for (InvestmentTypeConstant constant : InvestmentTypeConstant.values()) {
                if (constant.getId().equals(id)) {
                    return constant.getCode();
                }
            }
            return null;
        }
    };

    public static enum FundsAvailableItemTypeConstant {
        OPEN_BALANCE("Open Balance", 1), UNCLEARED_CHEQUE("Uncleared Cheque ", 2), PENDING_CHEQUE("Pending Cheque", 3),
        INTRA_ACCOUNT_TRANSFER("Intra-account Transfer", 4),
        FUNDS_TO_BE_RECEIVED_TODAY("Funds to be received today", 5),
        DEPOSIT_MATURED_INTEREST("Deposit Matured & Interest", 6), INTEREST_TRANSFER("Interest Transfer", 7),
        PAYMENTS_EXCEPT_DIVIDEND("Payments Except Dividend", 8), DIVIDEND("Dividend", 9), BUFFER("Buffer", 10),
        PENDING_PAYMENTS("Pending Payments", 11), PLACE_DEPOSIT("Place Deposit", 12);

        @Getter
        private String name;
        @Getter
        private Integer id;

        private FundsAvailableItemTypeConstant(String name, Integer id) {
            this.name = name;
            this.id = id;
        }

        public static Integer getIdByName(String name) {
            for (FundsAvailableItemTypeConstant constant : FundsAvailableItemTypeConstant.values()) {
                if (constant.getName().equals(name)) {
                    return constant.getId();
                }
            }
            return null;
        }

        public static String getNameById(Integer id) {
            for (FundsAvailableItemTypeConstant constant : FundsAvailableItemTypeConstant.values()) {
                if (constant.getId().equals(id)) {
                    return constant.getName();
                }
            }
            return null;
        }
    };
}
