package hk.oro.iefas.web.funds.maintenance.fundsparameter.view.validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@RequestScoped
public class FundsParameterValidator {

    @Getter
    @Setter
    private Integer upliftDate;

    @PostConstruct
    private void init() {
        log.info("======FundsParameterValidator init======");
    }

    public void validateUpliftDate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        this.upliftDate = (Integer)value;
        if (this.upliftDate == null || this.upliftDate.intValue() <= 0) {
            Messages.add(FacesMessage.SEVERITY_ERROR, null, "Uplift date must be greater than 0.");
            Faces.renderResponse();
        }
    }

}
