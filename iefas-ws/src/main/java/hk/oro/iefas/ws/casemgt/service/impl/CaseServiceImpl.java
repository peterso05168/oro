package hk.oro.iefas.ws.casemgt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.casemgt.dto.CaseDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchCaseDetailCriteriaDTO;
import hk.oro.iefas.domain.casemgt.dto.SearchCaseDetailResultDTO;
import hk.oro.iefas.domain.casemgt.entity.Case;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.casemgt.repository.CaseRepository;
import hk.oro.iefas.ws.casemgt.repository.assembler.CaseDTOAssembler;
import hk.oro.iefas.ws.casemgt.repository.predicate.CasePredicate;
import hk.oro.iefas.ws.casemgt.service.CaseService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Override
    @Transactional(readOnly = true)
    public Boolean validateExistsByCaseNo(String caseNo) {
        log.info("validateExistsByCaseNo start - and CaseNo = " + caseNo);
        String caseTypeCode = caseNo.substring(0, caseNo.indexOf("-"));
        String caseSerNo = caseNo.substring(caseNo.indexOf("-") + 1, caseNo.indexOf("/"));
        String caseYear = caseNo.substring(caseNo.indexOf("/") + 1, caseNo.length());
        Boolean result = caseRepository.exists(CasePredicate.findByCaseNo(caseTypeCode, caseSerNo, caseYear));
        log.info("validateExistsByCaseNo end - return: " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SearchCaseDetailResultDTO> findByCriteria(SearchDTO<SearchCaseDetailCriteriaDTO> criteria) {
        log.info("findByCriteria() start - " + criteria);
        Page<Case> page = null;
        Page<SearchCaseDetailResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = caseRepository.findAll(CasePredicate.findByCriteria(criteria.getCriteria()), pageable);
                result = CaseDTOAssembler.toResultDTO(page);
            }
        }
        log.info("findByCriteria() end - " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public CaseDTO findOne(Integer caseId) {
        log.info("findOne() start - and caseId = " + caseId);
        Case caseInfo = caseRepository.findOne(caseId);
        CaseDTO result = CaseDTOAssembler.toDTO(caseInfo);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public CaseDTO findByCaseNumber(CaseNumberDTO caseNumber) {
        log.info("findByCaseNumber start - and caseNumber = " + caseNumber);
        Case caseInfo = caseRepository.findOne(CasePredicate.findByCaseNo(caseNumber.getCaseType(),
            caseNumber.getCaseSequence(), caseNumber.getCaseYear()));
        CaseDTO result = CaseDTOAssembler.toDTO(caseInfo);
        log.info("findByCaseNumber end - return: " + result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public CaseDTO findByWholeCaseNumber(String wholeCaseNumber) {
        log.info("findByWholeCaseNumber start - caseNumber: " + wholeCaseNumber);
        Case caseInfo = caseRepository.findOne(CasePredicate.findByWholeCaseNo(wholeCaseNumber));
        CaseDTO result = CaseDTOAssembler.toDTO(caseInfo);
        log.info("findByWholeCaseNumber end - " + result);
        return result;
    }

}
