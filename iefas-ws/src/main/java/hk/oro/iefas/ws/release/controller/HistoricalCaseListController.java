package hk.oro.iefas.ws.release.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.release.dto.HistoricalCaseListDetailDTO;
import hk.oro.iefas.domain.release.dto.HistoricalCaseListResultDTO;
import hk.oro.iefas.domain.release.dto.SearchHistoricalCaseListDTO;
import hk.oro.iefas.domain.shroff.dto.ReceiptVoucherDetailDTO;
import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.dto.HolidayResultDTO;
import hk.oro.iefas.domain.system.dto.ImportFileDTO;
import hk.oro.iefas.domain.system.dto.ImportHolidayResultDTO;
import hk.oro.iefas.domain.system.dto.SearchHolidayDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.release.service.HistoricalCaseListService;
import hk.oro.iefas.ws.system.service.HolidayService;
import hk.oro.iefas.ws.system.service.SystemParameterService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2487 $ $Date: 2018-05-18 17:54:15 +0800 (Fri, 18 May 2018) $
 * @author $Author: cwen $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_HISTORICAL_CASE_LIST)
public class HistoricalCaseListController {

    @Autowired
    private HistoricalCaseListService historicalCaseListService;

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_SEARCH_HISTORICAL_CASE_LIST)
    public HistoricalCaseListResultDTO searchHistoricalCaseList(@RequestBody SearchHistoricalCaseListDTO searchDto) {
        log.info("searchHistoricalCaseList() start - " + searchDto);
        HistoricalCaseListResultDTO result = historicalCaseListService.searchHistoricalCaseList(searchDto);
        log.info("searchHistoricalCaseList() end and result = " + result);
        return result;
    }
    
    @PostMapping(value = RequestUriConstant.URI_FIND_HISTORICAL_CASE_LIST_ITEM)
    public HistoricalCaseListDetailDTO findHistCaseListDetail(@RequestBody Integer histCaseListId) {
        log.info("findHistCaseListDetail() start - histCaseListId: " + histCaseListId);
        HistoricalCaseListDetailDTO dto = historicalCaseListService.findHistCaseItem(histCaseListId);
        log.info("findHistCaseListDetail() end - " + dto);
        return dto;
    }
    
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE)
    @PostMapping(value = RequestUriConstant.URI_SAVE_HISTORICAL_CASE_LIST_ITEM)
    public Integer saveHistoricalCaseList(@RequestBody HistoricalCaseListDetailDTO historicalCaseListDetailDTO) {
        log.info("saveReceiptVoucher() start - " + historicalCaseListDetailDTO);
        Integer historicalCaseListId = this.historicalCaseListService.saveHistoricalCaseList(historicalCaseListDetailDTO);
        log.info("saveReceiptVoucher() end - historicalCaseListId: " + historicalCaseListId);
        return historicalCaseListId;
    }

}
