package hk.oro.iefas.ws.dividend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.common.dto.CaseNumberDTO;
import hk.oro.iefas.domain.dividend.dto.CreateWilonAndSeveranceDTO;
import hk.oro.iefas.domain.dividend.dto.SearchWilonAndSeverancePayCriteriaDTO;
import hk.oro.iefas.domain.dividend.dto.WilonAndSeverancePayDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.WilonAndSeveranceService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3087 $ $Date: 2018-06-12 18:02:20 +0800 (週二, 12 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_WILON_AND_SEVERANCE_PAY)
public class WilonAndSeveranceController {

    @Autowired
    private WilonAndSeveranceService wilonAndSeveranceService;

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_SEARCH_WILON_AND_SEVERANCE_PAY_LIST)
    public Page<WilonAndSeverancePayDTO>
        searchWILONAndSeveranceList(@RequestBody SearchDTO<SearchWilonAndSeverancePayCriteriaDTO> criteria) {
        log.info("searchWILONAndSeveranceList() start");
        Page<WilonAndSeverancePayDTO> page = wilonAndSeveranceService.searchWILONAndSeveranceList(criteria);
        log.info("searchWILONAndSeveranceList() end caseWILONAndSeverance - " + page);
        return page;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_SAVE_WILON_AND_SEVERANCE_PAY)
    public Integer saveWILONAndSeverancePay(@RequestBody WilonAndSeverancePayDTO wilonAndSeverancePay) {
        log.info("saveWILONAndSeverancePay() start - and wilonAndSeverancePay = " + wilonAndSeverancePay);
        Integer result = wilonAndSeveranceService.saveWILONAndSeverancePay(wilonAndSeverancePay);
        log.info("saveWILONAndSeverancePay() end and result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_SEARCH_CREDITOR_BY_CASENUMBER)
    public List<CreditorDTO> searchCreditorByCaseNumber(@RequestBody CaseNumberDTO caseNumberDTO) {
        log.info("searchCreditorByCaseNumber() start caseNumberDTO - " + caseNumberDTO);
        List<CreditorDTO> result = wilonAndSeveranceService.searchCreditorByCaseNumber(caseNumberDTO);
        log.info("searchCreditorByCaseNumber() end and result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_SEARCH_BY_WILON_AND_SEVERANCE_ID)
    public WilonAndSeverancePayDTO searchWilonAndSeverance(@RequestBody Integer wilonAndSevrnPayId) {
        log.info("searchWilonAndSeverance() start wilonAndSevrnPayId - " + wilonAndSevrnPayId);
        WilonAndSeverancePayDTO result = wilonAndSeveranceService.searchWILONAndSeverancePayById(wilonAndSevrnPayId);
        log.info("searchWilonAndSeverance() end and result =" + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_CREATE_WILON_AND_SEVERANCE_VALIDATE)
    public boolean createWILONAndSeveranceValidate(@RequestBody CreateWilonAndSeveranceDTO createWILONAndSeverance) {
        log.info("createWILONAndSeveranceValidate() start CreateWILONAndSeveranceDTO - " + createWILONAndSeverance);
        boolean result = wilonAndSeveranceService.createWILONAndSeveranceValidate(createWILONAndSeverance);
        log.info("createWILONAndSeveranceValidate() end and return result = " + result);
        return result;
    }
}
