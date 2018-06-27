package hk.oro.iefas.ws.shroff.service;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.RosterDTO;
import hk.oro.iefas.domain.shroff.dto.RosterResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchRosterDTO;

import java.util.Date;

/**
 * @version $Revision: 2745 $ $Date: 2018-05-30 20:19:44 +0800 (週三, 30 五月 2018) $
 * @author $Author: garrett.han $
 */
public interface RosterService {
    Page<RosterResultDTO> searchRosterList(SearchDTO<SearchRosterDTO> criteria);

    RosterDTO getRosterDetail(Integer rosterId);

    Integer saveRoster(RosterDTO rosterDTO);

    Boolean existsByOnDutyDateAndIdNot(SearchRosterDTO criteria);

}
