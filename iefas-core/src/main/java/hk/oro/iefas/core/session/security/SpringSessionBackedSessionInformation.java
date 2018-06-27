package hk.oro.iefas.core.session.security;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class SpringSessionBackedSessionInformation extends SessionInformation {

    private static final long serialVersionUID = 1L;

    static final String EXPIRED_ATTR = SpringSessionBackedSessionInformation.class.getName() + ".EXPIRED";

    private static final Log logger = LogFactory.getLog(SpringSessionBackedSessionInformation.class);

    private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    /**
     * Tries to determine the principal's name from the given Session.
     * 
     * @param session Spring Session session
     * @return the principal's name, or empty String if it couldn't be determined
     */
    private static String resolvePrincipal(Session session) {
        String principalName = session.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME);
        if (principalName != null) {
            return principalName;
        }
        SecurityContext securityContext = session.getAttribute(SPRING_SECURITY_CONTEXT);
        if (securityContext != null && securityContext.getAuthentication() != null) {
            return securityContext.getAuthentication().getName();
        }
        return "";
    }

    private final SessionRepository<? extends ExpiringSession> sessionRepository;

    SpringSessionBackedSessionInformation(ExpiringSession session,
        SessionRepository<? extends ExpiringSession> sessionRepository) {
        super(resolvePrincipal(session), session.getId(), new Date(session.getLastAccessedTime()));
        this.sessionRepository = sessionRepository;
        if (Boolean.TRUE.equals(session.getAttribute(EXPIRED_ATTR))) {
            super.expireNow();
        }
    }

    @Override
    public void expireNow() {
        if (logger.isDebugEnabled()) {
            logger.debug("Expiring session " + getSessionId() + " for user '" + getPrincipal()
                + "', presumably because maximum allowed concurrent sessions was exceeded");
        }
        super.expireNow();
        this.sessionRepository.delete(getSessionId());
    }
}
