package hk.oro.iefas.ws.security.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.dto.RolePrivilegeDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeCriteriaDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeResultDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.security.service.RolePrivilegeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2174 $ $Date: 2018-04-23 17:52:58 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_ROLE_PRIVILEGE)
public class RolePrivilegeController {

    @Autowired
    private RolePrivilegeService rolePrivilegeService;

    @PostMapping(value = RequestUriConstant.URI_ROLE_PRIVILEGE_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    public Integer save(@Valid @RequestBody RolePrivilegeDTO rolePrivilegeDTO) {
        log.info("saveRolePrivilege() start - " + rolePrivilegeDTO);
        Integer rolePrivilegeId = rolePrivilegeService.save(rolePrivilegeDTO);
        log.info("saveRolePrivilege() end and roleId = " + rolePrivilegeId);
        return rolePrivilegeId;
    }

    @PostMapping(value = RequestUriConstant.URI_ROLE_PRIVILEGE_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public RolePrivilegeDTO findOne(@RequestBody Integer rolePrivilegeId) {
        log.info("findOne() start - " + rolePrivilegeId);
        RolePrivilegeDTO rolePrivilege = rolePrivilegeService.findOne(rolePrivilegeId);
        log.info("findOne() end ");
        return rolePrivilege;
    }

    @PostMapping(value = RequestUriConstant.URI_ROLE_PRIVILEGE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public List<SearchPrivilegeResultDTO> findByCriteria(@RequestBody SearchPrivilegeCriteriaDTO criteria) {
        log.info("searchRolePrivilegeList() start - " + criteria);
        List<SearchPrivilegeResultDTO> result = rolePrivilegeService.findByCriteria(criteria);
        log.info("searchRolePrivilegeList() end ");
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_ROLE_PRIVILEGE_FINDBY_ROLE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public RolePrivilegeDTO findByRoleAndPrivilege(@RequestBody SearchPrivilegeCriteriaDTO criteria) {
        log.info("findByRoleAndPrivilege() start - " + criteria);
        RolePrivilegeDTO rolePrivilege = rolePrivilegeService.findByRoleAndPrivilege(criteria);
        log.info("findByRoleAndPrivilege() end ");
        return rolePrivilege;
    }
}
