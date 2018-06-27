package hk.oro.iefas.domain.shroff.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptVO extends TxnVO {

    private Integer receiptId;

    private BigDecimal chequeNo;

    private String contactNo;

    private String payerName;

    private BigDecimal paymentTypeId;

    private BigDecimal receiptAmount;

    private String receiptNo;

    private Date receiveDate;

    private String status;
    // loop
    // private List<ReceiveDepositVO> ctrCaseDeposits;

    private VoucherVO shrVcrInfo;
}
