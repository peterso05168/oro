package hk.oro.iefas.ws.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.security.entity.Privilege;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public interface PrivilegeRepository extends BaseRepository<Privilege, Integer> {

    @Query("select new hk.oro.iefas.domain.security.dto.PrivilegeDTO(p.privilegeId,p.privilegeName,p.privilegeDesc,p.privilegeType,p.privilegeCode) "
        + "from PostRole pr, RolePrivilege rp, Privilege p where pr.post.postId = ?1 and pr.role = rp.role and rp.privilege = p and rp.status = 'ACT' and pr.status = 'ACT'")
    List<PrivilegeDTO> findByPostId(Integer postId);

    Privilege findByPrivilegeCode(String privilegeCode);
}
