package hk.oro.iefas.domain.dividend.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2945 $ $Date: 2018-06-06 15:38:06 +0800 (週三, 06 六月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DistributedAmountVO {
    private String distribution;
    private BigDecimal amount;
    private BigDecimal charged;
}
