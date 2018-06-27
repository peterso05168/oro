/**
 * 
 */
package hk.oro.iefas.web.security.view;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Value;

import hk.oro.iefas.core.constant.EmailTemplateEnum;
import hk.oro.iefas.core.constant.EmailTemplateParamConstant;
import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.domain.security.vo.UserAccountVO;
import hk.oro.iefas.domain.system.vo.EmailTemplateVO;
import hk.oro.iefas.web.common.service.MailClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.security.service.ForgotPwdLogClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserAccountClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2976 $ $Date: 2018-06-07 11:26:43 +0800 (週四, 07 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class ForgotPasswordView extends BaseBean {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserAccountClientService userAccountClientService;

    @Inject
    private ForgotPwdLogClientService forgotPwdLogClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private MailClientService mailClientService;

    @Value(value = "${iefas.web.address}")
    private String webAddress;

    @Getter
    @Setter
    private String loginName;

    @PostConstruct
    private void init() {
        log.info("======ForgotPasswordView init======");
    }

    public String forgotPassword() {
        log.info("forgotPassword() start");
        UserAccountVO userAccountVO = userAccountClientService.findByLoginName(loginName);
        if (userAccountVO != null) {
            String engName = userAccountVO.getUserProfile() != null ? userAccountVO.getUserProfile().getEngName() : "";
            String token = forgotPwdLogClientService.createForgotPwdLog(userAccountVO.getUserAcId(),
                appResourceUtils.getBusinessDate());
            EmailTemplateEnum securityForgotpwd = EmailTemplateEnum.SECURITY_FORGOTPWD;
            Map<String, Object> params = new HashMap<>();
            params.put(EmailTemplateParamConstant.ENG_NAME, engName);
            params.put(EmailTemplateParamConstant.TOKEN, token);
            params.put(EmailTemplateParamConstant.WEB_ADRESS, webAddress);
            mailClientService.sendModuleMail(new EmailTemplateVO(securityForgotpwd.getModuleCode(),
                securityForgotpwd.getSubModuleCode(), userAccountVO.getUserProfile().getEmailAddress(), params));
        } else {
            Messages
                .addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.USER));
            return null;
        }
        log.info("forgotPassword() end");
        return "forgot-password-ack";
    }
}
