package hk.oro.iefas.ws.security.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.dto.DivisionPrivilegeDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeCriteriaDTO;
import hk.oro.iefas.domain.security.dto.SearchPrivilegeResultDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.security.service.DivisionPrivilegeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1983 $ $Date: 2018-04-10 11:24:47 +0800 (週二, 10 四月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_DIVISION_PRIVILEGE)
public class DivisionPrivilegeController {

    @Autowired
    private DivisionPrivilegeService divisionPrivilegeService;

    @PostMapping(value = RequestUriConstant.URI_DIVISION_PRIVILEGE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public List<SearchPrivilegeResultDTO> findByCriteria(@RequestBody SearchPrivilegeCriteriaDTO criteria) {
        log.info("searchPrivilegeList() start - " + criteria);
        List<SearchPrivilegeResultDTO> result = divisionPrivilegeService.findByCriteria(criteria);
        log.info("searchPrivilegeList() end ");
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_DIVISION_PRIVILEGE_SAVE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    public Integer save(@Valid @RequestBody DivisionPrivilegeDTO divisionPrivilegeDTO) {
        log.info("saveDivisionPrivilege() start - " + divisionPrivilegeDTO);
        Integer divisionPrivilegeId = divisionPrivilegeService.save(divisionPrivilegeDTO);
        log.info("saveDivisionPrivilege() end and divisionId = " + divisionPrivilegeId);
        return divisionPrivilegeId;
    }

    @PostMapping(value = RequestUriConstant.URI_DIVISION_PRIVILEGE_FINDBY_DIVISION)
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = false)
    public DivisionPrivilegeDTO findByDivisionAndPrivilege(@RequestBody SearchPrivilegeCriteriaDTO criteria) {
        log.info("findByDivisionAndPrivilege() start - " + criteria);
        DivisionPrivilegeDTO divisionPrivilege = divisionPrivilegeService.findByDivisionAndPrivilege(criteria);
        log.info("findByDivisionAndPrivilege() end ");
        return divisionPrivilege;
    }
}
