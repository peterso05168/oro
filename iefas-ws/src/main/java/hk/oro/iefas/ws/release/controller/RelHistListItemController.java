package hk.oro.iefas.ws.release.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.dto.HolidayDTO;
import hk.oro.iefas.domain.system.dto.HolidayResultDTO;
import hk.oro.iefas.domain.system.dto.ImportFileDTO;
import hk.oro.iefas.domain.system.dto.ImportHolidayResultDTO;
import hk.oro.iefas.domain.system.dto.SearchHolidayDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.system.service.HolidayService;
import hk.oro.iefas.ws.system.service.SystemParameterService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2487 $ $Date: 2018-05-18 17:54:15 +0800 (Fri, 18 May 2018) $
 * @author $Author: cwen $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_HOLIDAY)
public class RelHistListItemController {

    /*@Autowired
    private HolidayService holidayService;
    
    @Autowired
    private SystemParameterService systemParameterService;

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_HOLIDAY_SAVE)
    public HolidayDTO saveHoliday(@Valid @RequestBody HolidayDTO holidayDTO) {
        log.info("saveHoliday() start - " + holidayDTO);
        HolidayDTO result = holidayService.saveHoliday(holidayDTO);
        log.info("saveHoliday() end and result = " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_HOLIDAY_CREATE)
    public void createHoliday(@Valid @RequestBody HolidayDTO holidayDTO) {
        log.info("createHoliday() start - " + holidayDTO);
        holidayService.createHoliday(holidayDTO);
        log.info("createHoliday() end ");
        return;
    }

    @PostMapping(value = RequestUriConstant.URI_HOLIDAY_LOAD)
    public HolidayDTO loadHoliday(@RequestBody Integer holidayId) {
        log.info("loadHoliday() start - " + holidayId);
        HolidayDTO holiday = holidayService.loadHoliday(holidayId);
        log.info("loadHoliday() end and holiday = " + holiday);
        return holiday;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_HOLIDAY_LIST)
    public HolidayResultDTO searchHoliday(@RequestBody SearchHolidayDTO searchDto) {
        log.info("searchHoliday() start - " + searchDto);
        HolidayResultDTO result = holidayService.searchHoliday(searchDto);
        log.info("searchHoliday() end and result = " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_DELETE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_HOLIDAY_DELETE)
    public void deleteHoliday(@Valid @RequestBody HolidayDTO holidayDTO) {
        log.info("deleteHoliday() start - " + holidayDTO);
        holidayService.deleteHoliday(holidayDTO);
        log.info("deleteHoliday() end");
        return;
    }
    
    @GetMapping(value = RequestUriConstant.URI_HOLIDAY_DOWNLOAD)
    public String downloadHolidayTemplate() {
        log.info("downloadHolidayTemplate() start");
        String dateFormat = systemParameterService.getDateFormat();
        String result = holidayService.downloadHolidayTemplate(dateFormat);
        log.info("downloadHolidayTemplate() end");
        return result;
    }
    
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH)
    @PostMapping(value = RequestUriConstant.URI_HOLIDAY_IMPORT_TEMPLATE)
    public ImportHolidayResultDTO importListTemplate(@RequestBody ImportFileDTO importFileDTO) throws Exception {
        log.info("importListTemplate() start - " + importFileDTO);
        ImportHolidayResultDTO holiday = holidayService.importListTemplate(importFileDTO);
        log.info("importListTemplate() end and holiday = " + holiday);
        return holiday;
    }
    
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
            needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_HOLIDAY_CONFIRM_UPLOAD)
    public int confirmUploadRecord(@Valid @RequestBody ImportHolidayResultDTO importHolidayResultDTO) {
        log.info("confirmUploadRecord() start - " + importHolidayResultDTO);
        int result = holidayService.confirmUploadRecord(importHolidayResultDTO);
        log.info("confirmUploadRecord() end ");
        return result;
    }*/

}
