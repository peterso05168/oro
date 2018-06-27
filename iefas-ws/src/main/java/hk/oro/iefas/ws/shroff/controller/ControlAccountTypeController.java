package hk.oro.iefas.ws.shroff.controller;

import java.util.List;

import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.shroff.dto.ControlAccountTypeDTO;
import hk.oro.iefas.ws.shroff.service.ControlAccountTypeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author $Author: vicki.huang $
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 */
@RestController
@Slf4j
@RequestMapping(RequestUriConstant.URI_ROOT_CONTROL_ACCOUNT_TYPE)
public class ControlAccountTypeController {

    @Autowired
    private ControlAccountTypeService controlAccountTypeService;

    @GetMapping(RequestUriConstant.URI_FIND_ALL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<ControlAccountTypeDTO> finAllAccountTypeList() {
        log.info("finAllAccountTypeList() start");
        List<ControlAccountTypeDTO> result;
        result = controlAccountTypeService.findAll();
        log.info("finAllAccountTypeList() end - " + result);
        return result;
    }
}
