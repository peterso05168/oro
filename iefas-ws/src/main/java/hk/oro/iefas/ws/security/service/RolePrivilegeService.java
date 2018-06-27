package hk.oro.iefas.ws.security.service;

import java.util.List;

import hk.oro.iefas.domain.security.dto.RolePrivilegeDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeCriteriaDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeResultDTO;

public interface RolePrivilegeService {

    RolePrivilegeDTO findOne(Integer rolePrivilegeId);

    Integer save(RolePrivilegeDTO rolePrivilegeDTO);

    List<SearchPrivilegeResultDTO> findByCriteria(SearchPrivilegeCriteriaDTO criteria);

    RolePrivilegeDTO findByRoleAndPrivilege(SearchPrivilegeCriteriaDTO criteria);

}
