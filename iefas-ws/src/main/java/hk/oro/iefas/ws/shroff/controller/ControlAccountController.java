package hk.oro.iefas.ws.shroff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.domain.shroff.dto.ControlAccountDTO;
import hk.oro.iefas.domain.shroff.dto.ControlAccountResultDTO;
import hk.oro.iefas.domain.shroff.dto.SearchControlAccountDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.shroff.service.ControlAccountService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@RestController
@Slf4j
@RequestMapping(RequestUriConstant.URI_ROOT_CONTROL_ACCOUNT)
public class ControlAccountController {

    @Autowired
    private ControlAccountService controlAccountService;

    @GetMapping(RequestUriConstant.URI_CONTROL_ACCOUNT_FIND_ALL_CONTROL_ACCOUNT)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<ControlAccountDTO> findAllControlAccounts() {
        log.info("findAllControlAccounts() start - ");
        List<ControlAccountDTO> controlAccounts = this.controlAccountService.findAllControlAccounts();
        log.info("findAllControlAccounts() end - ");
        return controlAccounts;
    }

    @PostMapping(RequestUriConstant.URI_CONTROL_ACCOUNT_SEARCH_CONTROL_ACCOUNT)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<ControlAccountResultDTO>
        searchControlAccountList(@RequestBody SearchDTO<SearchControlAccountDTO> criteria) {
        log.info("searchControlAccountList() start - criteria : " + criteria);
        Page<ControlAccountResultDTO> result = controlAccountService.searchControlAccountResultList(criteria);
        log.info("searchControlAccountList end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_CONTROL_ACCOUNT_GET_CONTROL_ACCOUNT_DETAIL)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public ControlAccountDTO getControlAccountDetail(@RequestBody Integer ctlAcId) {
        log.info("getControlAccountDetail() start - ctlAcId : " + ctlAcId);
        ControlAccountDTO result;
        result = controlAccountService.getControlAccountDetail(ctlAcId);
        log.info("getControlAccountDetail() end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_CONTROL_ACCOUNT_SAVE_CONTROL_ACCOUNT)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SAVE)
    public Integer saveControlAccount(@RequestBody ControlAccountDTO controlAccountDTO) {
        log.info("saveControlAccount() start - controlAccountDTO : " + controlAccountDTO);
        Integer result = controlAccountService.saveControlAccount(controlAccountDTO);
        log.info("saveControlAccount end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_CONTROL_ACCOUNT_EXISTS_BY_CONTROL_ACCOUNT_NAME)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Boolean existsByControlAccountName(@RequestBody SearchControlAccountDTO criteria) {
        log.info("existsByControlAccountName start - criteria: " + criteria);
        Boolean result = controlAccountService.existsByControlAccountName(criteria);
        log.info("existsByControlAccountName end - " + result);
        return result;
    }

    @PostMapping(RequestUriConstant.URI_CONTROL_ACCOUNT_EXISTS_BY_CONTROL_ACCOUNT_CODE)
    @ModuleLog(module = ModuleLogConstant.MODULE_SHROFF, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Boolean existsByControlAccountCode(@RequestBody SearchControlAccountDTO criteria) {
        log.info("existsByControlAccountCode start - criteria: " + criteria);
        Boolean result = controlAccountService.existsByControlAccountCode(criteria);
        log.info("existsByControlAccountCode end - " + result);
        return result;
    }
}
