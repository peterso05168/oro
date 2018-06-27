/**
 * 
 */
package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.TransferToGrOrBeaItemDTO;
import hk.oro.iefas.domain.shroff.entity.ShrTxfGrBeaItem;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 3189 $ $Date: 2018-06-19 13:34:32 +0800 (週二, 19 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class TransferToGrOrBeaItemDTOAssembler extends AssemblerSupport<TransferToGrOrBeaItemDTO, ShrTxfGrBeaItem> {

    @Override
    public TransferToGrOrBeaItemDTO toDTO(ShrTxfGrBeaItem entity) {
        if (entity != null) {
            TransferToGrOrBeaItemDTO dto = DataUtils.copyProperties(entity, TransferToGrOrBeaItemDTO.class);
            return dto;
        }
        return null;
    }

}
