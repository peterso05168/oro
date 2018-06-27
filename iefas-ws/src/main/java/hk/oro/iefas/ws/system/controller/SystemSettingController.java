/**
 * 
 */
package hk.oro.iefas.ws.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.dto.OroInfoDTO;
import hk.oro.iefas.domain.system.dto.SysNotificationDTO;
import hk.oro.iefas.domain.system.dto.SystemParameterDTO;
import hk.oro.iefas.domain.system.dto.SystemSettingPageDTO;
import hk.oro.iefas.ws.core.annotation.ModuleLog;
import hk.oro.iefas.ws.core.constant.ModuleLogConstant;
import hk.oro.iefas.ws.system.service.SystemSettingService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_SYSTEM_SETTING)
public class SystemSettingController {

    @Autowired
    private SystemSettingService systemSettingService;

    /**
     * 
     * Initial Load
     */
    @PostMapping(value = RequestUriConstant.URI_SYSTEM_SETTING_PAGE_LOAD)
    public SystemSettingPageDTO loadSystemSettingPage() {
        log.info("loadSystemSettingPage() start");
        SystemSettingPageDTO result = new SystemSettingPageDTO();
        result.setSystemParameterList(loadSystemSettingParameter());
        result.setOroInfo(loadOroInformation());
        result.setSysNotification(loadSystemNotification());
        log.info("loadSystemSettingPage() end - " + result);
        return result;
    }

    /**
     * 
     * Tab 1. System Parameters
     */
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_SYSTEM_SETTING_SYSPAR_LIST)
    public List<SystemParameterDTO> loadSystemSettingParameter() {
        log.info("loadSystemSettingParameter() start");
        List<SystemParameterDTO> result = systemSettingService.findAllSystemParameters();
        log.info("loadSystemSettingParameter() end - " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_SYSTEM_SETTING_SYSPAR_SAVE)
    public SystemSettingPageDTO saveParameters(@RequestBody SystemSettingPageDTO systemSettingPageDTO) {
        log.info("saveParameters() start - " + systemSettingPageDTO.getSystemParameterList());
        List<SystemParameterDTO> parameters = systemSettingPageDTO.getSystemParameterList();
        List<SystemParameterDTO> resultList = systemSettingService.saveParameters(parameters);
        SystemSettingPageDTO result = new SystemSettingPageDTO();
        result.setSystemParameterList(resultList);
        log.info("saveParameters() end - " + resultList);
        return result;
    }

    /**
     * 
     * Tab 2. Oro Information
     */
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_SYSTEM_SETTING_ORO_INFO_LOAD)
    public OroInfoDTO loadOroInformation() {
        log.info("loadOroInformation() start");
        OroInfoDTO result = systemSettingService.loadOroInformation();
        log.info("loadOroInformation() end - " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_SYSTEM_SETTING_ORO_INFO_SAVE)
    public OroInfoDTO saveOroInformation(@RequestBody OroInfoDTO oroInfo) {
        log.info("saveOroInformation() start - " + oroInfo);
        OroInfoDTO result = systemSettingService.saveOroInformation(oroInfo);
        log.info("saveOroInformation() end - " + result);
        return result;
    }

    /**
     * 
     * Tab 3. System Notification
     */
    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_LOAD)
    public SysNotificationDTO loadSystemNotification() {
        log.info("loadSystemNotification() start");
        SysNotificationDTO result = systemSettingService.loadSysNotification();
        log.info("loadSystemNotification() end - " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SAVE,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_SAVE)
    public SysNotificationDTO saveSystemNotification(@RequestBody SysNotificationDTO sysNotificationDTO) {
        log.info("saveSystemNotification() start - " + sysNotificationDTO);
        SysNotificationDTO result = systemSettingService.saveSysNotification(sysNotificationDTO);
        log.info("saveSystemNotification() end - " + result);
        return result;
    }

    @ModuleLog(module = ModuleLogConstant.MODULE_SYSTEM, operation = ModuleLogConstant.OPERATION_SEARCH,
        needTransaction = true)
    @PostMapping(value = RequestUriConstant.URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_POP_UP)
    public String loadEffectiveSysNotificationContent() {
        log.info("loadEffectiveSysNotificationContent() start");
        String result = systemSettingService.loadEffectiveSysNotificationContent();
        log.info("loadEffectiveSysNotificationContent() end - " + result);
        return result;
    }
}
