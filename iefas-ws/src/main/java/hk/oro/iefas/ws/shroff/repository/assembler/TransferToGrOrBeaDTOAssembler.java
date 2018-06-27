package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.TransferToGrOrBeaDTO;
import hk.oro.iefas.domain.shroff.entity.ShrTxfGrBea;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 3080 $ $Date: 2018-06-12 11:12:19 +0800 (週二, 12 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class TransferToGrOrBeaDTOAssembler extends AssemblerSupport<TransferToGrOrBeaDTO, ShrTxfGrBea> {

    @Override
    public TransferToGrOrBeaDTO toDTO(ShrTxfGrBea entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, TransferToGrOrBeaDTO.class);
        }
        return null;
    }

}
