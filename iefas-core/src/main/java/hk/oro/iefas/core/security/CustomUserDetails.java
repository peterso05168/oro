package hk.oro.iefas.core.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import lombok.Getter;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
public class CustomUserDetails extends User {

    private static final long serialVersionUID = 1L;

    @Getter
    private final String post;

    @Getter
    private final String userFullName;

    @Getter
    private final boolean divisionAdminInd;

    @Getter
    private final boolean systemAdminInd;

    @Getter
    private final Integer postId;

    @Getter
    private final Integer divisionId;

    @Getter
    private final String division;

    public CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
        boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
        String post, String userFullName, boolean divisionAdminInd, boolean systemAdminInd, Integer postId,
        Integer divisionId, String division) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.post = post;
        this.userFullName = userFullName;
        this.divisionAdminInd = divisionAdminInd;
        this.systemAdminInd = systemAdminInd;
        this.postId = postId;
        this.divisionId = divisionId;
        this.division = division;
    }

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
        String post, String userFullName, boolean divisionAdminInd, boolean systemAdminInd, Integer postId,
        Integer divisionId, String division) {
        super(username, password, authorities);
        this.post = post;
        this.userFullName = userFullName;
        this.divisionAdminInd = divisionAdminInd;
        this.systemAdminInd = systemAdminInd;
        this.postId = postId;
        this.divisionId = divisionId;
        this.division = division;
    }

    public static CustomUserBuilder withCustomUsername(String username) {
        return new CustomUserBuilder().username(username);
    }

    public static class CustomUserBuilder {
        private String username;
        private String password;
        private List<GrantedAuthority> authorities;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;
        private String post;
        private String userFullName;
        private boolean divisionAdminInd;
        private boolean systemAdminInd;
        private Integer postId;
        private Integer divisionId;
        private String division;

        private CustomUserBuilder() {}

        private CustomUserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public CustomUserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public CustomUserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.length);
            for (String role : roles) {
                Assert.isTrue(!role.startsWith("ROLE_"), role + " cannot start with ROLE_ (it is automatically added)");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
            return authorities(authorities);
        }

        public CustomUserBuilder authorities(GrantedAuthority... authorities) {
            return authorities(Arrays.asList(authorities));
        }

        public CustomUserBuilder authorities(List<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<GrantedAuthority>(authorities);
            return this;
        }

        public CustomUserBuilder authorities(String... authorities) {
            return authorities(AuthorityUtils.createAuthorityList(authorities));
        }

        public CustomUserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public CustomUserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public CustomUserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public CustomUserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public CustomUserBuilder post(String post) {
            this.post = post;
            return this;
        }

        public CustomUserBuilder userFullName(String userFullName) {
            this.userFullName = userFullName;
            return this;
        }

        public CustomUserBuilder divisionAdminInd(boolean divisionAdminInd) {
            this.divisionAdminInd = divisionAdminInd;
            return this;
        }

        public CustomUserBuilder systemAdminInd(boolean systemAdminInd) {
            this.systemAdminInd = systemAdminInd;
            return this;
        }

        public CustomUserBuilder postId(Integer postId) {
            this.postId = postId;
            return this;
        }

        public CustomUserBuilder divisionId(Integer divisionId) {
            this.divisionId = divisionId;
            return this;
        }

        public CustomUserBuilder division(String division) {
            this.division = division;
            return this;
        }

        public UserDetails build() {
            return new CustomUserDetails(username, password, !disabled, !accountExpired, !credentialsExpired,
                !accountLocked, authorities, post, userFullName, divisionAdminInd, systemAdminInd, postId, divisionId,
                division);
        }
    }

}
