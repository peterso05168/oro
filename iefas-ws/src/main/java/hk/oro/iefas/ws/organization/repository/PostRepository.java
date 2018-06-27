package hk.oro.iefas.ws.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.organization.entity.Post;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
public interface PostRepository extends BaseRepository<Post, Integer> {

    Boolean existsByPostTitleIgnoreCaseAndPostIdNot(String postTitle, Integer postId);

    @Query("select p from Post p, DivisionAdmin da where da.post.postId = ?1 and da.division = p.division order by p.postTitle")
    List<Post> findByDivisionAdmin(Integer postId);

    @Query("select p from Post p, PostRole pr, RolePrivilege rp where p.division.divisionId = ?1 and p = pr.post and pr.role = rp.role and rp.privilege.privilegeCode = 'VFA'")
    List<Post> getFirstApprover(Integer divisionId);

    @Query("select p from Post p, PostRole pr, RolePrivilege rp where p.division.divisionId = ?1 and p = pr.post and pr.role = rp.role and rp.privilege.privilegeCode = 'VSA'")
    List<Post> getSecondApprover(Integer divisionId);

    @Query("select p from Post p, PostRole pr, RolePrivilege rp where p.division.divisionId = ?1 and p = pr.post and pr.role = rp.role and rp.privilege.privilegeCode = 'PAA'")
    List<Post> getAApprover(Integer divisionId);

    @Query("select p from Post p, PostRole pr, RolePrivilege rp where p.division.divisionId = ?1 and p = pr.post and pr.role = rp.role and rp.privilege.privilegeCode = 'PAB'")
    List<Post> getBApprover(Integer divisionId);
}
