/**
 * 
 */
package hk.oro.iefas.web.security.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import hk.oro.iefas.core.security.CustomUserDetails;
import hk.oro.iefas.domain.organization.vo.DelegatedVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserDelegationClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class SwitchUserView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserDelegationClientService userDelegationClientService;

    @Getter
    @Setter
    private List<DelegatedVO> delegatedVOs;

    @Getter
    @Setter
    private DelegatedVO selectedDelegatedVO;

    @Getter
    @Setter
    private String post;

    @Getter
    @Setter
    private String userFullName;

    @PostConstruct
    private void init() {
        log.info("======SwitchUserView init======");

        initUserForSwitch();
    }

    private void initUserForSwitch() {
        log.info("initUserForSwitch() start");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            this.post = user.getPost();
            this.userFullName = user.getUserFullName();
            delegatedVOs = userDelegationClientService.findByDelegateTo(user.getPostId());
        }
        log.info("initUserForSwitch() end");
    }

    public void switchUser() {
        log.info("switchUser() start");
        RequestContext.getCurrentInstance().closeDialog(this.selectedDelegatedVO);
        log.info("switchUser() end");
    }

    public void switchBack() {
        log.info("switchBack() start");
        RequestContext.getCurrentInstance().closeDialog(null);
        log.info("switchBack() end");
    }
}
