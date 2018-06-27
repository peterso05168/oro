package hk.oro.iefas.ws.core.constant;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public class FundsConstant {

    public static final int GEN_REQ_MODE_ADD = 1;
    public static final int GEN_REQ_MODE_UPDATE = 2;

    public static final String DEFAULT_CASH_REQUIREMENT_ITEM_TYPE_NAME = "Target";

    public static enum CashRequirementNatureConstant {
        TARGET("Target", "", 1), PAYMENT("Other Payments", "Payment Except Dividend", 2),
        GEN_REV("General Revenue", "Interest Transfer", 3),
        MAT_FUNDS("Matures Deposits", "Deposit Matured & Interest", 4),
        MAT_FUNDS_INT("", "Deposit Matured & Interest", 5), RECEIVE("", "", 6), DIVIDEND("Dividend", "Dividend", 7);

        private String cashRequirementItemType;
        private String avaItemType;
        private Integer nature;

        private CashRequirementNatureConstant(String cashRequirementItemType, String avaItemType, Integer nature) {
            this.cashRequirementItemType = cashRequirementItemType;
            this.avaItemType = avaItemType;
            this.nature = nature;
        }

        public static CashRequirementNatureConstant getCashRequirementNatureConstant(Integer nature) {
            for (CashRequirementNatureConstant natureConstant : CashRequirementNatureConstant.values()) {
                if (nature.equals(natureConstant.getNature())) {
                    return natureConstant;
                }
            }
            return null;
        }

        public String getCashRequirementItemType() {
            return cashRequirementItemType;
        }

        public String getAvaItemType() {
            return avaItemType;
        }

        public Integer getNature() {
            return nature;
        }
    };

    public static enum InvestmentTypeConstant {
        BG1, LG2, LG3
    };
}
