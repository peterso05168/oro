package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.ControlAccountDTO;
import hk.oro.iefas.domain.shroff.dto.GeneralLedgerDTO;
import hk.oro.iefas.domain.shroff.entity.ShrGeneralLedger;
import hk.oro.iefas.ws.core.assembler.AssemblerSupport;

/**
 * 
 * @version $Revision$ $Date$
 * @author $Author$
 */
@Component
public class GeneralLedgerDTOAssembler extends AssemblerSupport<GeneralLedgerDTO, ShrGeneralLedger> {

    @Override
    public GeneralLedgerDTO toDTO(ShrGeneralLedger entity) {
        GeneralLedgerDTO generalLedgerDTO = null;
        if (entity != null) {
            generalLedgerDTO = DataUtils.copyProperties(entity, GeneralLedgerDTO.class);
        }
        return generalLedgerDTO;
    }

}
