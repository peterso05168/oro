package hk.oro.iefas.web.system.divisionadministration.role.service;

import java.util.List;

import hk.oro.iefas.domain.security.vo.DivisionPrivilegeVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeResultVO;

public interface DivisionPrivilegeClientService {

    List<SearchPrivilegeResultVO> findByCriteria(SearchPrivilegeCriteriaVO criteria);

    DivisionPrivilegeVO findByDivisionAndPrivilege(SearchPrivilegeCriteriaVO criteria);

    Integer save(DivisionPrivilegeVO divisionPrivilegeVO);
}
