package hk.oro.iefas.ws.casemgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.ws.casemgt.service.CreditorService;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_CREDITOR)
public class CreditorController {

    @Autowired
    private CreditorService creditorService;

    @PostMapping(value = RequestUriConstant.URI_FIND_CREDITOR_BY_CASE)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<CreditorDTO> findCreditorByCaseNumber(@RequestBody String caseNumber) {
        log.info("findCreditorByCaseNumber() start - caseId = " + caseNumber);
        List<CreditorDTO> result = creditorService.findCreditorByCaseNumber(caseNumber);
        log.info("findCreditorByCaseNumber() end and result = " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.URI_FIND_CREDITOR_BY_ID)
    @ModuleLog(module = ModuleLogConstant.MODULE_CASE, operation = ModuleLogConstant.OPERATION_SEARCH)
    public CreditorDTO findCreditorById(@RequestBody Integer creditorId) {
        log.info("findCreditorById() start - creditorId = " + creditorId);
        CreditorDTO creditorDTO = creditorService.findCreditorById(creditorId);
        log.info("findCreditorById() end and creditorDTO = " + creditorDTO);
        return creditorDTO;
    }
}
