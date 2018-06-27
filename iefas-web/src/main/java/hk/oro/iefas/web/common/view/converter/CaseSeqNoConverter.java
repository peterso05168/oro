package hk.oro.iefas.web.common.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import hk.oro.iefas.core.util.CommonUtils;

/**
 * @version $Revision: 2596 $ $Date: 2018-05-24 20:28:38 +0800 (週四, 24 五月 2018) $
 * @author $Author: vicki.huang $
 */
@FacesConverter("caseSeqNoConverter")
public class CaseSeqNoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (CommonUtils.isNotBlank(value)) {
            StringBuilder result = new StringBuilder();
            if (value.length() < 5) {
                for (int i = 5 - value.length(); i > 0; i--) {
                    result.append("0");
                }
            }
            result.append(value);
            return result.toString();
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(value);
    }

}
