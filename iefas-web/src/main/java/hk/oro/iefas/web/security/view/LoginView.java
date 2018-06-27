package hk.oro.iefas.web.security.view;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.session.Session;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.web.common.service.SystemNotificationClientService;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2902 $ $Date: 2018-06-05 16:30:49 +0800 (週二, 05 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@RequestScoped
public class LoginView implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String PARAM_ERROR = "error";

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String msg = "Please input username";

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private SystemNotificationClientService systemNotificationClientService;

    @Getter
    @Setter
    private String sysNotificationContent;

    @PostConstruct
    private void init() {
        log.info("======LoginView init======");
        Map<String, String> parameterMap = Faces.getRequestParameterMap();
        if (parameterMap.containsKey(PARAM_ERROR)) {
            String error = parameterMap.get(PARAM_ERROR);
            if (CommonUtils.isNotBlank(error)) {
                Messages.addGlobalError(error);
            } else {
                Session session = appResourceUtils.getCurrentSession();
                if (session != null) {
                    Object attribute = session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                    if (attribute instanceof AuthenticationException) {
                        AuthenticationException ex = (AuthenticationException)attribute;
                        if (ex != null) {
                            log.error("Login attempt was not successful.", ex);
                            Messages
                                .addGlobalError("Your login attempt was not successful, try again. " + ex.getMessage());
                        }
                    } else if (attribute instanceof String) {
                        Messages.addGlobalError(appResourceUtils.getMessage(String.valueOf(attribute)));
                    }
                }
            }
        }
        sysNotificationContent = systemNotificationClientService.findEffectiveSysNotificationContent();
        if (sysNotificationContent != null && !sysNotificationContent.trim().isEmpty()) {
            RequestContext.getCurrentInstance().execute("PF('notificationDialog').show()");
        }
    }

}
