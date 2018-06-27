package hk.oro.iefas.web.common.view.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import hk.oro.iefas.domain.shroff.vo.ControlAccountVO;
import hk.oro.iefas.web.common.util.ApplicationContextUtils;
import hk.oro.iefas.web.ledger.maintenance.controlaccount.service.ControlAccountClientService;

/**
 * 
 * @version $Revision$ $Date$
 * @author $Author$
 */
@FacesConverter("controlAccountConverter")
public class ControlAccountConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ControlAccountClientService controlAccountClientService = ApplicationContextUtils.getApplicationContext().
            getBean("controlAccountClientServiceImpl", ControlAccountClientService.class);
        Integer controlAccountId = Integer.parseInt(value);
        ControlAccountVO controlAccount = controlAccountClientService.getControlAccountDetail(controlAccountId);
        return controlAccount;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null) {
            return "";
        }
        ControlAccountVO controlAccount = (ControlAccountVO)value;
        return controlAccount.getCtlAcId().toString();
    }
    
}
