package hk.oro.iefas.core.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.FilterBase;
import org.apache.catalina.filters.RemoteAddrFilter;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import hk.oro.iefas.core.util.CommonUtils;

/**
 * @version $Revision: 912 $ $Date: 2018-01-19 10:27:19 +0800 (週五, 19 一月 2018) $
 * @author $Author: marvel.ma $
 */
public class CustomRequestFilter extends FilterBase {

    private static final Log log = LogFactory.getLog(RemoteAddrFilter.class);

    protected List<Pattern> allows = new ArrayList<>();

    protected List<Pattern> denys = new ArrayList<>();

    protected int denyStatus = HttpServletResponse.SC_FORBIDDEN;

    private static final String PLAIN_TEXT_MIME_TYPE = "text/plain";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        process(request.getRemoteAddr(), request, response, chain);
    }

    @Override
    protected Log getLogger() {
        return log;
    }

    public List<Pattern> getAllows() {
        return allows;
    }

    public void setAllows(List<Pattern> allows) {
        this.allows = allows;
    }

    public void setAllow(String allow) {
        if (CommonUtils.isNotBlank(allow)) {
            this.allows.add(Pattern.compile(allow));
        }
    }

    public List<Pattern> getDenys() {
        return denys;
    }

    public void setDenys(List<Pattern> denys) {
        this.denys = denys;
    }

    public void setDeny(String deny) {
        if (CommonUtils.isNotBlank(deny)) {
            this.denys.add(Pattern.compile(deny));
        }
    }

    public int getDenyStatus() {
        return denyStatus;
    }

    public void setDenyStatus(int denyStatus) {
        this.denyStatus = denyStatus;
    }

    // ------------------------------------------------------ Protected Methods

    @Override
    protected boolean isConfigProblemFatal() {
        return true;
    }

    private void process(String property, ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        if (isAllowed(property)) {
            chain.doFilter(request, response);
        } else {
            if (response instanceof HttpServletResponse) {
                if (getLogger().isDebugEnabled()) {
                    getLogger().debug(
                        sm.getString("requestFilter.deny", ((HttpServletRequest)request).getRequestURI(), property));
                }
                ((HttpServletResponse)response).sendError(denyStatus);
            } else {
                sendErrorWhenNotHttp(response);
            }
        }
    }

    private boolean isAllowed(String property) {

        boolean isAllowed = false;

        if (CommonUtils.isNotEmpty(this.denys)) {

            isAllowed = !this.denys.parallelStream().anyMatch(deny -> deny.matcher(property).matches());
        }

        // Check the allow patterns, if any
        if (CommonUtils.isNotEmpty(this.allows)) {
            isAllowed = this.allows.parallelStream().anyMatch(allow -> allow.matcher(property).matches());
        }

        // Allow if denies specified but not allows
        if (CommonUtils.isNotEmpty(this.denys) && CommonUtils.isEmpty(this.allows)) {
            isAllowed = true;
        }

        // Deny this request
        return isAllowed;
    }

    private void sendErrorWhenNotHttp(ServletResponse response) throws IOException {
        response.setContentType(PLAIN_TEXT_MIME_TYPE);
        response.getWriter().write(sm.getString("http.403"));
        response.getWriter().flush();
    }

}
