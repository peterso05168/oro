package hk.oro.iefas.ws.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.organization.dto.SearchRoleResultDTO;
import hk.oro.iefas.domain.security.dto.RoleSummaryDTO;
import hk.oro.iefas.domain.security.entity.Role;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public interface RoleRepository extends BaseRepository<Role, Integer> {

    Boolean existsByRoleNameIgnoreCaseAndRoleIdNot(String roleName, Integer roleId);

    @Query("select new hk.oro.iefas.domain.security.dto.RoleSummaryDTO(r.roleId, r.roleName, r.roleDesc) "
        + "from PostRole pr, Role r where r.roleId = pr.role.roleId and pr.post.postId = ?1")
    List<RoleSummaryDTO> findByPostId(Integer postId);

    @Query("select new hk.oro.iefas.domain.organization.dto.SearchRoleResultDTO(r.roleId, r.division.divisionName, r.roleName, r.roleDesc, r.status) "
        + "from Role r where r.division.divisionId = ?1 and r.status = 'ACT'")
    List<SearchRoleResultDTO> findByDivisionId(Integer divisionId);
}
