package hk.oro.iefas.domain.core.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version $Revision: 1079 $ $Date: 2018-02-07 18:02:00 +0800 (週三, 07 二月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TxnVO extends BaseVO {

    private Long txnKeyRef;
}
