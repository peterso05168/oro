/**
 * 
 */
package hk.oro.iefas.web.system.profile.service;

import hk.oro.iefas.domain.organization.vo.UserProfileVO;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public interface UserProfileClientService {
    Integer saveUserProfile(UserProfileVO userProfileVO);

    String getUserNameByPostId(Integer postId);

    Boolean existsByEmailAddress(String emailAddress);
}
