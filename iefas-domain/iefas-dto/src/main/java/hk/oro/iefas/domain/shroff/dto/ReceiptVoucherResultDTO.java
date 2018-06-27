package hk.oro.iefas.domain.shroff.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @version $Revision$ $Date$
 * @author $Author$
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptVoucherResultDTO {
    private Integer voucherId;
    private String voucherNo;
    private Date voucherDate;
    private String groupCode;
    private BigDecimal voucherTotalAmount;
    private String status;
}
