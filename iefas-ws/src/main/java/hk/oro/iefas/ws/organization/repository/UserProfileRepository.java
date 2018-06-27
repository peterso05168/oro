/**
 * 
 */
package hk.oro.iefas.ws.organization.repository;

import org.springframework.data.jpa.repository.Query;

import hk.oro.iefas.domain.organization.entity.UserProfile;
import hk.oro.iefas.ws.core.repository.BaseRepository;

/**
 * @version $Revision: 2091 $ $Date: 2018-04-17 15:49:06 +0800 (週二, 17 四月 2018) $
 * @author $Author: dante.fang $
 */
public interface UserProfileRepository extends BaseRepository<UserProfile, Integer> {

    @Query("select u.engName from UserProfile u where u.post.postId = ?1 and u.status = 'ACT'")
    public String findUserNameByPostId(Integer postId);

    boolean existsByEmailAddress(String emailAddress);
}
