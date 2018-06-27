package hk.oro.iefas.web.system.systemadministration.systemsetting.service;

import java.util.List;

import hk.oro.iefas.domain.search.vo.ResultPageVO;
import hk.oro.iefas.domain.system.vo.OroInfoVO;
import hk.oro.iefas.domain.system.vo.SysNotificationVO;
import hk.oro.iefas.domain.system.vo.HolidayResultVO;
import hk.oro.iefas.domain.system.vo.SystemParameterVO;
import hk.oro.iefas.domain.system.vo.SystemSettingPageVO;

public interface SystemSettingClientService {

    SystemSettingPageVO loadSystemSettingPage();

    ResultPageVO<List<SystemParameterVO>> findAllSystemParameters();

    OroInfoVO loadOroInformation();

    SystemSettingPageVO saveParameters(SystemSettingPageVO systemSettingPageVO);

    OroInfoVO saveOroInformation(OroInfoVO oroInfo);

    SysNotificationVO saveSysNotification(SysNotificationVO sysNotificationVo);
}
