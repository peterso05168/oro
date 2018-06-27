package hk.oro.iefas.web.core.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.security.CustomAuthenticationToken;
import hk.oro.iefas.core.security.CustomUserDetails;
import hk.oro.iefas.core.security.UserInfo;
import hk.oro.iefas.core.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2888 $ $Date: 2018-06-04 22:28:27 +0800 (週一, 04 六月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
public class CurrentUserInfoInterceptor implements ClientHttpRequestInterceptor {
    private static final Charset CHAR_SET = CoreConstant.UTF_8;
    private static final String DEFAULT_USER = "System";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {
        log.info("intercept() start");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ObjectMapper mapper = new ObjectMapper();
        String userInfoOfJson = null;
        UserInfo userInfo = new UserInfo();

        if (authentication == null || !authentication.isAuthenticated()
            || authentication instanceof AnonymousAuthenticationToken) {
            userInfo.setUserName(DEFAULT_USER);
            userInfo.setDelegateUser(DEFAULT_USER);
            userInfo.setDelegatePost(DEFAULT_USER);
            userInfo.setUserPost(DEFAULT_USER);
        } else {
            Object principal = authentication.getPrincipal();
            CustomUserDetails user = (CustomUserDetails)principal;
            String username = user.getUsername();
            String post = user.getPost();
            Integer divisionId = user.getDivisionId();
            Integer postId = user.getPostId();
            userInfo.setUserName(username);
            userInfo.setUserPost(post);
            userInfo.setDivisionId(divisionId);
            userInfo.setPostId(postId);

            if (authentication instanceof CustomAuthenticationToken) {
                CustomAuthenticationToken authenticationToken = (CustomAuthenticationToken)authentication;
                String delegateUser = authenticationToken.getDelegateUser();
                if (CommonUtils.isEmpty(delegateUser)) {
                    userInfo.setDelegateUser(username);
                    userInfo.setDelegatePost(post);
                    userInfo.setDelegateDivisionId(divisionId);
                    userInfo.setDelegatePostId(postId);
                } else {
                    userInfo.setDelegateUser(delegateUser);
                    userInfo.setDelegatePost(authenticationToken.getDelegatePost());
                    userInfo.setDelegateDivisionId(authenticationToken.getDelegateDivisionId());
                    userInfo.setDelegatePostId(authenticationToken.getDelegatePostId());
                }
            } else {
                userInfo.setDelegateUser(username);
                userInfo.setDelegatePost(post);
                userInfo.setDelegateDivisionId(divisionId);
                userInfo.setDelegatePostId(postId);
            }
        }
        userInfoOfJson = mapper.writeValueAsString(userInfo);

        String token = Base64Utils.encodeToString((userInfoOfJson).getBytes(CHAR_SET));
        request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Basic " + token);

        log.info("intercept() end");
        return execution.execute(request, body);
    }

}
