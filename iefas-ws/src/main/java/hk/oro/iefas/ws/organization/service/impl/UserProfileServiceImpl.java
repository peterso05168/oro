/**
 * 
 */
package hk.oro.iefas.ws.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.organization.dto.UserProfileDTO;
import hk.oro.iefas.domain.organization.entity.UserProfile;
import hk.oro.iefas.ws.organization.repository.UserProfileRepository;
import hk.oro.iefas.ws.organization.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveUserProfile(UserProfileDTO userProfileDTO) {
        log.info("saveUserProfile() start - " + userProfileDTO);
        UserProfile userProfile = DataUtils.copyProperties(userProfileDTO, UserProfile.class);
        userProfile = userProfileRepository.save(userProfile);
        log.info("save() end - User Id: " + userProfile.getUserProfileId());
        return userProfile.getUserProfileId();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public String getUserNameByPostId(Integer postId) {
        log.info("getUserNameByPostId() start - postId = " + postId);
        String userName = userProfileRepository.findUserNameByPostId(postId);
        log.info("getUserNameByPostId() end - User Name: " + userName);
        return userName;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmailAddress(String emailAddress) {
        log.info("existsByEmailAddress() start - EmailAddress = " + emailAddress);
        boolean isExist = userProfileRepository.existsByEmailAddress(emailAddress);
        log.info("existsByEmailAddress() end - Exist: " + isExist);
        return isExist;
    }

}
