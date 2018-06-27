package hk.oro.iefas.domain.shroff.vo;

import java.math.BigDecimal;
import java.util.Date;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3136 $ $Date: 2018-06-14 10:27:58 +0800 (週四, 14 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TransferToGrOrBeaVO extends TxnVO {

    private Integer transferId;
    private String transferNo;
    private TransferAmountTypeVO transferType;
    private Date cutOffDate;
    private Date processDate;
    private String status;
    private BigDecimal totalAmount;
    private VoucherVO voucher;
}
