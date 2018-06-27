package hk.oro.iefas.ws.adjudication.service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.adjudication.entity.GroCode;
import hk.oro.iefas.domain.casemgt.entity.CaseType;
import hk.oro.iefas.domain.dividend.dto.GroundCodeDTO;
import hk.oro.iefas.domain.dividend.dto.SearchGroundCodeCriteriaDTO;
import hk.oro.iefas.domain.search.dto.PageDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.adjudication.repository.GroundCodeRepository;
import hk.oro.iefas.ws.adjudication.repository.assembler.GroundCodeDTOAssembler;
import hk.oro.iefas.ws.adjudication.repository.predicate.GroundCodePredicate;
import hk.oro.iefas.ws.adjudication.service.GroundCodeService;
import hk.oro.iefas.ws.casemgt.repository.CaseTypeRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */

@Slf4j
@Service
public class GroundCodeServiceImpl implements GroundCodeService {

    @Autowired
    private CaseTypeRepository caseTypeRepository;

    @Autowired
    private GroundCodeRepository groundCodeRepository;

    @Autowired
    private GroundCodeDTOAssembler groundCodeDTOAssembler;

    @Override
    @Transactional(readOnly = true)
    public Page<GroundCodeDTO> searchGroundCodeList(SearchDTO<SearchGroundCodeCriteriaDTO> criteriaDTO) {
        log.info("searchGroundCodeList start - and criteriaDTO = " + criteriaDTO);
        Page<GroundCodeDTO> result = null;
        if (criteriaDTO != null) {
            Pageable pageable = null;
            PageDTO pageDTO = criteriaDTO.getPage();
            if (pageDTO != null) {
                String sortField = pageDTO.getSortField();
                if (sortField == null) {
                    criteriaDTO.getPage().setSortField("groundCodeCode");
                }
                pageable = criteriaDTO.getPage().toPageable();
            }

            if (criteriaDTO.getCriteria() != null) {
                Page<GroCode> page = groundCodeRepository
                    .findAll(GroundCodePredicate.findByParam(criteriaDTO.getCriteria()), pageable);
                if (page != null) {
                    result = groundCodeDTOAssembler.toResultDTO(page);
                }
            }
        }
        log.info("searchGroundCodeList end - return" + criteriaDTO);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer saveGroundCode(GroundCodeDTO groundCodeDTO) {
        log.info("insertGroundCode start - and groundCodeDTO =" + groundCodeDTO);
        Integer result = null;
        GroCode groCode = null;
        if (groundCodeDTO != null) {
            Integer groundCodeId = groundCodeDTO.getGroundCodeId();
            if (groundCodeId != null && groundCodeId.intValue() > 0) {
                groCode = groundCodeRepository.findOne(groundCodeId);
                groCode.setGroundCodeDescChi(groundCodeDTO.getGroundCodeDescChi());
                groCode.setGroundCodeDesc(groundCodeDTO.getGroundCodeDesc());
                groCode.setStatus(groundCodeDTO.getStatus());
            } else {
                groCode = DataUtils.copyProperties(groundCodeDTO, GroCode.class);
                CaseType caseType = caseTypeRepository.findOne(groundCodeDTO.getCaseType().getCaseTypeId());
                if (caseType != null) {
                    groCode.setCaseType(caseType);
                }
            }
            result = groundCodeRepository.save(groCode).getGroundCodeId();
        }
        log.info("insertGroundCode end - return" + groundCodeDTO);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public GroundCodeDTO searchGroundCodeById(Integer groundCodeId) {
        log.info("searchGroundCodeById start - and groundCodeId =" + groundCodeId);
        GroundCodeDTO groundCodeDTO = null;
        if (groundCodeId != null && groundCodeId > 0) {
            GroCode groCode = groundCodeRepository.findOne(groundCodeId);
            if (groCode != null) {
                groundCodeDTO = groundCodeDTOAssembler.toDTO(groCode);
            }
        }
        log.info("searchGroundCodeById end - return " + groundCodeDTO);
        return groundCodeDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroundCodeDTO> findAll() {
        log.info("findAll start-");
        List<GroundCodeDTO> list = new ArrayList<GroundCodeDTO>();
        List<GroCode> groCodes = groundCodeRepository.findByStatusIgnoreCase(CoreConstant.STATUS_ACTIVE);
        if (CommonUtils.isNotEmpty(groCodes)) {
            groCodes.forEach(code -> {
                if (code != null) {
                    list.add(DataUtils.copyProperties(code, GroundCodeDTO.class));
                }
            });
        }
        Collections.sort(list);
        log.info("findAll end- return : " + list);
        return list;
    }
}
