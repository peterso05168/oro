package hk.oro.iefas.ws.shroff.repository.assembler;

import org.springframework.stereotype.Component;

import hk.oro.iefas.domain.shroff.dto.RosterResultDTO;
import hk.oro.iefas.domain.shroff.entity.ShrRoster;
import hk.oro.iefas.ws.core.assembler.PagingAssemblerSupport;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Component
public class RosterResultDTOAssembler extends PagingAssemblerSupport<RosterResultDTO, ShrRoster> {
    @Override
    public RosterResultDTO toDTO(ShrRoster entity) {
        RosterResultDTO rosterResultDTO = null;
        if (entity != null) {
            rosterResultDTO = new RosterResultDTO(entity.getRosterId(), entity.getOnDutyDate(), entity.getStatus(),
                entity.getGroupAPost().getPostTitle(), entity.getGroupBPost().getPostTitle());
        }
        return rosterResultDTO;
    }
}
