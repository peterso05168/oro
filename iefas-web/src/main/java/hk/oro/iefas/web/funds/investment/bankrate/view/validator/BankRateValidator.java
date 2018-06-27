package hk.oro.iefas.web.funds.investment.bankrate.view.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.funds.investment.bankrate.service.BankRateClientService;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Named
@RequestScoped
public class BankRateValidator {

    @Inject
    private BankRateClientService bankRateClientService;

    public void investDateValidate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        Boolean validate = bankRateClientService.createUploadBankRateValidate((Date)value);
        if (validate == null || !validate) {
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Investment Date is duplicated.", null));
            Faces.renderResponse();
        }
    }
}
