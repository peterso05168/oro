package hk.oro.iefas.web.common.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@RequestScoped
public class CaseNumberValidator {

    @Inject
    private AppResourceUtils appResourceUtils;

    public void validateCaseNumber(String caseTypeCode, String caseSeqNo, String caseYear) throws ValidatorException {
        log.info("validateCaseNumber() start");
        if (CommonUtils.isNotBlank(caseTypeCode) || CommonUtils.isNotBlank(caseSeqNo)
            || CommonUtils.isNotBlank(caseYear)) {
            boolean flag = false;
            if (CommonUtils.isBlank(caseTypeCode)) {
                Messages.add(FacesMessage.SEVERITY_ERROR, null,
                    appResourceUtils.getRequiredMessage(MsgParamCodeConstant.CASE_TYPE_CODE));
                flag = true;
            }
            if (CommonUtils.isBlank(caseSeqNo)) {
                Messages.add(FacesMessage.SEVERITY_ERROR, null,
                    appResourceUtils.getRequiredMessage(MsgParamCodeConstant.CASE_SEQ_NO));
                flag = true;
            }
            if (CommonUtils.isBlank(caseYear)) {
                Messages.add(FacesMessage.SEVERITY_ERROR, null,
                    appResourceUtils.getRequiredMessage(MsgParamCodeConstant.CASE_YEAR));
                flag = true;
            }
            if (flag) {
                Faces.renderResponse();
            }
        }
        log.info("validateCaseNumber() end");
    }

}
