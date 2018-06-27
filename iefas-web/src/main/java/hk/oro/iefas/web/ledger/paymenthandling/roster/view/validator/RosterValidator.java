package hk.oro.iefas.web.ledger.paymenthandling.roster.view.validator;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.domain.shroff.vo.SearchRosterVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.ledger.paymenthandling.roster.service.RosterClientService;
import hk.oro.iefas.web.ledger.paymenthandling.roster.view.RosterEditView;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3159 $ $Date: 2018-06-15 15:25:48 +0800 (週五, 15 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@RequestScoped
public class RosterValidator {

    @Inject
    private RosterClientService rosterClientService;

    @Inject
    private RosterEditView rosterEditView;

    @Inject
    private AppResourceUtils appResourceUtils;

    @PostConstruct
    public void init() {
        log.info("=============== RosterValidator init start ===============");
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {
        Date date = (Date)value;
        Integer rosterId = rosterEditView.getRosterVO().getRosterId();
        SearchRosterVO criteria = new SearchRosterVO();
        if (rosterId == null)
            criteria.setRosterId(0);
        else
            criteria.setRosterId(rosterId);
        criteria.setOnDutyDate(date);
        Boolean isExists = rosterClientService.existByOnDutyDateAndIdNot(criteria);
        if (isExists != null && isExists) {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_IS_EXIST, MsgParamCodeConstant.ON_DUTY_DATE));
            Faces.renderResponse();
        }
    }
}
