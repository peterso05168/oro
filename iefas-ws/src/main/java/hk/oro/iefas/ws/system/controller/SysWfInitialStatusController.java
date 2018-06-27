/**
 * 
 */
package hk.oro.iefas.ws.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.dto.SysWfInitialStatusDTO;
import hk.oro.iefas.ws.system.service.SysWfInitialStatusService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_SYS_WF_INITIAL_STATUS)
public class SysWfInitialStatusController {

    @Autowired
    private SysWfInitialStatusService sysWfInitialStatusService;

    @PostMapping(value = RequestUriConstant.URI_SYS_WF_INITIAL_STATUS_FINDBY_PRIVILEGE_CODE)
    public SysWfInitialStatusDTO findByPrivilegeCode(@RequestBody String privilegeCode) {
        log.info("findByPrivilegeId() start - PrivilegeCode: " + privilegeCode);
        SysWfInitialStatusDTO sysWfInitialStatusDTO = sysWfInitialStatusService.findByPrivilegeCode(privilegeCode);
        log.info("findByPrivilegeId() end - " + sysWfInitialStatusDTO);
        return sysWfInitialStatusDTO;
    }

}
