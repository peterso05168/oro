package hk.oro.iefas.web.ledger.chequehandling.outgoingcheque.view.validator;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.domain.shroff.vo.SearchIncomingChequeVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.ledger.chequehandling.incomingcheque.service.ChequeClientService;
import hk.oro.iefas.web.ledger.chequehandling.outgoingcheque.view.OutgoingChequeEditView;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3088 $ $Date: 2018-06-12 18:15:45 +0800 (週二, 12 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@RequestScoped
public class OutgoingChequeValidator {
    public static final int MIN_CHEQUE_NO = 10000000;
    public static final int MAX_CHEQUE_NO = 99999999;
    @Inject
    private ChequeClientService chequeClientService;

    @Inject
    private OutgoingChequeEditView outgoingChequeEditView;

    private Integer chequeId;

    @Inject
    private AppResourceUtils appResourceUtils;

    @PostConstruct
    public void init() {
        log.info("ChequeValidator init start==================");
        if (outgoingChequeEditView != null && outgoingChequeEditView.getChequeVO() != null)
            chequeId = outgoingChequeEditView.getChequeVO().getChequeId();
        log.info("ChequeValidator init end ====================");
    }

    public void validateChequeNumber(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        Integer chequeNumber = (Integer)value;
        if (chequeNumber < MIN_CHEQUE_NO || chequeNumber > MAX_CHEQUE_NO) {
            Messages.addError(null, appResourceUtils.getMessage(MsgCodeConstant.MSG_INPUT_VALID_CHEQUE_NUMBER));
            Faces.renderResponse();
        }
        SearchIncomingChequeVO criteria = new SearchIncomingChequeVO();
        if (chequeId != null)
            criteria.setChequeId(chequeId);
        else
            criteria.setChequeId(0);
        criteria.setChequeNumber(chequeNumber);
        Boolean exist = chequeClientService.existsByChequeNumber(criteria);
        if (exist != null && exist) {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_DUPLICATE_VALUE, MsgParamCodeConstant.CHEQUE_NUMBER));
            Faces.renderResponse();
        }
    }
}
