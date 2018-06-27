/**
 * 
 */
package hk.oro.iefas.web.common.service;

import hk.oro.iefas.domain.system.vo.SysNotificationVO;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public interface SystemNotificationClientService {

    SysNotificationVO findSysNotification();

    String findEffectiveSysNotificationContent();
}
