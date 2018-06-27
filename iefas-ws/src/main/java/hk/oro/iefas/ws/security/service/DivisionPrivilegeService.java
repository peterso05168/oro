package hk.oro.iefas.ws.security.service;

import java.util.List;

import hk.oro.iefas.domain.security.dto.DivisionPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeCriteriaDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeResultDTO;

public interface DivisionPrivilegeService {

    List<SearchPrivilegeResultDTO> findByCriteria(SearchPrivilegeCriteriaDTO criteria);

    DivisionPrivilegeDTO findByDivisionAndPrivilege(SearchPrivilegeCriteriaDTO criteria);

    Integer save(DivisionPrivilegeDTO divisionPrivilegeDTO);
}
