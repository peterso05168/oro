package hk.oro.iefas.ws.casemgt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.counter.dto.ProofOfDebtItemDTO;
import hk.oro.iefas.domain.counter.entity.CtrProofDebtItem;
import hk.oro.iefas.ws.casemgt.repository.ProofOfDebtItemRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.ProofOfDebtItemDTOAssembler;
import hk.oro.iefas.ws.casemgt.service.ProofOfDebtItemService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class ProofOfDebtItemServiceImpl implements ProofOfDebtItemService {

    @Autowired
    private ProofOfDebtItemRepository proofOfDebtItemRepository;

    @Autowired
    private ProofOfDebtItemDTOAssembler proofOfDebtItemDTOAssembler;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(ProofOfDebtItemDTO proofOfDebtItemDTO) {
        log.info("save() start - " + proofOfDebtItemDTO);
        CtrProofDebtItem proofDebtItem = DataUtils.copyProperties(proofOfDebtItemDTO, CtrProofDebtItem.class);
        proofDebtItem = proofOfDebtItemRepository.save(proofDebtItem);
        log.info("save() end - Proof Of Debt Item Id: " + proofDebtItem.getProofOfDebtItemId());
        return proofDebtItem.getProofOfDebtItemId();
    }

    @Override
    @Transactional(readOnly = true)
    public ProofOfDebtItemDTO findOne(Integer proofOfDebtItemId) {
        log.info("findOne() start - and proofOfDebtItemId = " + proofOfDebtItemId);
        CtrProofDebtItem proofOfDebtItem = proofOfDebtItemRepository.findOne(proofOfDebtItemId);
        ProofOfDebtItemDTO result = proofOfDebtItemDTOAssembler.toDTO(proofOfDebtItem);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }
}
