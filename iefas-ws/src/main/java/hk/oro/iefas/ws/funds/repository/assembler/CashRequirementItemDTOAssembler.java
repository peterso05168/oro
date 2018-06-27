package hk.oro.iefas.ws.funds.repository.assembler;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.funds.dto.CashRequirementItemDTO;
import hk.oro.iefas.domain.funds.entity.CashReqItem;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class CashRequirementItemDTOAssembler {

    public static CashRequirementItemDTO toDTO(CashReqItem cashReqItem) {
        if (cashReqItem != null) {
            CashRequirementItemDTO dto = DataUtils.copyProperties(cashReqItem, CashRequirementItemDTO.class);
            dto.setCashRequirementItemId(cashReqItem.getCashRqmtItemTypeId());
            dto.setCashRequirementItemDesc(cashReqItem.getCashRqmtItemTypeName());
            return dto;
        }
        return null;
    }
}
