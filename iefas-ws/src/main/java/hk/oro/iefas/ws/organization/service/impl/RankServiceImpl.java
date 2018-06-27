package hk.oro.iefas.ws.organization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.organization.dto.RankDTO;
import hk.oro.iefas.ws.organization.repository.RankRepository;
import hk.oro.iefas.ws.organization.repository.assembler.RankDTOAssembler;
import hk.oro.iefas.ws.organization.service.RankService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2174 $ $Date: 2018-04-23 17:52:58 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
@Slf4j
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankRepository rankRepository;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<RankDTO> findAll() {
        log.info("findAll() start");
        List<RankDTO> result = rankRepository.findAll(RankDTOAssembler.toDTO(), null);
        log.info("findAll() end - " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RankDTO findOne(Integer rankId) {
        log.info("findOne() start and rankId = " + rankId);
        RankDTO result = RankDTOAssembler.toDTO(rankRepository.findOne(rankId));
        log.info("findOne() end - " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RankDTO findByRankName(String rankName) {
        log.info("findByRankName() start and rankId = " + rankName);
        RankDTO result = RankDTOAssembler.toDTO(rankRepository.findByRankName(rankName));
        log.info("findByRankName() end - " + result);
        return result;
    }

}
