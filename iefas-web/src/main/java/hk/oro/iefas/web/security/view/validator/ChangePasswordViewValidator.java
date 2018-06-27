/**
 * 
 */
package hk.oro.iefas.web.security.view.validator;

import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.password.Password;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.common.view.validator.PasswordValidator;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.security.view.ChangePasswordView;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@RequestScoped
public class ChangePasswordViewValidator {

    private static final String FORM_ID = "changePwdForm";
    private static final String COMP_NEW_PWD_ID = FORM_ID + ":newPassword";
    private static final String COMP_CONFIRM_PWD_ID = FORM_ID + ":confirmPassword";

    @Inject
    private PasswordValidator passwordValidator;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private ChangePasswordView changePasswordView;

    public void validate(ComponentSystemEvent event) {
        log.info("validate() start");
        Password newPwd = (Password)Faces.getViewRoot().findComponent(COMP_NEW_PWD_ID);
        String newPasswordStr = newPwd.getLocalValue() == null ? null : String.valueOf(newPwd.getLocalValue());
        Password confirmPwd = (Password)Faces.getViewRoot().findComponent(COMP_CONFIRM_PWD_ID);
        String confirmPasswordStr
            = confirmPwd.getLocalValue() == null ? null : String.valueOf(confirmPwd.getLocalValue());

        if (CommonUtils.isBlank(newPasswordStr)) {
            Messages.addGlobalError(appResourceUtils.getRequiredMessage(MsgParamCodeConstant.NEW_PWD));
            newPwd.setValid(false);
            Faces.renderResponse();
        } else {
            if (!passwordValidator.validateFormat(newPasswordStr)) {
                Messages.addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_INVALID_PWD));
                newPwd.setValid(false);
                Faces.renderResponse();
            }
            if (!passwordValidator.validateExist(newPasswordStr, changePasswordView.getUserAcId())) {
                Messages.addGlobalError(appResourceUtils.getMessage(MsgCodeConstant.MSG_INVALID_PWD));
                newPwd.setValid(false);
                Faces.renderResponse();
            }
        }

        if (CommonUtils.isBlank(confirmPasswordStr)) {
            Messages.addGlobalError(appResourceUtils.getRequiredMessage(MsgParamCodeConstant.CONFIRM_PWD));
            confirmPwd.setValid(false);
            Faces.renderResponse();
        }

        if (CommonUtils.isNotBlank(confirmPasswordStr) && CommonUtils.isNotBlank(newPasswordStr)
            && !confirmPasswordStr.equals(newPasswordStr)) {
            Messages.addGlobalError(
                appResourceUtils.getNotEqMsg(MsgParamCodeConstant.NEW_PWD, MsgParamCodeConstant.CONFIRM_PWD));
            newPwd.setValid(false);
            confirmPwd.setValid(false);
            Faces.renderResponse();
        }

        log.info("validate() end");
    }

}
