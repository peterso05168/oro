package hk.oro.iefas.web.system.systemadministration.holiday.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("yearConverter")
public class YearConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            int year = Integer.valueOf(value);
            if (year >= 90 && year < 100) {
                return String.valueOf(1900 + year);
            } else if (year >= 0 && year < 90) {
                return String.valueOf(2000 + year);
            }
        }
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(value);
    }

}
