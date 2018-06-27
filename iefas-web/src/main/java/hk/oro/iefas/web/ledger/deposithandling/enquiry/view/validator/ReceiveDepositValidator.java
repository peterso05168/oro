package hk.oro.iefas.web.ledger.deposithandling.enquiry.view.validator;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
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
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2911 $ $Date: 2018-06-05 17:53:12 +0800 (週二, 05 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@RequestScoped
public class ReceiveDepositValidator {
    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private ChequeClientService chequeClientService;

    @PostConstruct
    public void init() {
        log.info("================ receiveDepositValidator init start ================");
        log.info("================ receiveDepositValidator init end ===================");
    }

    public void validateChequeNumber(FacesContext context, UIComponent component, Object value) {
        BigDecimal chequeNumber = (BigDecimal)value;
        if (chequeNumber != null) {
            SearchIncomingChequeVO searchIncomingChequeVO = new SearchIncomingChequeVO();
            searchIncomingChequeVO.setChequeNumber(chequeNumber.intValue());
            searchIncomingChequeVO.setChequeId(0);
            Boolean exists = chequeClientService.existsByChequeNumber(searchIncomingChequeVO);
            if (exists != null && !exists) {
                Messages.addError(null,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_NOT_EXIST, MsgParamCodeConstant.CHEQUE_NUMBER));
                Faces.renderResponse();
            }
        }
    }

}
