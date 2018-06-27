package hk.oro.iefas.domain.shroff.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ChequeFileResultVO {
    private Integer chequeId;
    private Integer itemNumber;
    private Date chequeDate;
    private String voucherNumber;
    private String payee;
    private String currency;
    private BigDecimal amount;
}
