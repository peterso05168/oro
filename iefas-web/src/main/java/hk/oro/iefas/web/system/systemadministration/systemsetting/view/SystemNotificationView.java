package hk.oro.iefas.web.system.systemadministration.systemsetting.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import hk.oro.iefas.web.common.service.SystemNotificationClientService;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequestScoped
public class SystemNotificationView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private SystemNotificationClientService systemNotificationClientService;

    @Getter
    @Setter
    private String sysNotificationContent;

    @PostConstruct
    private void init() {
        log.info("======SystemNotificationView init Start======");
        sysNotificationContent = systemNotificationClientService.findEffectiveSysNotificationContent();
        log.info("======SystemNotificationView init End======");
    }
}
