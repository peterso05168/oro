package hk.oro.iefas.core.constant;

/**
 * @version $Revision: 3163 $ $Date: 2018-06-15 16:09:30 +0800 (週五, 15 六月 2018) $
 * @author $Author: vicki.huang $
 */
public class DividendConstant {

    public static final String STATUS_CONFIRM = "CON";

    public static final String GROUND_CODE_MAX_VAL = "999";
    public static final String SEQ_CODE = "GROUND_CODE_";

    public static final String WITHHELD_CODE = "WITHHELD_CODE";
    public static final String WITHHELD_CODE_A = "A";
    public static final String WITHHELD_CODE_Z = "Z";

    public static final String GAZETTE_CODE = "GAZETTE_CODE";
    public static final String GAZETTE_CODE_G = "G";

    public static final String GAZETTE_CODE_FORMAT = "%04d";
    public static final String GAZETTE_CODE_MAX_VAL = "9999";

    // =============================Adj Type===================================
    public static final String ADJTYPE_PRE = "Preferential";
    public static final String ADJTYPE_DEF_PRE = "Deferred Preferential";
    public static final String ADJTYPE_DEF_ORD = "Deferred Ordinary";
    public static final String ADJTYPE_ORD = "Ordinary";
    public static final String ADJTYPE_INT = "Interest";
    // =============================Adj Type===================================

    // =============================Nature of Claim============================
    public static final String PREFERENTIAL = "PRE";
    public static final String ORDINARY = "ORD";
    public static final String INTEREST = "INT";
    // =============================Nature of Claim============================

    // =============================preferential adjudication type=============
    public static final String PAJ_TYPE_SAL = "SAL";
    public static final String PAJ_TYPE_HOL = "HOL";
    public static final String PAJ_TYPE_WIL = "WIL";
    public static final String PAJ_TYPE_SEV = "SEV";
    public static final String PAJ_TYPE_OTH = "OTH";
    public static final String PAJ_TYPE_TOT = "TOT";
    // =============================preferential adjudication type=============

    // =============================is reject type =============================
    public static final String IS_REJECT_Y = "Y";
    public static final String IS_REJECT_N = "N";
    // =============================is reject type =============================

    // =============================Case type dividend required=============================
    public static final String DIVIDEND_REQUIRED = "1";
    // =============================Case type dividend required=============================

    public static final String DIVIDEND_TYPE_INTEREST = "INT";
    public static final String DIVIDEND_TYPE_DIVIDEND = "DIV";

    public static final String CASE_TYPE_B = "B";
    public static final String CASE_TYPE_L = "L";

    public static final String DETAIL_OF_CASE_FEE_TYPE_B = "A18";
    public static final String DETAIL_OF_CASE_FEE_TYPE_L = "A7";

    public static final String MINIMUM_FEE_OF_CASE_FEE_TYPE_B = "B11";
    public static final String MINIMUM_FEE_OF_CASE_FEE_TYPE_L = "BIX";

    public static final String CASE_FEE_TYPE_B1 = "B1";
    public static final String CASE_FEE_TYPE_B2 = "B2";
    public static final String CASE_FEE_TYPE_B3 = "B3";
    public static final String CASE_FEE_TYPE_B4 = "B4";
    public static final String CASE_FEE_TYPE_B5 = "B5";
    public static final String CASE_FEE_TYPE_B9 = "B9";
    public static final String CASE_FEE_TYPE_BI = "BI";
    public static final String CASE_FEE_TYPE_BIV1 = "BIV(1)";
    public static final String CASE_FEE_TYPE_BIV2 = "BIV(2)";

    public static final String OR_FEE_COMPUTATION_DATE = "2013-11-01 00:00";

    // FIX for Fixed Amount, RAT for Rate, ADD for Added Value, SLI for Sliding Scale
    public static final String CALCULATION_TYPE_FIX = "FIX";
    public static final String CALCULATION_TYPE_RAT = "RAT";
    public static final String CALCULATION_TYPE_ADD = "ADD";
    public static final String CALCULATION_TYPE_SLI = "SLI";

    // dividend schedule type code
    public static final String SCHEDULE_TYPE_DS = "DS";
    public static final String SCHEDULE_TYPE_ADS = "ADS";
    public static final String SCHEDULE_TYPE_IS = "IS";
    public static final String SCHEDULE_TYPE_AIS = "AIS";

    public static final String NO_ACCOUNT_NUMBER = "No number inputted.";
}
