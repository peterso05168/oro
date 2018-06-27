package hk.oro.iefas.ws.security.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.organization.dto.SearchRoleCriteriaDTO;
import hk.oro.iefas.domain.organization.dto.SearchRoleResultDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.security.dto.RoleDTO;
import hk.oro.iefas.domain.security.dto.RoleSummaryDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_ROLE)
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = RequestUriConstant.URI_ROLE_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    public Integer save(@Valid @RequestBody RoleDTO roleDTO) {
        log.info("saveRoleDetail() start - " + roleDTO);
        Integer roleId = roleService.save(roleDTO);
        log.info("saveRoleDetail() end and roleId = " + roleId);
        return roleId;
    }

    @PostMapping(value = RequestUriConstant.URI_ROLE_VALIDATE)
    public Boolean existsByRoleName(@RequestBody SearchRoleCriteriaDTO criteriaDTO) {
        log.info("existsByRoleName() start - " + criteriaDTO);
        return roleService.existsByRoleName(criteriaDTO.getRoleName(), criteriaDTO.getRoleId());
    }

    @PostMapping(value = RequestUriConstant.URI_ROLE_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public RoleDTO findOne(@RequestBody Integer roleId) {
        log.info("getRoleDetail() start - " + roleId);
        RoleDTO role = roleService.findOne(roleId);
        log.info("getRoleDetail() end ");
        return role;
    }

    @PostMapping(value = RequestUriConstant.URI_ROLE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public Page<SearchRoleResultDTO> findByCriteria(@RequestBody SearchDTO<SearchRoleCriteriaDTO> criteria) {
        log.info("searchRoleList() start - " + criteria);
        Page<SearchRoleResultDTO> roleList = roleService.findByCriteria(criteria);
        log.info("searchRoleList() end ");
        return roleList;
    }

    @PostMapping(value = RequestUriConstant.URI_ROLE_SUMMARY_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public List<RoleSummaryDTO> findByPostId(@RequestBody Integer postId) {
        log.info("findByPostId() start - postId: " + postId);
        List<RoleSummaryDTO> result = roleService.findByPostId(postId);
        log.info("findByPostId() end - " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_ROLE_LIST_BY_DIVISION)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public List<SearchRoleResultDTO> findRoleListByDivision(@RequestBody Integer divisionId) {
        log.info("findRoleListByDivision() start - divisionId: " + divisionId);
        List<SearchRoleResultDTO> result = roleService.findByDivisionId(divisionId);
        log.info("findRoleListByDivision() end - " + result);
        return result;
    }
}
