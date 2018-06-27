package hk.oro.iefas.web.dividend.maintenance.commoncreditor.view.validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.domain.casemgt.vo.CommonCreditorVO;
import hk.oro.iefas.domain.casemgt.vo.SearchCommonCreditorCriteriaVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.dividend.maintenance.commoncreditor.service.DividendCommonCreditorClientService;
import hk.oro.iefas.web.dividend.maintenance.commoncreditor.view.CommonCreditorEditView;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@RequestScoped
public class CommonCreditorValidator {
    @Inject
    private AppResourceUtils appResourceUtils;
    @Inject
    private CommonCreditorEditView commonCreditorEditView;

    @Inject
    private DividendCommonCreditorClientService dividendCommonCreditorClientService;

    @PostConstruct
    private void init() {
        log.info("======CommonCreditorValidator init======");
    }

    public boolean saveCommonCreditorValidate() {
        return validateCommonCreditorName();
    }

    private boolean validateCommonCreditorName() {
        CommonCreditorVO commonCreditorVO = commonCreditorEditView.getCommonCreditorVO();
        SearchCommonCreditorCriteriaVO criteriaVO = new SearchCommonCreditorCriteriaVO();
        criteriaVO.setCommonCreditorId(
            commonCreditorVO.getCommonCreditorId() != null ? commonCreditorVO.getCommonCreditorId() : 0);
        criteriaVO.setCommonCreditorName(commonCreditorVO.getCommonCreditorName());
        if (dividendCommonCreditorClientService.existsByCommonCreditorName(criteriaVO)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_IS_EXIST, MsgParamCodeConstant.CREDITOR_NAME), ""));
            ((UIInput)Faces.getViewRoot().findComponent("editForm:" + MsgParamCodeConstant.CREDITOR_NAME))
                .setValid(false);
            return false;
        }
        return true;
    }
}
