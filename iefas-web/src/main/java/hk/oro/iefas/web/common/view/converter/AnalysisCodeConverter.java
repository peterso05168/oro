/**
 * 
 */
package hk.oro.iefas.web.common.view.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.shroff.vo.AnalysisCodeVO;
import hk.oro.iefas.web.common.util.ApplicationContextUtils;
import hk.oro.iefas.web.ledger.maintenance.analysiscode.service.AnalysisCodeClientService;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@FacesConverter("analysisCodeConverter")
public class AnalysisCodeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        AnalysisCodeClientService analysisCodeClientService = ApplicationContextUtils.getApplicationContext()
            .getBean("analysisCodeClientServiceImpl", AnalysisCodeClientService.class);
        List<AnalysisCodeVO> analysisCodes = analysisCodeClientService.findByAnalysisCode(value);
        if (CommonUtils.isNotEmpty(analysisCodes)) {
            return analysisCodes.get(0);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        AnalysisCodeVO analysisCode = (AnalysisCodeVO)value;

        return analysisCode.getAnalysisCode();
    }

}
