package hk.oro.iefas.web.system.divisionadministration.role.service;

import java.util.List;

import hk.oro.iefas.domain.organization.vo.SearchRoleCriteriaVO;
import hk.oro.iefas.domain.organization.vo.SearchRoleResultVO;
import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.security.vo.RoleSummaryVO;
import hk.oro.iefas.domain.security.vo.RoleVO;

public interface RoleClientService {

    RoleVO findOne(Integer roleId);

    Integer save(RoleVO roleVO);

    ResultPageVO<List<SearchRoleResultVO>> findByCriteria(SearchVO<SearchRoleCriteriaVO> criteria);

    Boolean existsByRoleName(String roleName, Integer roleId);

    List<RoleSummaryVO> findByPostId(Integer postId);

    List<SearchRoleResultVO> findByDivisionId(Integer divisionId);
}
