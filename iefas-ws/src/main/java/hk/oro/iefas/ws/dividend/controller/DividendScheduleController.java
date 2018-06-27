package hk.oro.iefas.ws.dividend.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.casemgt.dto.CreditorDTO;
import hk.oro.iefas.domain.dividend.dto.ApprovedAdjucationResultItemDTO;
import hk.oro.iefas.domain.dividend.dto.CreateDividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleDTO;
import hk.oro.iefas.domain.dividend.dto.DividendScheduleItemDTO;
import hk.oro.iefas.domain.dividend.dto.SearchDividendScheduleDTO;
import hk.oro.iefas.domain.search.dto.SearchDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.dividend.service.DividendScheduleService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3240 $ $Date: 2018-06-21 10:18:46 +0800 (週四, 21 六月 2018) $
 * @author $Author: noah.liang $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.ROOT_DIVIDEND_SCHEDULE)
public class DividendScheduleController {

    @Autowired
    private DividendScheduleService dividendScheduleService;

    @PostMapping(value = RequestUriConstant.VALIDATE_CREATE_DIVIDEND_SCHEDULE)
    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    public boolean createDividendScheduleValidate(@RequestBody CreateDividendScheduleDTO dto) {
        log.info("createDividendScheduleValidate() start - and param: " + dto);
        boolean exists = dividendScheduleService.validateCreateDividendSchedule(dto);
        log.info("createDividendScheduleValidate() end - and return: " + exists);
        return exists;
    }

    @PostMapping(value = RequestUriConstant.SEARCH_DIVIDEND_SCHEDULE_LIST)
    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    public Page<DividendScheduleDTO>
        searchDividendScheduleList(@RequestBody SearchDTO<SearchDividendScheduleDTO> searchDTO) {
        log.info("searchDividendScheduleList() start - and param: " + searchDTO);
        Page<DividendScheduleDTO> scheduleList = dividendScheduleService.searchDividendScheduleList(searchDTO);
        log.info("searchDividendScheduleList() end - and return: " + scheduleList);
        return scheduleList;
    }

    @PostMapping(value = RequestUriConstant.SEARCH_DIVIDEND_SCHEDULE)
    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    public DividendScheduleDTO searchDividendSchedule(@RequestBody Integer dividendScheduleId) {
        log.info("searchDividendSchedule() start - and param: " + dividendScheduleId);
        DividendScheduleDTO dividendScheduleDTO = dividendScheduleService.searchDividendSchedule(dividendScheduleId);
        log.info("searchDividendSchedule() end - and return: " + dividendScheduleDTO);
        return dividendScheduleDTO;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.FIND_BY_CREDITOR_ID)
    public List<ApprovedAdjucationResultItemDTO> findByCreditor(@RequestBody Integer creditorId) {
        log.info("findByCreditor() start - and creditorId: " + creditorId);
        List<ApprovedAdjucationResultItemDTO> result = dividendScheduleService.findByCreditor(creditorId);
        log.info("findByCreditor() end - and return: " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.SEARCH_CREDITOR_BY_CASEID)
    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    public List<CreditorDTO> searchCreditorByCaseId(@RequestBody Integer caseId) {
        log.info("searchCreditorByCaseId() start - and caseId: " + caseId);
        List<CreditorDTO> result = dividendScheduleService.searchCreditorByCaseId(caseId);
        log.info("searchCreditorByCaseId() end - and return: " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.SEARCH_TOTALINTERESTAMOUNT)
    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    public BigDecimal searchTotalInterestAmount(@RequestBody Integer creditorId) {
        log.info("searchCreditorByCaseNumber() start - and creditorId: " + creditorId);
        BigDecimal result = dividendScheduleService.searchTotalInterestAmount(creditorId);
        log.info("searchCreditorByCaseNumber() end - and return: " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.SEARCH_DIVSCHEDULEITEM_BY_ID)
    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    public DividendScheduleItemDTO searchDividendScheduleItemById(@RequestBody Integer divScheduleId) {
        log.info("searchDividendScheduleItemById() start - and divScheduleId: " + divScheduleId);
        DividendScheduleItemDTO result = this.dividendScheduleService.searchDividendScheduleItemById(divScheduleId);
        log.info("searchDividendScheduleItemById() end - and return: " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.SAVE_DIVIDENDSCHEDULE)
    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SAVE)
    public Integer saveDividendSchedule(@RequestBody DividendScheduleDTO dividendScheduleDTO) {
        log.info("searchDividendScheduleItemById() start - and dividendScheduleDTO: " + dividendScheduleDTO);
        Integer result = this.dividendScheduleService.saveDividendSchedule(dividendScheduleDTO);
        log.info("searchDividendScheduleItemById() end - and return: " + result);
        return result;
    }

    @PostMapping(value = RequestUriConstant.SEARCH_BY_CREDTYPEID)
    @ModuleLog(module = ModuleLogConstant.MODULE_DIVIDEND, operation = ModuleLogConstant.OPERATION_SEARCH)
    public BigDecimal findCredTypePercentageByCredTypeId(@RequestBody Integer credTypeId) {
        log.info("findCredTypePercentageByCredTypeId() start - and credTypeId: " + credTypeId);
        BigDecimal result = this.dividendScheduleService.findCredTypePercentageByCredTypeId(credTypeId);
        log.info("findCredTypePercentageByCredTypeId() end - and return: " + result);
        return result;
    }
}
