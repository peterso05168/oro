package hk.oro.iefas.ws.dividend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.AnalysisCodeDTO;
import hk.oro.iefas.domain.shroff.dto.SearchAnalysisCodeCriteriaDTO;
import hk.oro.iefas.domain.shroff.dto.SearchAnalysisCodeResultDTO;
import hk.oro.iefas.domain.shroff.entity.AnalysisCode;
import hk.oro.iefas.ws.dividend.repository.AnalysisCodeRepository;
import hk.oro.iefas.ws.dividend.repository.assembler.AnalysisCodeDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.assembler.SearchAnalysisCodeResultDTOAssembler;
import hk.oro.iefas.ws.dividend.repository.predicate.AnalysisCodePredicate;
import hk.oro.iefas.ws.dividend.service.AnalysisCodeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3060 $ $Date: 2018-06-11 21:26:02 +0800 (週一, 11 六月 2018) $
 * @author $Author: george.wu $
 */
@Slf4j
@Service
public class AnalysisCodeServiceImpl implements AnalysisCodeService {

    @Autowired
    private AnalysisCodeRepository analysisCodeRepository;

    @Autowired
    private AnalysisCodeDTOAssembler analysisCodeDTOAssembler;

    @Autowired
    private SearchAnalysisCodeResultDTOAssembler searchAnalysisCodeResultDTOAssembler;

    @Transactional(readOnly = true)
    @Override
    public List<AnalysisCodeDTO> findByAnalysisCode(String analysisCode) {
        log.info("findByAnalysisCode() start - AnalysisCode: " + analysisCode);
        List<AnalysisCode> analysisCodes = analysisCodeRepository.findByAnalysisCodeContaining(analysisCode);
        List<AnalysisCodeDTO> dtoList = analysisCodeDTOAssembler.toDTOList(analysisCodes);
        log.info("findByAnalysisCode() end - " + dtoList);
        return dtoList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer save(AnalysisCodeDTO analysisCodeDTO) {
        log.info("saveAnalysisCode() start - AnalysisCodeDTO: " + analysisCodeDTO);
        AnalysisCode code = DataUtils.copyProperties(analysisCodeDTO, AnalysisCode.class);
        code = analysisCodeRepository.save(code);
        log.info("saveAnalysisCode() end - AnalysisCodeId: " + code.getAnalysisCodeId());
        return code.getAnalysisCodeId();
    }

    @Transactional(readOnly = true)
    @Override
    public AnalysisCodeDTO findOne(Integer analysisCodeId) {
        log.info("findOne() start - and analysisCodeId = " + analysisCodeId);
        AnalysisCode code = analysisCodeRepository.findOne(analysisCodeId);
        AnalysisCodeDTO result = analysisCodeDTOAssembler.toDTO(code);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Page<SearchAnalysisCodeResultDTO> findByCriteria(SearchDTO<SearchAnalysisCodeCriteriaDTO> criteria) {
        log.info("findByCriteria() start - " + criteria);
        Page<AnalysisCode> page = null;
        Page<SearchAnalysisCodeResultDTO> result = null;
        if (criteria != null) {
            Pageable pageable = null;
            if (criteria.getPage() != null) {
                pageable = criteria.getPage().toPageable();
            }
            if (criteria.getCriteria() != null) {
                page = analysisCodeRepository.findAll(AnalysisCodePredicate.findByCriteria(criteria.getCriteria()),
                    pageable);
                if (page != null) {
                    result = searchAnalysisCodeResultDTOAssembler.toResultDTO(page);
                }
            }
        }
        return result;
    }
    
    public Boolean isAnalysisCodeExistedByCriteria(SearchAnalysisCodeCriteriaDTO criteria) {
        log.info("isAnalysisCodeExistedByCriteria start - criteria: " + criteria);
        Boolean isExisted = false;
        
        AnalysisCode analysisCode = this.analysisCodeRepository.findOne(AnalysisCodePredicate.findByCriteria(criteria));
        if(analysisCode != null) {
            isExisted = true;
        }
        
        log.info("isAnalysisCodeExistedByCriteria end - isExisted: " + isExisted);
        return isExisted;
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean existsByAnalysisCode(SearchAnalysisCodeCriteriaDTO criteria) {
        log.info("existsByAnalysisCode() start - criteria = " + criteria);
        Boolean result = analysisCodeRepository.existsByAnalysisCodeAndAnalysisCodeIdNot(criteria.getAnalysisCode(),
            criteria.getAnalysisCodeId());
        log.info("existsByAnalysisCode() end - result = " + result);
        return result;
    }
}
