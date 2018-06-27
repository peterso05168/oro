/**
 * 
 */
package hk.oro.iefas.ws.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.security.dto.SearchUserCriteriaDTO;
import hk.oro.iefas.domain.security.dto.SearchUserResultDTO;
import hk.oro.iefas.domain.security.dto.UserAccountDTO;
import hk.oro.iefas.domain.security.dto.UserDetailDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.security.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_USER)
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping(value = RequestUriConstant.URI_USER_FINDBY_LOGINNAME)
    public UserAccountDTO findByLoginName(@RequestBody String loginName) {
        log.info("findByLoginName() start - Login Name: " + loginName);
        UserAccountDTO userAccountDTO = userAccountService.findByLoginName(loginName);
        log.info("findByLoginName() end - " + userAccountDTO);
        return userAccountDTO;
    }

    @PostMapping(value = RequestUriConstant.URI_USER_DETAIL)
    public UserDetailDTO findUserDetail(@RequestBody String loginName) {
        log.info("findUserDetail() start - Login Name: " + loginName);
        UserDetailDTO userDetailDTO = userAccountService.findUserDetail(loginName);
        log.info("findUserDetail() end - " + userDetailDTO);
        return userDetailDTO;
    }

    @PostMapping(value = RequestUriConstant.URI_USER_CLEARLOCK)
    public void clearLock(@RequestBody String loginName) {
        log.info("clearLock() start - Login Name: " + loginName);
        userAccountService.clearLock(loginName);
        log.info("clearLock() end");
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SECURITY, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_SAVE)
    public Integer save(@RequestBody UserAccountDTO userAccountDTO) {
        log.info("save() start - " + userAccountDTO);
        Integer id = userAccountService.save(userAccountDTO);
        log.info("save() end");
        return id;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SECURITY, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_USER_FINDBY_CRITERIA)
    public Page<SearchUserResultDTO> searchUserList(@RequestBody SearchDTO<SearchUserCriteriaDTO> searchDTO) {
        log.info("searchUserList() start - " + searchDTO);
        Page<SearchUserResultDTO> page = userAccountService.searchUserList(searchDTO);
        log.info("searchUserList() end - " + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SECURITY, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_USER_INSERT)
    public Integer insertUserDetail(@RequestBody UserAccountDTO userAccountDTO) {
        log.info("save() start - " + userAccountDTO);
        Integer id = userAccountService.insertUserDetail(userAccountDTO);
        log.info("save() end");
        return id;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SECURITY, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_USER_UPDATE)
    public Integer updateUserDetail(@RequestBody UserAccountDTO userAccountDTO) {
        log.info("save() start - " + userAccountDTO);
        Integer id = userAccountService.updateUserDetail(userAccountDTO);
        log.info("save() end");
        return id;
    }

    @PostMapping(value = RequestUriConstant.URI_FIND_ONE)
    public UserAccountDTO findOne(@RequestBody Integer id) {
        log.info("save() start - Id: " + id);
        UserAccountDTO userAccountDTO = userAccountService.findOne(id);
        log.info("save() end - " + userAccountDTO);
        return userAccountDTO;
    }

}
