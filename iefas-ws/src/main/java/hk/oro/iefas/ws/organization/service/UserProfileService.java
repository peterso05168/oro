/**
 * 
 */
package hk.oro.iefas.ws.organization.service;

import hk.oro.iefas.domain.organization.dto.UserProfileDTO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface UserProfileService {

    Integer saveUserProfile(UserProfileDTO userProfileDTO);

    String getUserNameByPostId(Integer postId);

    boolean existsByEmailAddress(String emailAddress);
}
