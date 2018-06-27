package hk.oro.iefas.domain.shroff.vo;

import hk.oro.iefas.domain.core.vo.TxnVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version $Revision: 3119 $ $Date: 2018-06-13 17:28:06 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TransferAmountTypeVO extends TxnVO {

    private Integer txfAmountTypeId;
    private String txfAmountTypeDesc;
    private String txfAmountTypeName;
}
