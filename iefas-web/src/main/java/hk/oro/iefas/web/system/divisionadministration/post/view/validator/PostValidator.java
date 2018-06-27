package hk.oro.iefas.web.system.divisionadministration.post.view.validator;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.system.divisionadministration.post.service.PostClientService;
import hk.oro.iefas.web.system.divisionadministration.post.view.PostEditView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequestScoped
public class PostValidator {

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private PostEditView postEditView;

    @Inject
    private PostClientService postClientService;

    @PostConstruct
    private void init() {
        log.info("======PostValidator init======");
    }

    public void validatePostTitle(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String name = (String)value;
        Boolean isExists = postClientService.existsByPostName(name,
            postEditView.getPostVO().getPostId() != null ? postEditView.getPostVO().getPostId() : 0);
        if (isExists != null & isExists) {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_DUPLICATE_VALUE, MsgParamCodeConstant.POST_TITLE));
            Faces.renderResponse();
        }
    }

}
