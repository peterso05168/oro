package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.shroff.dto.RosterDTO;
import hk.oro.iefas.domain.shroff.entity.ShrRoster;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Component
public class RosterDTOAssembler extends PagingAssemblerSupport<RosterDTO, ShrRoster> {

    @Override
    public RosterDTO toDTO(ShrRoster entity) {
        RosterDTO rosterDTO = null;
        if (entity != null) {
            rosterDTO = DataUtils.copyProperties(entity, RosterDTO.class);
        }
        return rosterDTO;
    }
}
