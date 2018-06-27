package hk.oro.iefas.web.funds.maintenance.currencyrate.view.validator;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.bank.vo.CurrencyRateVO;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.funds.maintenance.currencyrate.view.CurrencyRateEditView;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@RequestScoped
public class CurrencyRateValidator {

    @Inject
    private CurrencyRateEditView currencyRateEditView;

    @Getter
    @Setter
    private Date from;

    @PostConstruct
    private void init() {
        log.info("======CurrencyRateValidator init======");
    }

    public void validatePeriod(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.info("validatePeriod() start");
        Date to = (Date)value;
        log.info("From: " + from + " , To: " + to);
        CurrencyRateVO currencyRateVO = currencyRateEditView.getCurrencyRateVO();

        List<CurrencyRateVO> currencyRates = currencyRateEditView.getCurrencyVO().getCurrencyRates();

        if (CommonUtils.isNotEmpty(currencyRates)) {
            for (CurrencyRateVO cr : currencyRates) {
                if (CoreConstant.STATUS_ACTIVE.equalsIgnoreCase(cr.getStatus())
                    && currencyRateVO.hashCode() != cr.hashCode()) {
                    if (!(from.after(cr.getEffectiveTo()) || to.before(cr.getEffectiveFrom()))) {
                        Messages.addError("msgs", "Period is repeated.");
                        Faces.renderResponse();
                        break;
                    }
                }
            }
        }
        log.info("validatePeriod() end");
    }

    public void setFromDateValue(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.info("setFromDateValue() start");
        this.from = (Date)value;
        log.info("setFromDateValue() end");
    }

}
