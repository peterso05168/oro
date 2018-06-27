package hk.oro.iefas.web.core.security.authentication;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.security.vo.UserAccountVO;
import hk.oro.iefas.domain.security.vo.UserAttemptLogVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.security.service.UserAttemptLogClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserAccountClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3245 $ $Date: 2018-06-21 13:27:27 +0800 (週四, 21 六月 2018) $
 * @author $Author: dante.fang $
 */
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final static String USERNAME = "username";

    private String defaultFailureUrl;
    private boolean forwardToDestination = false;
    private boolean allowSessionCreation = true;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Inject
    private UserAttemptLogClientService userAttemptLogClientService;

    @Inject
    private UserAccountClientService userAccountClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    public CustomAuthenticationFailureHandler() {}

    public CustomAuthenticationFailureHandler(String defaultFailureUrl) {
        setDefaultFailureUrl(defaultFailureUrl);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {

        Date currentDate = appResourceUtils.getBusinessDate();
        String username = request.getParameter(USERNAME);
        if (exception instanceof BadCredentialsException) {
            UserAccountVO userAccountVO = userAccountClientService.findByLoginName(username);
            Integer failCount = appResourceUtils.getLoginFailCount();
            if (failCount != null) {
                if (userAccountVO.getFailCount().equals(failCount)) {
                    userAccountVO.setFailCount(0);
                    userAccountVO.setLockedInd(true);

                    Calendar c = Calendar.getInstance();
                    c.setTime(currentDate);
                    c.add(Calendar.MINUTE, appResourceUtils.getLoginLockPeriod());
                    userAccountVO.setUnlockTime(c.getTime());
                } else {
                    userAccountVO.setFailCount(userAccountVO.getFailCount() + 1);
                }
                userAccountClientService.save(userAccountVO);
            }
        }

        log.info("Log User Attempt Start");
        UserAttemptLogVO userAttemptLogVO = new UserAttemptLogVO();

        userAttemptLogVO.setLoginName(username);
        userAttemptLogVO.setLoginResult(CoreConstant.LOGIN_RESULT_FAILURE);
        userAttemptLogVO.setStatus(CoreConstant.STATUS_ACTIVE);
        userAttemptLogVO.setAttemptDate(currentDate);
        userAttemptLogVO.setIpAddress(request.getRemoteAddr());
        userAttemptLogClientService.save(userAttemptLogVO);
        log.info("Log User Attempt End");

        if (defaultFailureUrl == null) {
            log.debug("No failure URL set, sending 401 Unauthorized error");

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + exception.getMessage());
        } else {
            saveException(request, exception);

            if (forwardToDestination) {
                log.debug("Forwarding to " + defaultFailureUrl);

                request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
            } else {
                log.debug("Redirecting to " + defaultFailureUrl);
                redirectStrategy.sendRedirect(request, response, defaultFailureUrl);
            }
        }
    }

    protected final void saveException(HttpServletRequest request, AuthenticationException exception) {
        if (forwardToDestination) {
            request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        } else {
            HttpSession session = request.getSession(false);

            if (session != null || allowSessionCreation) {
                request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
            }
        }
    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultFailureUrl),
            "'" + defaultFailureUrl + "' is not a valid redirect URL");
        this.defaultFailureUrl = defaultFailureUrl;
    }

    protected boolean isUseForward() {
        return forwardToDestination;
    }

    public void setUseForward(boolean forwardToDestination) {
        this.forwardToDestination = forwardToDestination;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    protected boolean isAllowSessionCreation() {
        return allowSessionCreation;
    }

    public void setAllowSessionCreation(boolean allowSessionCreation) {
        this.allowSessionCreation = allowSessionCreation;
    }
}
