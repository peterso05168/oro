/**
 * 
 */
package hk.oro.iefas.ws.organization.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.dto.UserProfileDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.organization.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_USER_PROFILE)
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @ModuleLog(module = ModuleLogConstant.MODULE_ORGANIZATION, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_USER_PROFILE_SAVE)
    public Integer saveUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
        log.info("saveUserProfile() start - " + userProfileDTO);
        Integer id = userProfileService.saveUserProfile(userProfileDTO);
        log.info("saveUserProfile() end User Profile Id: " + id);
        return id;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_USER_NAME_BY_POST)
    public String getUserNameByPostId(@RequestBody Integer postId) {
        log.info("getUserNameByPostId() start - postId = " + postId);
        String name = userProfileService.getUserNameByPostId(postId);
        log.info("getUserNameByPostId() end User Name: " + name);
        return name;
    }

    @PostMapping(value = RequestUriConstant.URI_USER_EXIST_EMAIL)
    public Boolean existsByEmailAddress(@RequestBody String emailAddress) {
        log.info("existsByEmailAddress() start - EmailAddress = " + emailAddress);
        boolean isExist = userProfileService.existsByEmailAddress(emailAddress);
        log.info("existsByEmailAddress() end - Exist: " + isExist);
        return isExist;
    }
}
