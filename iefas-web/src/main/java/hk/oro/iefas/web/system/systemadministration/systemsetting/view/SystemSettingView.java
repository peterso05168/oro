package hk.oro.iefas.web.system.systemadministration.systemsetting.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.domain.system.vo.OroInfoVO;
import hk.oro.iefas.domain.system.vo.SysNotificationVO;
import hk.oro.iefas.domain.system.vo.SystemSettingPageVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.systemadministration.systemsetting.service.SystemSettingClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
public class SystemSettingView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Autowired
    private SystemSettingClientService systemSettingClientService;

    @Getter
    @Setter
    private SystemSettingPageVO systemSettingPageVO;

    @Getter
    @Setter
    private OroInfoVO oroInfoVO;

    @PostConstruct
    private void initialLoad() {
        log.info("====== SystemSettingView init start======");
        systemSettingPageVO = systemSettingClientService.loadSystemSettingPage();
        // Load System Notification
        log.info("====== SystemSettingView init end======");
    }

    public void saveSystemSettingParameter() {
        log.info("saveSystemSettingParameter() start - systemParameterVoList = "
            + systemSettingPageVO.getSystemParameterList());
        SystemSettingPageVO savedSystemSettingPageVO = systemSettingClientService.saveParameters(systemSettingPageVO);
        this.systemSettingPageVO.setSystemParameterList(savedSystemSettingPageVO.getSystemParameterList());
        appResourceUtils.reloadSysParams();
        log.info("saveSystemSettingParameter() end - systemParameterVoList = "
            + systemSettingPageVO.getSystemParameterList());
    }

    public void saveOROInfo() {
        log.info("saveOROInfo() start");
        OroInfoVO savedVo = systemSettingClientService.saveOroInformation(systemSettingPageVO.getOroInfo());
        this.systemSettingPageVO.setOroInfo(savedVo);
        Messages.addGlobalInfo("Save ORO Information Successfully!");
        log.info("saveOROInfo() end");
    }

    public void saveNotification() {
        log.info("saveNotification() start");
        SysNotificationVO savedVo
            = systemSettingClientService.saveSysNotification(systemSettingPageVO.getSysNotification());
        this.systemSettingPageVO.setSysNotification(savedVo);
        Messages.addGlobalInfo("Save System Notification Successfully!");
        log.info("saveNotification() end");
    }

}
