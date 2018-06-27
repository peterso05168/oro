package hk.oro.iefas.ws.funds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.funds.dto.FundsParameterDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.funds.service.FundsParameterService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_FUNDS_OTHER_PARA)
public class FundsParameterController {

    @Autowired
    private FundsParameterService fundsParameterService;

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_LOAD_PARA)
    public List<FundsParameterDTO> loadParameter() {
        log.info("loadParameter() start");
        List<FundsParameterDTO> page = fundsParameterService.loadParameter();
        log.info("loadParameter() end");
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_FUNDS, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_SAVE_PARA)
    public void searchInvestmentInstruction(@RequestBody List<FundsParameterDTO> fundsParameterList) {
        log.info("updateParameter() start - fundsParameterList: " + fundsParameterList);
        fundsParameterService.updateParameter(fundsParameterList);
        log.info("updateParameter() end");
    }
}
