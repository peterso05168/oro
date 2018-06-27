package hk.oro.iefas.web.funds.maintenance.currencyrate.view.validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.domain.bank.vo.CurrencyVO;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.service.CurrencyClientService;
import hk.oro.iefas.web.funds.maintenance.currencyrate.view.CurrencyRateEditView;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@RequestScoped
public class CurrencyValidator {

    @Inject
    private CurrencyClientService currencyClientService;

    @Inject
    private CurrencyRateEditView currencyRateEditView;

    @PostConstruct
    private void init() {
        log.info("======CurrencyValidator init======");
    }

    public void validateCurrencyCode(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        log.info("validateCurrencyCode() start");
        String code = (String)value;
        CurrencyVO currencyVO = currencyRateEditView.getCurrencyVO();
        Boolean isExists = currencyClientService.existsByCurcyCode(code, currencyVO.getCurcyId());
        if (isExists != null & isExists) {
            Messages.add(FacesMessage.SEVERITY_ERROR, null, "Currency Code is duplicated.");
            Faces.renderResponse();
        }
        log.info("validateCurrencyCode() end");
    }
}
