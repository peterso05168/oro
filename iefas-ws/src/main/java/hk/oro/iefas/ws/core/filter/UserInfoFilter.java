package hk.oro.iefas.ws.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import hk.oro.iefas.core.exceptions.BaseException;
import hk.oro.iefas.core.security.UserInfo;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.ws.common.util.RequestContextUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3217 $ $Date: 2018-06-20 14:01:41 +0800 (週三, 20 六月 2018) $
 * @author $Author: scott.feng $
 */
@Slf4j
@WebFilter("/*")
public class UserInfoFilter implements Filter {

    public static final String BASIC = "Basic ";
    private static final String NOT_AUTHENTICATED = "User is not authenticated.";
    private static final String[] ALLOW_ACCESS_PATHS = {"/system"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        // HttpServletResponse httpServletResponse = (HttpServletResponse)
        // response;
        String servletPath = httpServletRequest.getServletPath();

        for (String str : ALLOW_ACCESS_PATHS) {
            if (str.equalsIgnoreCase(servletPath)) {
                chain.doFilter(request, response);
                return;
            }

        }

        String userInfoHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (CommonUtils.isNotBlank(userInfoHeader)) {
            int basicLength = BASIC.length();
            if (userInfoHeader.length() > basicLength) {
                try {
                    String userInfoJson
                        = new String(Base64Utils.decodeFromString(userInfoHeader.substring(basicLength)));
                    ObjectMapper mapper = new ObjectMapper();
                    UserInfo userInfo = mapper.readValue(userInfoJson, UserInfo.class);
                    RequestContextUtils.setCurrentUserInfo(userInfo);
                    chain.doFilter(request, response);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    throw new BaseException(e.getMessage(), e);
                } finally {
                }
            } else {
                log.info(NOT_AUTHENTICATED);
                chain.doFilter(request, response);
                // httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            }

        } else {
            log.info(NOT_AUTHENTICATED);
            chain.doFilter(request, response);
            // httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
