package hk.oro.iefas.domain.shroff.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2770 $ $Date: 2018-05-31 16:26:26 +0800 (週四, 31 五月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BankTransferItemVO extends TxnVO {

    private Integer bankTransferItemId;
    private Integer batchId;
    private String beneficiaryAc;
    private String beneficiaryRef;
    private String curcyCode;
    private String debitAc;
    private Integer fromAcId;
    private BigDecimal instructionRefNo;
    private BigDecimal itemNo;
    private String originaorRef;
    private Date paymentDate;
    private Integer paymentFileId;
    private String rejectionReason;
    private String status;
    private String toAcBankId;
    private String toAcName;
    private BigDecimal toAcNo;
    private BigDecimal transferAmount;
    private Date transferDate;
    private Integer voucherId;
    private Integer voucherItemId;
    private PaymentTypeVO shrPaymentType;
}
