/**
 * 
 */
package hk.oro.iefas.ws.system.service;

import java.util.List;

import hk.oro.iefas.domain.system.dto.OroInfoDTO;
import hk.oro.iefas.domain.system.dto.SysNotificationDTO;
import hk.oro.iefas.domain.system.dto.SystemParameterDTO;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public interface SystemSettingService {

    List<SystemParameterDTO> findAllSystemParameters();

    OroInfoDTO loadOroInformation();

    List<SystemParameterDTO> saveParameters(List<SystemParameterDTO> parameters);

    OroInfoDTO saveOroInformation(OroInfoDTO oroInfo);

    SysNotificationDTO loadSysNotification();

    SysNotificationDTO saveSysNotification(SysNotificationDTO sysNotificationDTO);

    String loadEffectiveSysNotificationContent();

}
