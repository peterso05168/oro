package hk.oro.iefas.web.system.divisionadministration.role.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import hk.oro.iefas.domain.organization.vo.SearchRoleCriteriaVO;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@RequestScoped
public class RoleSearchBean {

    @Inject
    private RoleManagementView roleManagementView;

    @PostConstruct
    private void init() {
        log.info("======RoleManagementView init======");
    }

    public void search() {
        log.info("search() start");
        log.info("Criteria: " + roleManagementView.getCriteria());
        if (roleManagementView.getRoleResultDataModel() == null) {
            roleManagementView.setRoleResultDataModel(new RoleLazyDataModel(roleManagementView.getCriteria()));
        }
        log.info("search() end");
    }

    public void reset() {
        log.info("reset() start");
        roleManagementView.setCriteria(new SearchRoleCriteriaVO());
        roleManagementView.setRoleResultDataModel(null);
        log.info("reset() end");
    }

}
