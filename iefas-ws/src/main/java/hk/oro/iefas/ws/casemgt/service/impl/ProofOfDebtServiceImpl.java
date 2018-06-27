package hk.oro.iefas.ws.casemgt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.casemgt.dto.SearchProofOfDebtCriteriaDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchProofOfDebtResultDTO;
import hk.oro.iefas.domain.casemgt.entity.CaseCred;
import hk.oro.iefas.domain.counter.dto.ProofOfDebtDTO;
import hk.oro.iefas.domain.counter.entity.CtrProofDebt;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.casemgt.repository.CaseCredRepository;
import hk.oro.iefas.ws.casemgt.repository.ProofOfDebtRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.ProofOfDebtDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.assembler.SearchProofOfDebtResultDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.predicate.ProofOfDebtPredicate;
import hk.oro.iefas.ws.casemgt.service.ProofOfDebtService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class ProofOfDebtServiceImpl implements ProofOfDebtService {

    @Autowired
    private ProofOfDebtRepository proofOfDebtRepository;

    @Autowired
    private CaseCredRepository caseCredRepository;

    @Autowired
    private ProofOfDebtDTOAssembler proofOfDebtDTOAssembler;

    @Autowired
    private SearchProofOfDebtResultDTOAssembler searchProofOfDebtResultDTOAssembler;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SearchProofOfDebtResultDTO> findByCriteria(SearchDTO<SearchProofOfDebtCriteriaDTO> criteria) {
        log.info("findByCriteria() start - " + criteria);
        Page<CtrProofDebt> page = null;
        Page<SearchProofOfDebtResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = proofOfDebtRepository.findAll(ProofOfDebtPredicate.findByCriteria(criteria.getCriteria()),
                    pageable);
                result = searchProofOfDebtResultDTOAssembler.toResultDTO(page);
            }
        }
        log.info("findByCriteria() end - " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(ProofOfDebtDTO proofOfDebtDTO) {
        log.info("save() start - " + proofOfDebtDTO);
        CtrProofDebt proofDebt = DataUtils.copyProperties(proofOfDebtDTO, CtrProofDebt.class);
        proofDebt = proofOfDebtRepository.save(proofDebt);
        if (proofOfDebtDTO.getProofOfDebtId() == null || proofOfDebtDTO.getProofOfDebtId() == 0) {
            CaseCred creditor = new CaseCred();
            creditor.setCtrProofDebt(proofDebt);
            creditor.setProofNo(proofDebt.getProofNo());
            creditor.setCaseInfo(proofDebt.getCaseInfo());
            caseCredRepository.save(creditor);
        }
        log.info("save() end - Proof Of Debt Id: " + proofDebt.getProofOfDebtId());
        return proofDebt.getProofOfDebtId();
    }

    @Override
    @Transactional(readOnly = true)
    public ProofOfDebtDTO findOne(Integer proofOfDebtId) {
        log.info("findOne() start - and proofOfDebtId = " + proofOfDebtId);
        CtrProofDebt proofOfDebt = proofOfDebtRepository.findOne(proofOfDebtId);
        ProofOfDebtDTO result = proofOfDebtDTOAssembler.toDTO(proofOfDebt);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }
}
