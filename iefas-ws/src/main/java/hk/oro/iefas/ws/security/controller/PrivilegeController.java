package hk.oro.iefas.ws.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.dto.PrivilegeDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeResultDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.security.service.PrivilegeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_PRIVILEGE)
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @PostMapping(value = RequestUriConstant.URI_PRIVILEGE_FINDBY_POSTID)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public List<PrivilegeDTO> findByPostId(@RequestBody Integer postId) {
        log.info("findByPost() start = PostId: " + postId);
        List<PrivilegeDTO> result = privilegeService.findByPostId(postId);
        log.info("findByPost end - " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_PRIVILEGE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public List<SearchPrivilegeResultDTO> findAll() {
        log.info("searchPrivilegeList() start - ");
        List<SearchPrivilegeResultDTO> result = privilegeService.findAll();
        log.info("searchPrivilegeList() end ");
        return result;
    }

}
