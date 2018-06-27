package hk.oro.iefas.core.constant;

import java.nio.charset.Charset;

import lombok.Getter;

/**
 * @version $Revision: 3170 $ $Date: 2018-06-15 19:34:19 +0800 (週五, 15 六月 2018) $
 * @author $Author: noah.liang $
 */
public class CoreConstant {
    public static final String STATUS_ACTIVE = "ACT";
    public static final String STATUS_INACTIVE = "INA";
    public static final String STATUS_SUSPEND = "SSP";
    public static final String STATUS_DELETE = "DEL";
    public static final String STATUS_OPTION = "Y";

    public static final String LOGIN_RESULT_SUCCESS = "S";
    public static final String LOGIN_RESULT_FAILURE = "F";

    public static final String DEFAULT_DATE_PATTERN = "YYYYMMDD HH:mm";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static final String CSV_FILE_EXTENDS = ".csv";
    public static final String CSV_LINE_END = "\r\n";
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Charset BIG5 = Charset.forName("Big5");
    public static final Charset ISO_8859_1 = Charset.forName("ISO8859-1");

    public static final String VOUCHER_STATUS_DRAFT = "DRT";
    public static final String VOUCHER_STATUS_SUBMITTED = "SUB";
    public static final String VOUCHER_STATUS_REJECTED = "REJ";
    public static final String VOUCHER_STATUS_SUBMITTED_FOR_2ND_APPROVAL = "SSA";
    public static final String VOUCHER_STATUS_APPROVED = "APR";
    public static final String VOUCHER_STATUS_VERIFIED = "VER";
    public static final String VOUCHER_STATUS_REVERSED = "REV";
    public static final String VOUCHER_STATUS_DELETED = "DEL";
    public static final String VOUCHER_STATUS_PENDING_FOR_PAYMENT = "PP";
    public static final String VOUCHER_STATUS_PAYMENT_RECEIVED = "PR";
    public static final String VOUCHER_STATUS_PENDING_FOR_SUBMIT = "PS";

    public static final String CHEQUE_STATUS_PENDING = "PD";
    public static final String CHEQUE_STATUS_ONHOLD = "OHD";
    public static final String CHEQUE_STATUS_REJECTED = "REJ";
    public static final String CHEQUE_STATUS_DELETED = "DEL";
    public static final String CHEQUE_STATUS_RECEIVED = "RCD";
    public static final String CHEQUE_STATUS_REVERSED = "REV";
    public static final String CHEQUE_STATUS_CLAIMED = "CLM";
    public static final String CHEQUE_STATUS_GENERATED = "GEN";
    public static final String CHEQUE_STATUS_COMBINED = "CMB";
    public static final String CHEQUE_STATUS_APPROVED = "APP";
    public static final String CHEQUE_STATUS_SUBMITTED = "SUB";
    public static final String CHEQUE_STATUS_CANCELLED = "CCL";
    public static final String CHEQUE_STATUS_DRAFT = "DRT";
    public static final String CHEQUE_STATUS_ACTIVE = "ACT";
    public static final String CHEQUE_STATUS_PRINTED = "PRT";
    public static final String CHEQUE_STATUS_REISSUED = "ISU";

    public static final String COMMON_STATUS_DRAFT = "DRT";
    public static final String COMMON_STATUS_SUBMITTED = "SUB";
    public static final String COMMON_STATUS_APPROVED = "APP";
    public static final String COMMON_STATUS_REJECTED = "REJ";
    public static final String DEPOSIT_STATUS_PENDING = "PED";
    public static final String DEPOSIT_STATUS_REVERSED = "REV";

    public static final String SCHEDULE_STATUS_CONFIRMED = "CON";

    public static enum CalculationMethod {
        FIX("Fixed Amount"), RAT("Rate"), ADD("Added Value"), SLI("Sliding Scale");

        @Getter
        private String desc;

        private CalculationMethod(String desc) {
            this.desc = desc;
        }
    }
}
