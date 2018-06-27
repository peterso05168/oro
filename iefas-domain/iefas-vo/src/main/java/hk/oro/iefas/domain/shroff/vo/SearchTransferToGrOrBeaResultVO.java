package hk.oro.iefas.domain.shroff.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3174 $ $Date: 2018-06-15 19:54:00 +0800 (週五, 15 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SearchTransferToGrOrBeaResultVO {

    private Integer transferId;
    private String transferNo;
    private String transferType;
    private String accountType;
    private Date processDate;
    private String paymentVoucherNumber;
    private BigDecimal totalAmount;
    private Date cutOffDate;
}
