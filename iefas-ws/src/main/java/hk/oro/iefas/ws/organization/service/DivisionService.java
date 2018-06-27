package hk.oro.iefas.ws.organization.service;

import java.util.List;

import hk.oro.iefas.domain.organization.dto.DivisionDTO;
import hk.oro.iefas.domain.organization.dto.SearchDivisionCriteriaDTO;

public interface DivisionService {

    List<DivisionDTO> findAll();

    List<DivisionDTO> findByParentDivisionId(Integer parentDivisionId);

    DivisionDTO findOne(Integer divisionId);

    List<DivisionDTO> findByCriteria(SearchDivisionCriteriaDTO criteria);
}
