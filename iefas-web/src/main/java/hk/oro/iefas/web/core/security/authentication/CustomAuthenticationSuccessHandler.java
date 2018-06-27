/**
 * 
 */
package hk.oro.iefas.web.core.security.authentication;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.domain.security.vo.UserAccountVO;
import hk.oro.iefas.domain.security.vo.UserAttemptLogVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.security.service.UserAttemptLogClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserAccountClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
public class CustomAuthenticationSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
    implements AuthenticationSuccessHandler {

    @Inject
    private UserAttemptLogClientService userAttemptLogClientService;

    @Inject
    private UserAccountClientService userAccountClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    public CustomAuthenticationSuccessHandler() {}

    public CustomAuthenticationSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        Date currentDate = appResourceUtils.getBusinessDate();
        UserAccountVO userAccountVO = userAccountClientService.findByLoginName(authentication.getName());
        userAccountVO.setLockedInd(false);
        userAccountVO.setUnlockTime(null);
        userAccountVO.setLastLoginTime(currentDate);
        userAccountClientService.save(userAccountVO);

        log.info("Log User Attempt Start");
        UserAttemptLogVO userAttemptLogVO = new UserAttemptLogVO();
        userAttemptLogVO.setLoginName(authentication.getName());
        userAttemptLogVO.setLoginResult(CoreConstant.LOGIN_RESULT_SUCCESS);
        userAttemptLogVO.setStatus(CoreConstant.STATUS_ACTIVE);
        userAttemptLogVO.setAttemptDate(currentDate);
        userAttemptLogVO.setIpAddress(request.getRemoteAddr());
        userAttemptLogClientService.save(userAttemptLogVO);
        log.info("Log User Attempt End");

        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
