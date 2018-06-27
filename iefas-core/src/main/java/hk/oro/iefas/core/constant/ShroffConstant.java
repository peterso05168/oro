package hk.oro.iefas.core.constant;

/**
 * @version $Revision: 3210 $ $Date: 2018-06-20 11:29:17 +0800 (週三, 20 六月 2018) $
 * @author $Author: garrett.han $
 */
public class ShroffConstant {

    public static final String DR = "DR";
    public static final String CR = "CR";

    /**
     * Voucher Type Code
     */
    public static final String VT_JOU = "JOU";
    public static final String VT_REC = "REC";
    public static final String VT_PAY = "PAY";

    /**
     * Cheque Type Code
     */
    public static final int CQ_INCOMING = 1;
    public static final int CQ_OUTGOING = 2;
    public static final int CQ_COM_OUTGOING = 3;

    /**
     * Currency
     */
    public static final int CURCY_HKD = 11;

    /**
     * Receipt
     */
    public static final String RECEIPT_CODE = "RECEIPT_SEQ";
    
    /**
     * Deposit
     */
    public static final String DEPOSIT_CODE = "DEPOSIT_SEQ";

    public static final String RECEIPT_NUMBER_PREFIX = "R";
    public static final String DEPOSIT_NUMBER_PREFIX = "D";
    public static final String BASE_NUMBER = "00000";
    
    /**
     * Transfer Amount to BEA / General Revenue
     */
    public static final String TRANSFER_CODE = "TRANSFER_TO_GR_BEA_SEQ_NO";
    
    public static final String TRANSFER_NUMBER_PREFIX = "T";
}
