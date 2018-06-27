package hk.oro.iefas.web.ledger.maintenance.controlaccount.view.validator;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.domain.shroff.vo.SearchControlAccountVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.ledger.maintenance.controlaccount.service.ControlAccountClientService;
import hk.oro.iefas.web.ledger.maintenance.controlaccount.view.ControlAccountEditView;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@RequestScoped
public class ControlAccountValidator {
    @Inject
    private ControlAccountClientService controlAccountClientService;

    @Inject
    private ControlAccountEditView controlAccountEditView;

    @Inject
    private AppResourceUtils appResourceUtils;

    @PostConstruct
    public void init() {
        log.info("============= ControlAccountValidator init start ============");
        log.info("============= ControlAccountValidator init end ============");
    }

    public void validateControlAccountName(FacesContext context, UIComponent component, Object value) {
        String controlAccountName = (String)value;
        SearchControlAccountVO criteria = new SearchControlAccountVO();
        criteria.setCtlAcName(controlAccountName);
        if (controlAccountEditView.getControlAccount().getCtlAcId() == null)
            criteria.setCtlAcId(0);
        else
            criteria.setCtlAcId(controlAccountEditView.getControlAccount().getCtlAcId());
        Boolean exists = controlAccountClientService.existsByControlAccountName(criteria);
        if (exists != null && exists) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_DUPLICATE_VALUE,
                MsgParamCodeConstant.CONTROL_ACCOUNT_NAME));
            Faces.renderResponse();
        }
    }

    public void validateControlAccountCode(FacesContext context, UIComponent component, Object value) {
        String controlAccountCode = (String)value;
        SearchControlAccountVO criteria = new SearchControlAccountVO();
        criteria.setCtlAcCode(controlAccountCode);
        if (controlAccountEditView.getControlAccount().getCtlAcId() == null)
            criteria.setCtlAcId(0);
        else
            criteria.setCtlAcId(controlAccountEditView.getControlAccount().getCtlAcId());
        Boolean exists = controlAccountClientService.existsByControlAccountCode(criteria);
        if (exists != null && exists) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_DUPLICATE_VALUE,
                MsgParamCodeConstant.CONTROL_ACCOUNT_CODE));
            Faces.renderResponse();
        }
    }
}
