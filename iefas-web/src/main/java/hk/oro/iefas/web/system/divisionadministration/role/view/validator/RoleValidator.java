package hk.oro.iefas.web.system.divisionadministration.role.view.validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.system.divisionadministration.role.service.RoleClientService;
import hk.oro.iefas.web.system.divisionadministration.role.view.RoleDetailView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequestScoped
public class RoleValidator {

    @Inject
    private RoleDetailView roleDetailView;

    @Inject
    private RoleClientService roleClientService;

    @PostConstruct
    private void init() {
        log.info("======RoleValidator init======");
    }

    public void validateRoleName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String name = (String)value;
        Boolean isExists = roleClientService.existsByRoleName(name,
            roleDetailView.getRoleVO().getRoleId() != null ? roleDetailView.getRoleVO().getRoleId() : 0);
        if (isExists != null & isExists) {
            Messages.add(FacesMessage.SEVERITY_ERROR, null, "Role Name is duplicated.");
            Faces.renderResponse();
        }
    }
}
