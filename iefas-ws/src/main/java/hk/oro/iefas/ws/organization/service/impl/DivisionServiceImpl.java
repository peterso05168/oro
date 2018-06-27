package hk.oro.iefas.ws.organization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.domain.organization.dto.DivisionDTO;
import hk.oro.iefas.domain.organization.dto.SearchDivisionCriteriaDTO;
import hk.oro.iefas.domain.organization.entity.Division;
import hk.oro.iefas.ws.organization.repository.DivisionRepository;
import hk.oro.iefas.ws.organization.repository.assembler.DivisionDTOAssembler;
import hk.oro.iefas.ws.organization.service.DivisionService;
import hk.oro.iefas.ws.security.repository.predicate.DivisionPredicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    public List<DivisionDTO> findAll() {
        log.info("findAll() start - ");
        List<Division> list = divisionRepository.findAll();
        List<DivisionDTO> result = DivisionDTOAssembler.toDTOList(list);
        log.info("findAll end - and returnVal = " + result);
        return result;
    }

    @Override
    public List<DivisionDTO> findByParentDivisionId(Integer parentDivisionId) {
        log.info("findByParentDivisionId() start - and parentDivisionId = " + parentDivisionId);
        List<Division> list = divisionRepository.findDivisionByParentDivisionId(parentDivisionId);
        List<DivisionDTO> result = DivisionDTOAssembler.toDTOList(list);
        log.info("findByParentDivisionId end - and returnVal = " + result);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public DivisionDTO findOne(Integer divisionId) {
        log.info("findOne() start - and divisionId = " + divisionId);
        Division division = divisionRepository.findOne(divisionId);
        DivisionDTO result = DivisionDTOAssembler.toDTO(division);
        log.info("findOne end - and returnVal = " + result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<DivisionDTO> findByCriteria(SearchDivisionCriteriaDTO criteria) {
        log.info("findByCriteria() start - and criteria = " + criteria);
        List<DivisionDTO> result
            = divisionRepository.findAll(DivisionDTOAssembler.toDTO(), DivisionPredicate.findByCriteria(criteria));
        log.info("findByCriteria end - and returnVal = " + result);
        return result;
    }

}
