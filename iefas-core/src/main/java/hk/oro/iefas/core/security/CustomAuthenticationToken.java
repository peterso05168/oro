package hk.oro.iefas.core.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

/**
 * @version $Revision: 2214 $ $Date: 2018-04-25 14:05:52 +0800 (週三, 25 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    @Getter
    private final String delegatePost;

    @Getter
    private final String delegateUser;

    @Getter
    private final Integer delegatePostId;

    @Getter
    private final Integer delegateDivisionId;

    public CustomAuthenticationToken(Object principal, Object credentials, String delegatePost, Integer delegatePostId,
        String delegateUser, Integer delegateDivisionId) {
        super(principal, credentials);
        this.delegatePost = delegatePost;
        this.delegateUser = delegateUser;
        this.delegateDivisionId = delegateDivisionId;
        this.delegatePostId = delegatePostId;
    }

    public CustomAuthenticationToken(Object principal, Object credentials,
        Collection<? extends GrantedAuthority> authorities, String delegatePost, Integer delegatePostId,
        String delegateUser, Integer delegateDivisionId) {
        super(principal, credentials, authorities);
        this.delegatePost = delegatePost;
        this.delegateUser = delegateUser;
        this.delegateDivisionId = delegateDivisionId;
        this.delegatePostId = delegatePostId;
    }

}
