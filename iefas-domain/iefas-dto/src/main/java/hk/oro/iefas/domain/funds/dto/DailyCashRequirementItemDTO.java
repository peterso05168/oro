package hk.oro.iefas.domain.funds.dto;

import java.math.BigDecimal;

import hk.oro.iefas.domain.core.dto.TxnDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@RequiredArgsConstructor
public class DailyCashRequirementItemDTO extends TxnDTO {

    private Integer dailyCashRequirementItemId;
    private CashRequirementItemTypeDTO cashRequirementItemType;
    private BigDecimal requiredAmount;

}
