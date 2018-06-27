package hk.oro.iefas.domain.funds.dto;

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
public class CashRequirementItemTypeDTO extends TxnDTO {

    private Integer cashRequirementItemTypeId;
    private String cashRequirementItemTypeName;
    private InvestmentTypeDTO investmentType;
    private String status;
}
