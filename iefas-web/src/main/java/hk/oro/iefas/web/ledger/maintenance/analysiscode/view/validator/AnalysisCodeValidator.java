package hk.oro.iefas.web.ledger.maintenance.analysiscode.view.validator;

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
import hk.oro.iefas.domain.shroff.vo.SearchAnalysisCodeCriteriaVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.ledger.maintenance.analysiscode.service.AnalysisCodeClientService;
import hk.oro.iefas.web.ledger.maintenance.analysiscode.view.AnalysisCodeEditView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequestScoped
public class AnalysisCodeValidator {

    @Autowired
    private AppResourceUtils appResourceUtils;

    @Inject
    private AnalysisCodeEditView analysisCodeEditView;

    @Inject
    private AnalysisCodeClientService analysisCodeClientService;

    @PostConstruct
    private void init() {
        log.info("=====AnalysisCodeValidator init=====");
    }

    public void validateAnalysisCode(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        log.info("validateAnalysisCode() start-");
        String code = (String)value;
        SearchAnalysisCodeCriteriaVO criteria = new SearchAnalysisCodeCriteriaVO();
        criteria.setAnalysisCodeId(
            analysisCodeEditView.getAnalysisCodeId() != null ? analysisCodeEditView.getAnalysisCodeId() : 0);
        criteria.setAnalysisCode(code);
        Boolean isExists = analysisCodeClientService.existsByAnalysisCode(criteria);
        if (isExists != null & isExists) {
            Messages.addError(null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_DUPLICATE_VALUE, MsgParamCodeConstant.ANALYSIS_CODE));
            Faces.renderResponse();
        }
    }
}
