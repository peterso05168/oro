/**
 * 
 */
package hk.oro.iefas.ws.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.security.dto.UserPwdHistDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.security.service.UserPwdHistService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_USER_PWD_HIST)
public class UserPwdHistController {

    @Autowired
    private UserPwdHistService userPwdHistService;

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_USER_PWD_HIST_SAVE)
    public Integer saveUserPwdHist(@RequestBody UserPwdHistDTO userPwdHistDTO) {
        log.info("saveUserPwdHist() start - " + userPwdHistDTO);
        Integer userPwdHistId = userPwdHistService.saveUserPwdHist(userPwdHistDTO);
        log.info("saveUserPwdHist() end - User Pwd Hist Id: " + userPwdHistId);
        return userPwdHistId;
    }

    @PostMapping(value = RequestUriConstant.URI_USER_PWD_HIST_FINDBY_USER_AC_ID)
    public List<UserPwdHistDTO> findByUserAcId(@RequestBody SearchDTO<Integer> searchDTO) {
        log.info("findByUserAcId() start - " + searchDTO);
        List<UserPwdHistDTO> result = userPwdHistService.findByUserAcId(searchDTO);
        log.info("findByUserAcId() end - " + result);
        return result;
    }

}
