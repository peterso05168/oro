package hk.oro.iefas.ws.shroff.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.RosterDTO;
import hk.oro.iefas.domain.shroff.dto.RosterResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchRosterDTO;
import hk.oro.iefas.domain.shroff.entity.ShrRoster;
import hk.oro.iefas.ws.shroff.repository.RosterRepository;
import hk.oro.iefas.ws.shroff.repository.assembler.RosterDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.assembler.RosterResultDTOAssembler;
import hk.oro.iefas.ws.shroff.repository.predicate.RosterPredicate;
import hk.oro.iefas.ws.shroff.service.RosterService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class RosterServiceImpl implements RosterService {

    @Autowired
    private RosterRepository rosterRepository;

    @Autowired
    private RosterResultDTOAssembler rosterResultDTOAssembler;

    @Autowired
    private RosterDTOAssembler rosterDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public Page<RosterResultDTO> searchRosterList(SearchDTO<SearchRosterDTO> criteria) {
        log.info("searchRosterList() start criteria -" + criteria);
        Page<ShrRoster> page = null;
        Page<RosterResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = rosterRepository.findAll(RosterPredicate.findByCriteria(criteria.getCriteria()), pageable);
                result = rosterResultDTOAssembler.toResultDTO(page);
            }
        }
        log.info("searchRosterList() end");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public RosterDTO getRosterDetail(Integer rosterId) {
        log.info("getRosterDetail() start - startId : " + rosterId);
        RosterDTO rosterDTO = null;
        ShrRoster shrRoster = rosterRepository.findOne(rosterId);
        if (shrRoster != null) {
            rosterDTO = rosterDTOAssembler.toDTO(shrRoster);
        }
        log.info("getRosterDetail() end -" + rosterDTO);
        return rosterDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveRoster(RosterDTO rosterDTO) {
        log.info("saveRoster() start - rosterDTO : " + rosterDTO);
        ShrRoster roster;
        Integer rosterId = null;
        if (rosterDTO != null) {
            roster = DataUtils.copyProperties(rosterDTO, ShrRoster.class);
            roster = rosterRepository.save(roster);
            if (roster != null)
                rosterId = roster.getRosterId();
        }
        log.info("saveRoster() end - " + rosterId);
        return rosterId;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByOnDutyDateAndIdNot(SearchRosterDTO criteria) {
        log.info("existsByOnDutyDate start - criteria: " + criteria);
        Boolean result = false;
        if (criteria != null)
            result
                = rosterRepository.existsByOnDutyDateAndRosterIdNot(criteria.getOnDutyDate(), criteria.getRosterId());
        log.info("existsByOnDutyDate end - " + result);
        return result;
    }

}
