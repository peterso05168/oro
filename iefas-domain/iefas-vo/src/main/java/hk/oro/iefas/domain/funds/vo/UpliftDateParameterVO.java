package hk.oro.iefas.domain.funds.vo;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UpliftDateParameterVO extends TxnVO {

    private BigDecimal fromValue = BigDecimal.ZERO;
    private BigDecimal toValue = BigDecimal.ZERO;
    private Integer upliftDay = 0;
    private boolean disableAdd;
    private boolean disableDelete;
    private boolean lastColumn = false;
}
