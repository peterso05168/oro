/**
 * 
 */
package hk.oro.iefas.web.system.profile.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.vo.UserProfileVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.system.profile.service.UserProfileClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class UserProfileClientServiceImpl extends BaseClientService implements UserProfileClientService {

    @Override
    public Integer saveUserProfile(UserProfileVO userProfileVO) {
        log.info("saveUserProfile() start " + userProfileVO);
        ResponseEntity<Integer> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_USER_PROFILE_SAVE, userProfileVO, Integer.class);
        Integer body = responseEntity.getBody();
        log.info("saveUserProfile() end User Profile Id: " + body);
        return body;
    }

    @Override
    public String getUserNameByPostId(Integer postId) {
        log.info("getUserNameByPostId() start postId = " + postId);
        ResponseEntity<String> responseEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_USER_NAME_BY_POST, postId, String.class);

        String body = responseEntity.getBody();
        log.info("getUserNameByPostId() end User Name: " + body);
        return body;
    }

    @Override
    public Boolean existsByEmailAddress(String emailAddress) {
        log.info("existsByEmailAddress() start - EmailAddress = " + emailAddress);
        ResponseEntity<Boolean> postForEntity
            = postForEntity(RequestUriConstant.CLIENT_URI_USER_EXIST_EMAIL, emailAddress, Boolean.class);
        Boolean body = postForEntity.getBody();
        log.info("existsByEmailAddress() end - Exist: " + body);
        return body;
    }

}
