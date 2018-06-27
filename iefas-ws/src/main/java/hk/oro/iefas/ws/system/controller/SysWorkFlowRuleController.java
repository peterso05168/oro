/**
 * 
 */
package hk.oro.iefas.ws.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.common.dto.ActionDTO;
import hk.oro.iefas.domain.system.dto.SysWorkFlowRuleDTO;
import hk.oro.iefas.ws.system.service.SysWorkFlowRuleService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3143 $ $Date: 2018-06-14 18:20:23 +0800 (週四, 14 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_SYS_WORKFLOW_RULE)
public class SysWorkFlowRuleController {

    @Autowired
    private SysWorkFlowRuleService sysWorkFlowRuleService;

    @PostMapping(value = RequestUriConstant.URI_SYS_WORKFLOW_RULE_FINDBY_PRIVILEGE_CODE)
    public List<SysWorkFlowRuleDTO> findByPrivilegeCode(@RequestBody String privilegeCode) {
        log.info("findByPrivilegeCode() start - PrivilegeCode: " + privilegeCode);
        List<SysWorkFlowRuleDTO> dtoList = sysWorkFlowRuleService.findByPrivilegeCode(privilegeCode);
        log.info("findByPrivilegeCode() end - " + dtoList);
        return dtoList;

    }

    @PostMapping(value = RequestUriConstant.URI_SYS_WORKFLOW_RULE_FIND_INIT_ACTION)
    public ActionDTO findIntialAction(@RequestBody String privilegeCode) {
        log.info("findAction() start - " + String.format("[PrivilegeCode: %s]", privilegeCode));
        ActionDTO action = sysWorkFlowRuleService.findIntialAction(privilegeCode);
        log.info("findAction() end - " + action);
        return action;
    }
}
