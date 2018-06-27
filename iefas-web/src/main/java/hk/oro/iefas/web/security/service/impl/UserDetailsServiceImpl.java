package hk.oro.iefas.web.security.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.security.CustomUserDetails;
import hk.oro.iefas.core.security.CustomUserDetails.CustomUserBuilder;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.security.vo.UserDetailVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserAccountClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    private UserAccountClientService userAccountClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername() start - User Name: " + username);
        UserDetails user = null;

        UserDetailVO userDetailVO = userAccountClientService.findUserDetail(username);

        if (userDetailVO == null) {
            log.debug("User '" + username + " not found");

            throw new UsernameNotFoundException("User '" + username + " not found");
        }

        Date currentDate = appResourceUtils.getBusinessDate();
        boolean lockedInd = userDetailVO.getLockedInd();
        if (userDetailVO.getUnlockTime() != null && currentDate.after(userDetailVO.getUnlockTime())) {
            lockedInd = false;
        }

        CustomUserBuilder userBuilder = CustomUserDetails.withCustomUsername(username)
            .password(userDetailVO.getPassword()).post(userDetailVO.getPostTitle())
            .userFullName(userDetailVO.getEngName()).postId(userDetailVO.getPostId())
            .division(userDetailVO.getDivisionName()).divisionId(userDetailVO.getDivisionId()).divisionAdminInd(true)
            .systemAdminInd(true).disabled(!CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(userDetailVO.getStatus()))
            .accountExpired(false).accountLocked(lockedInd);

        Integer pwdExpDay = appResourceUtils.getPwdExpDay();
        if (pwdExpDay > 0) {
            userBuilder
                .credentialsExpired(!currentDate.before(DateUtils.addDays(userDetailVO.getExpiryDate(), pwdExpDay)));
        }

        userBuilder
            .authorities(userDetailVO.getPrivileges().stream().map(p -> p.getPrivilegeCode()).toArray(String[]::new));

        user = userBuilder.build();

        return user;
    }
}
