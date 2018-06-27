/**
 * 
 */
package hk.oro.iefas.ws.organization.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.organization.dto.DelegatedDTO;
import hk.oro.iefas.domain.organization.entity.UserDelegation;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2214 $ $Date: 2018-04-25 14:05:52 +0800 (週三, 25 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface UserDelegationRepository extends BaseRepository<UserDelegation, Integer> {

    @Query("select new hk.oro.iefas.domain.organization.dto.DelegatedDTO(ac.loginName, ac.userProfile.engName, "
        + "ac.userProfile.post.postTitle, ac.userProfile.post.postId, ac.userProfile.post.division.divisionId) "
        + "from UserDelegation ud, UserAccount ac "
        + "where ud.delegateTo.postId = ?1 and ud.effectiveStartDate <= ?2 and ud.effectiveEndDate >= ?2 "
        + "and ac.userProfile.post.postId = ud.delegateFrom and ud.status = 'ACT'")
    List<DelegatedDTO> findByDelegateTo(Integer postId, Date currentDate);

}
