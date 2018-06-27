package hk.oro.iefas.ws.release.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.release.dto.RelHistListItemDTO;
import hk.oro.iefas.domain.release.entity.RelHistListItem;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 2651 $ $Date: 2018-05-28 10:36:35 +0800 (Mon, 28 May 2018) $
 * @author $Author: george.wu $
 */
@Component
public class HistoricalCaseListItemDTOAssembler extends AssemblerSupport<RelHistListItemDTO, RelHistListItem> {

    @Override
    public RelHistListItemDTO toDTO(RelHistListItem entity) {
        if (entity != null) {
        	RelHistListItemDTO histCaseListItemDTO
                = DataUtils.copyProperties(entity, RelHistListItemDTO.class);
            return histCaseListItemDTO;
        }
        return null;
    }
}
