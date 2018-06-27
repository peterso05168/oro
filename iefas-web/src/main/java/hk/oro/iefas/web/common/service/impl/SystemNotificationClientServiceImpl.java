package hk.oro.iefas.web.common.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.vo.SysNotificationVO;
import hk.oro.iefas.web.common.service.SystemNotificationClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date :$
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class SystemNotificationClientServiceImpl extends BaseClientService implements SystemNotificationClientService {

    @Override
    public SysNotificationVO findSysNotification() {
        log.info("findSysNotification() start");
        ResponseEntity<SysNotificationVO> responseEntity = postForEntity(
            RequestUriConstant.CLIENT_URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_LOAD, null, SysNotificationVO.class);
        SysNotificationVO body = responseEntity.getBody();
        log.info("findSysNotification() end - " + body);
        return body;
    }

    @Override
    public String findEffectiveSysNotificationContent() {
        log.info("findEffectiveSysNotificationContent() start");
        String result = null;
        try {
            result = postForObject(RequestUriConstant.CLIENT_URI_SYSTEM_SETTING_SYSTEM_NOTIFICATION_POP_UP, null,
                String.class);
        } catch (Exception e) {
            log.error("", e);
        }
        log.info("findEffectiveSysNotificationContent() end - " + result);
        return result;
    }

}
