package hk.oro.iefas.ws.shroff.repository.assembler;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.ControlAccountDTO;
import hk.oro.iefas.domain.shroff.entity.ShrCtlAc;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class ControlAccountDTOAssembler extends PagingAssemblerSupport<ControlAccountDTO, ShrCtlAc> {
    @Override
    public ControlAccountDTO toDTO(ShrCtlAc entity) {
        ControlAccountDTO controlAccountDTO = null;
        if (entity != null) {
            controlAccountDTO = DataUtils.copyProperties(entity, ControlAccountDTO.class);
        }
        return controlAccountDTO;
    }
}
