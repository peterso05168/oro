package hk.oro.iefas.web.system.divisionadministration.role.service;

import java.util.List;

import hk.oro.iefas.domain.security.vo.RolePrivilegeVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeResultVO;

public interface RolePrivilegeClientService {

    RolePrivilegeVO findOne(Integer rolePrivilegeId);

    Integer save(RolePrivilegeVO rolePrivilegeVO);

    List<SearchPrivilegeResultVO> findByCriteria(SearchPrivilegeCriteriaVO criteria);

    RolePrivilegeVO findByRoleAndPrivilege(SearchPrivilegeCriteriaVO criteria);
}
