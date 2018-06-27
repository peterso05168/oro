package hk.oro.iefas.web.system.divisionadministration.post.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeCriteriaVO;
import hk.oro.iefas.domain.security.vo.SearchPrivilegeResultVO;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.system.divisionadministration.role.service.RolePrivilegeClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class PrivilegeDialogView implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String REQUEST_PARAM_ROLE_ID = "roleId";

    @Inject
    private RolePrivilegeClientService rolePrivilegeClientService;

    @Getter
    @Setter
    private List<SearchPrivilegeResultVO> searchPrivilegeResults;

    @PostConstruct
    private void init() {
        log.info("======PrivilegeDialogView init======");
        String parameter = Faces.getRequestParameter(REQUEST_PARAM_ROLE_ID);
        if (CommonUtils.isNotBlank(parameter)) {
            searchPrivilegeResults
                = rolePrivilegeClientService.findByCriteria(new SearchPrivilegeCriteriaVO(Integer.valueOf(parameter),
                    null, null, null, null, CoreConstant.STATUS_ACTIVE));
        }
    }

    public void closeDialog() {
        log.info("closeDialog() start");
        RequestContext.getCurrentInstance().closeDialog(null);
        log.info("closeDialog() end");
    }

}
