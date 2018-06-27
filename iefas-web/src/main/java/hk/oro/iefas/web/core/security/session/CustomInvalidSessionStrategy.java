/**
 * 
 */
package hk.oro.iefas.web.core.security.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {

    private final String destinationUrl;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private static final String FACES_REQUEST_HEADER = "faces-request";

    @Setter
    private boolean createNewSession = true;

    public CustomInvalidSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        boolean ajaxRedirect = "partial/ajax".equals(request.getHeader(FACES_REQUEST_HEADER));
        if (ajaxRedirect) {
            String contextPath = request.getContextPath();
            String redirectUrl = contextPath + destinationUrl;
            log.debug("Session expired due to ajax request, redirecting to '{}'", redirectUrl);

            String ajaxRedirectXml = createAjaxRedirectXml(redirectUrl);
            log.debug("Ajax partial response to redirect: {}", ajaxRedirectXml);

            response.setContentType("text/xml");
            response.getWriter().write(ajaxRedirectXml);
        } else {
            log.debug("Starting new session (if required) and redirecting to '" + destinationUrl + "'");
            if (createNewSession) {
                request.getSession();
            }
            redirectStrategy.sendRedirect(request, response, destinationUrl);
        }
    }

    private String createAjaxRedirectXml(String redirectUrl) {
        return new StringBuilder().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
            .append("<partial-response><redirect url=\"").append(redirectUrl)
            .append("\"></redirect></partial-response>").toString();
    }

}
