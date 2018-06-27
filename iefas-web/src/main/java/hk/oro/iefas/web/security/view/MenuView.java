package hk.oro.iefas.web.security.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import hk.oro.iefas.core.security.CustomAuthenticationToken;
import hk.oro.iefas.core.security.CustomUserDetails;
import hk.oro.iefas.core.util.SecurityUtils;
import hk.oro.iefas.domain.organization.vo.DelegatedVO;
import hk.oro.iefas.domain.security.vo.PrivilegeVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.security.service.MenuClientService;
import hk.oro.iefas.web.security.session.UserSessionBean;
import hk.oro.iefas.web.system.divisionadministration.role.service.PrivilegeClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@ViewScoped
public class MenuView implements Serializable {

    private static final long serialVersionUID = -6587083632616959616L;

    @Inject
    private MenuClientService menuClientService;

    @Inject
    private PrivilegeClientService privilegeClientService;

    @Getter
    @Setter
    @Inject
    private UserSessionBean userSession;

    @PostConstruct
    private void init() {
        log.info("======MenuView init======");
        if (this.userSession.getMenuModel() == null) {
            this.userSession.buildMenu(menuClientService.findAllTopMenu());
            this.userSession.renderMenu(menuClientService.findByPostId(SecurityUtils.getCurrentPostId()));
        }
    }

    public void openSwitchUserDialog() {
        log.info("openSwitchUserDialog() start");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("minHeight", 50);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");

        RequestContext.getCurrentInstance().openDialog("switch-user", options, null);
        log.info("openSwitchUserDialog() end");
    }

    public void onUserChosen(SelectEvent event) throws IOException {
        log.info("onUserChosen() start");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (event.getObject() != null) {
            DelegatedVO selectedDelegationInfoVO = (DelegatedVO)event.getObject();
            List<PrivilegeVO> privileges = privilegeClientService.findByPostId(selectedDelegationInfoVO.getPostId());
            List<GrantedAuthority> authorityList = AuthorityUtils
                .createAuthorityList(privileges.stream().map(p -> p.getPrivilegeCode()).toArray(String[]::new));
            CustomAuthenticationToken authenticationToken = new CustomAuthenticationToken(authentication.getPrincipal(),
                authentication.getCredentials(), SecurityUtils.unmodifiableSet(authorityList),
                selectedDelegationInfoVO.getPost(), selectedDelegationInfoVO.getPostId(),
                selectedDelegationInfoVO.getLoginName(), selectedDelegationInfoVO.getDivisionId());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
            CustomAuthenticationToken authenticationToken = new CustomAuthenticationToken(authentication.getPrincipal(),
                authentication.getCredentials(), user.getAuthorities(), null, null, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        FacesContext.getCurrentInstance().getExternalContext()
            .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.html");
        log.info("onUserChosen() end");
    }
}
