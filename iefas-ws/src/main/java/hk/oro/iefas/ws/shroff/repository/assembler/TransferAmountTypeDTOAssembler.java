package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.TransferAmountTypeDTO;
import hk.oro.iefas.domain.shroff.entity.ShrTxfAmountType;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * @version $Revision: 3122 $ $Date: 2018-06-13 17:34:56 +0800 (週三, 13 六月 2018) $
 * @author $Author: dante.fang $
 */
@Component
public class TransferAmountTypeDTOAssembler extends AssemblerSupport<TransferAmountTypeDTO, ShrTxfAmountType> {

    @Override
    public TransferAmountTypeDTO toDTO(ShrTxfAmountType entity) {
        if (entity != null) {
            return DataUtils.copyProperties(entity, TransferAmountTypeDTO.class);
        }
        return null;
    }

}
