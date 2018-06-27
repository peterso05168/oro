/**
 * 
 */
package hk.oro.iefas.core.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import hk.oro.iefas.core.security.CustomAuthenticationToken;
import hk.oro.iefas.core.security.CustomUserDetails;

/**
 * @version $Revision: 2214 $ $Date: 2018-04-25 14:05:52 +0800 (週三, 25 四月 2018) $
 * @author $Author: marvel.ma $
 */
public abstract class SecurityUtils {

    public static Set<GrantedAuthority> unmodifiableSet(Collection<? extends GrantedAuthority> authorities) {
        return Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());

        if (CommonUtils.isNotEmpty(authorities)) {
            for (GrantedAuthority grantedAuthority : authorities) {
                Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
                sortedAuthorities.add(grantedAuthority);
            }
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        private static final long serialVersionUID = 1L;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    public static Integer getCurrentPostId() {
        Integer postId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CustomAuthenticationToken
            && ((CustomAuthenticationToken)authentication).getDelegatePostId() != null) {
            postId = ((CustomAuthenticationToken)authentication).getDelegatePostId();
        } else if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            postId = user.getPostId();
        }
        return postId;
    }

    public static Integer getCurrenDivisionId() {
        Integer divisionId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CustomAuthenticationToken
            && ((CustomAuthenticationToken)authentication).getDelegateDivisionId() != null) {
            divisionId = ((CustomAuthenticationToken)authentication).getDelegateDivisionId();
        } else if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            divisionId = user.getDivisionId();
        }
        return divisionId;
    }

    public static String getUserName() {
        String userName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            userName = user.getUsername();
        }
        return userName;
    }

}
