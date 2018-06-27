package hk.oro.iefas.core.session.security;

import static java.util.stream.Collectors.toList;
import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.util.Assert;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
public class SpringSessionBackedSessionRegistry implements SessionRegistry {
    private final FindByIndexNameSessionRepository<? extends ExpiringSession> sessionRepository;

    public SpringSessionBackedSessionRegistry(
        FindByIndexNameSessionRepository<? extends ExpiringSession> sessionRepository) {
        Assert.notNull(sessionRepository, "sessionRepository cannot be null");
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Object> getAllPrincipals() {
        throw new UnsupportedOperationException(
            "SpringSessionBackedSessionRegistry does not support retrieving all principals, "
                + "since Spring Session provides no way to obtain that information");
    }

    @Override
    public List<SessionInformation> getAllSessions(Object principal, boolean includeExpiredSessions) {
        return sessionRepository.findByIndexNameAndIndexValue(PRINCIPAL_NAME_INDEX_NAME, name(principal)).values()
            .stream().filter(session -> includeExpiredSessions || !session.isExpired())
            .map(session -> new SpringSessionBackedSessionInformation(session, sessionRepository)).collect(toList());
    }

    @Override
    public SessionInformation getSessionInformation(String sessionId) {
        ExpiringSession session = this.sessionRepository.getSession(sessionId);
        if (session != null) {
            return new SpringSessionBackedSessionInformation(session, this.sessionRepository);
        }
        return null;
    }

    /**
     * This is a no-op, as we don't administer sessions ourselves.
     */
    @Override
    public void refreshLastRequest(String sessionId) {}

    /**
     * This is a no-op, as we don't administer sessions ourselves.
     */
    @Override
    public void registerNewSession(String sessionId, Object principal) {}

    /**
     * This is a no-op, as we don't administer sessions ourselves.
     */
    @Override
    public void removeSessionInformation(String sessionId) {}

    /**
     * Derives a String name for the given principal.
     * 
     * @param principal as provided by Spring Security
     * @return name of the principal, or its {@code toString()} representation if no name could be derived
     */
    protected String name(Object principal) {
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        }
        if (principal instanceof Principal) {
            return ((Principal)principal).getName();
        }
        return principal.toString();
    }
}
