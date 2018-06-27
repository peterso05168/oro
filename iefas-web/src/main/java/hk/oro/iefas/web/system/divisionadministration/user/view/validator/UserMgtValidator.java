/**
 * 
 */
package hk.oro.iefas.web.system.divisionadministration.user.view.validator;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.core.constant.MsgParamCodeConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.search.vo.PageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.security.vo.UserPwdHistVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.system.divisionadministration.user.view.UserMgtEditView;
import hk.oro.iefas.web.system.profile.service.UserProfileClientService;
import hk.oro.iefas.web.system.profile.service.UserPwdHistClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2040 $ $Date $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@RequestScoped
public class UserMgtValidator {

    @Inject
    private UserMgtEditView userMgtEditView;

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    private UserPwdHistClientService userPwdHistClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private UserProfileClientService userProfileClientService;

    public void validateNewPassword(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        log.info("validateNewPassword() start");
        String inputedPassword = (String)value;
        if (CommonUtils.isNotBlank(inputedPassword)) {
            String passwordRegex = appResourceUtils.getPasswordRegex();
            if (!inputedPassword.matches(passwordRegex)) {
                Messages.add(FacesMessage.SEVERITY_ERROR, null,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_INVALID_PWD));
                Faces.renderResponse();
            }

            Integer userAcId = userMgtEditView.getUserAccountVO().getUserAcId();
            Integer pwdHistCount = appResourceUtils.getPwdHistCount();
            if (pwdHistCount > 0 && userAcId != null) {
                List<UserPwdHistVO> userPwdHistList
                    = userPwdHistClientService.findByUserAcId(new SearchVO<Integer>(userAcId,
                        new PageVO(0, appResourceUtils.getPwdHistCount(), "passwordDate", Direction.DESC.name())));
                boolean anyMatch = userPwdHistList.parallelStream()
                    .anyMatch(pwdHist -> bCryptPasswordEncoder.matches(inputedPassword, pwdHist.getPreviousPassword()));
                if (anyMatch) {
                    Messages.add(FacesMessage.SEVERITY_ERROR, null,
                        appResourceUtils.getMessage(MsgCodeConstant.MSG_INVALID_PWD));
                    ((Password)component).setValid(false);
                    Faces.renderResponse();
                }
            }
        }

        log.info("validateNewPassword() end");
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.info("validateEmail() start");
        String email = (String)value;
        Boolean isExist = userProfileClientService.existsByEmailAddress(email);
        if (isExist) {
            Messages.add(FacesMessage.SEVERITY_ERROR, null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_IS_EXIST, MsgParamCodeConstant.EMAIL));
            ((InputText)component).setValid(false);
            Faces.renderResponse();
        }
        log.info("validateEmail() end");
    }

}
