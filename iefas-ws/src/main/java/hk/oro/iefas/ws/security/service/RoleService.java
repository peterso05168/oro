package hk.oro.iefas.ws.security.service;

import java.util.List;

import org.springframework.data.domain.Page;

import hk.oro.iefas.domain.organization.dto.SearchRoleCriteriaDTO;
import hk.oro.iefas.domain.organization.dto.SearchRoleResultDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.security.dto.RoleDTO;
import hk.oro.iefas.domain.security.dto.RoleSummaryDTO;

public interface RoleService {

    Integer save(RoleDTO roleDTO);

    Page<SearchRoleResultDTO> findByCriteria(SearchDTO<SearchRoleCriteriaDTO> criteria);

    RoleDTO findOne(Integer roleId);

    boolean existsByRoleName(String roleName, Integer roleId);

    List<RoleSummaryDTO> findByPostId(Integer postId);

    List<SearchRoleResultDTO> findByDivisionId(Integer divisionId);
}
