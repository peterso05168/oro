/**
 * 
 */
package hk.oro.iefas.web.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;

/**
 * @author Marvel.ma
 * 
 */
public class CustomUserFilter implements Filter {

    private RequestMatcher requiresAuthenticationRequestMatcher;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public CustomUserFilter() {
        requiresAuthenticationRequestMatcher
            = new AntPathRequestMatcher("/oro/admin/index/login.xhtml", HttpMethod.POST.name());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        if (!requiresAuthenticationRequestMatcher.matches(req)) {
            chain.doFilter(request, response);
        } else {

            String username
                = request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
            if (CommonUtils.isNotBlank(username)) {
                chain.doFilter(request, response);
            } else {
                saveError(req, MsgCodeConstant.MSG_EMPTY_USERNAME);
                redirectStrategy.sendRedirect(req, res, req.getServletPath() + "?error");
            }
        }

    }

    @Override
    public void destroy() {}

    protected final void saveError(HttpServletRequest request, String msgCode) {
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, msgCode);
    }

}
