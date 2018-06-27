package hk.oro.iefas.ws.casemgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.DepositCardDTO;
import hk.oro.iefas.domain.casemgt.entity.CaseDepositCard;
import hk.oro.iefas.ws.casemgt.repository.DepositCardRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.DepositCardDTOAssembler;
import hk.oro.iefas.ws.casemgt.service.DepositCardService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2120 $ $Date: 2018-04-19 10:25:23 +0800 (週四, 19 四月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@Service
public class DepositCardServiceImpl implements DepositCardService {

    @Autowired
    private DepositCardRepository depositCardRepository;

    @Autowired
    private DepositCardDTOAssembler depositCardDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public DepositCardDTO findOne(Integer depositCardId) {
        log.info("findOne() start - and depositCardId = " + depositCardId);
        CaseDepositCard depositCard = depositCardRepository.findOne(depositCardId);
        DepositCardDTO result = depositCardDTOAssembler.toDTO(depositCard);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(DepositCardDTO depositCardDTO) {
        log.info("save() start - and DepositCardDTO = " + depositCardDTO);
        CaseDepositCard depositCard = DataUtils.copyProperties(depositCardDTO, CaseDepositCard.class);
        depositCard = depositCardRepository.save(depositCard);
        log.info("save end - and returnVal = " + depositCard.getDepositCardId());
        return depositCard.getDepositCardId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepositCardDTO> findDepositCardByCase(Integer caseId) {
        log.info("findDepositCardByCase() start - and caseId = " + caseId);
        List<CaseDepositCard> list = depositCardRepository.findDepositCardByCase(caseId);
        List<DepositCardDTO> result = depositCardDTOAssembler.toDTOList(list);
        log.info("findDepositCardByCase() end - and returnVal = " + result);
        return result;
    }

}
