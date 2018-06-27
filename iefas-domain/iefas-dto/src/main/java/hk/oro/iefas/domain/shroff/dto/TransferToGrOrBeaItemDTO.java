package hk.oro.iefas.domain.shroff.dto;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.dto.TxnDTO;
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
public class TransferToGrOrBeaItemDTO extends TxnDTO {

    private Integer transferItemId;
    private Integer acId;
    private Integer acTypeId;
    private String status;
    private BigDecimal tranAmount;
    private Integer transferId;
}
