package hk.oro.iefas.web.casemgt.maintenance.outsider.view.validator;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.domain.casemgt.vo.SearchOutsiderDetailCriteriaVO;
import hk.oro.iefas.web.casemgt.maintenance.outsider.service.OutsiderClientService;
import hk.oro.iefas.web.casemgt.maintenance.outsider.view.OutsiderEditView;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version $Revision: 3257 $ $Date: 2018-06-22 10:09:33 +0800 (週五, 22 六月 2018) $
 * @author $Author: garrett.han $
 */
@Slf4j
@Named
@RequestScoped
public class OutsiderValidator {

    @Inject
    private OutsiderEditView outsiderEditView;

    @Autowired
    private AppResourceUtils appResourceUtils;
    @Inject
    private OutsiderClientService outsiderClientService;

    private Pattern chiPattern;

    @PostConstruct
    private void init() {
        log.info("======OutsiderEditView init start ======");
        chiPattern = Pattern.compile("[\u4e00-\u9fa5]");
        log.info("======OutsiderEditView init end =========");
    }

    public void validateOutsiderName(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        String name = (String)value;
        SearchOutsiderDetailCriteriaVO criteriaVO = new SearchOutsiderDetailCriteriaVO();
        criteriaVO.setOutsiderName(name);
        if (outsiderEditView.getOutsiderVO().getOutsiderId() == null) {
            criteriaVO.setOutsiderId(0);
        } else {
            criteriaVO.setOutsiderId(outsiderEditView.getOutsiderVO().getOutsiderId());
        }
        Boolean isExists = outsiderClientService.existsByOutsiderName(criteriaVO);
        if (isExists != null && isExists) {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_DUPLICATE_VALUE, MsgParamCodeConstant.OUTSIDER_NAME));
            Faces.renderResponse();
        }
    }

    public void validateEngAddress(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        String address = (String)value;
        if(address == null){
            return;
        }
        if(address.length() != address.getBytes().length) {
            Messages.addError(null,appResourceUtils.getMessage(MsgCodeConstant.MSG_ENG_ADDRESS_ERROR));
        }
    }

    public void validateChiAddress(FacesContext context,UIComponent component,Object value) {
        String address = (String)value;
        if(address == null)
            return;
        Matcher matcher = chiPattern.matcher(address);
        if(!matcher.find()) {
            Messages.addError(null,appResourceUtils.getMessage(MsgCodeConstant.MSG_CHI_ADDRESS_ERROR));
        }
    }

}
