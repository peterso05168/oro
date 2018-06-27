package hk.oro.iefas.ws.dividend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.dividend.dto.DividendParameterDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.DividendParameterService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_DIVIDEND_PARAMETER)
public class DividendParameterController {

    @Autowired
    private DividendParameterService dividendParameterService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @GetMapping(value = RequestUriConstant.URI_DIVIDEND_PARAMETER_SEARCH)
    public List<DividendParameterDTO> searchDividendParameter() {
        log.info("searchDividendParameter() start");
        List<DividendParameterDTO> list = dividendParameterService.searchDividendParameter();
        log.info("searchDividendParameter() end - return : " + list);
        return list;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_DIVIDEND_PARAMETER_SAVE)
    public boolean saveDividendParameter(@RequestBody List<DividendParameterDTO> parameterDTOs) {
        log.info("saveDividendParameter() start - " + parameterDTOs);
        boolean bl = dividendParameterService.saveDividendParameter(parameterDTOs);
        log.info("saveDividendParameter() end - " + bl);
        return bl;
    }
}
