package hk.oro.iefas.domain.shroff.vo;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 2507 $ $Date: 2018-05-21 14:31:32 +0800 (週一, 21 五月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeVO extends TxnVO {

    private Integer paymentTypeId;
    private String paymentTypeName;
    private String paymentTypeDesc;
    private String status;
}
