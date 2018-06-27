/**
 * 
 */
package hk.oro.iefas.web.system.profile.view.validator;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hk.oro.iefas.core.constant.MsgCodeConstant;
import hk.oro.iefas.domain.search.vo.PageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.security.vo.UserPwdHistVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.scope.RequestScoped;
import hk.oro.iefas.web.system.profile.service.UserPwdHistClientService;
import hk.oro.iefas.web.system.profile.view.MyProfileView;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Named
@RequestScoped
public class UserProfileValidator {

    @Inject
    private MyProfileView myProfileView;

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    private UserPwdHistClientService userPwdHistClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    public void validateCurrentPassword(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        log.info("validateCurrentPassword() start");
        String inputedPassword = (String)value;
        String currentPassword = myProfileView.getUserAccountVO().getPassword();
        if (!bCryptPasswordEncoder.matches(inputedPassword, currentPassword)) {
            Messages.add(FacesMessage.SEVERITY_ERROR, null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_INCORRECT_PWD));
            Faces.renderResponse();
        }
        log.info("validateCurrentPassword() end");
    }

    public void validateNewPassword(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        log.info("validateNewPassword() start");
        String inputedPassword = (String)value;

        String passwordRegex = appResourceUtils.getPasswordRegex();
        if (!inputedPassword.matches(passwordRegex)) {
            Messages.add(FacesMessage.SEVERITY_ERROR, null,
                appResourceUtils.getMessage(MsgCodeConstant.MSG_INVALID_PWD));
            Faces.renderResponse();
        }

        Integer userAcId = myProfileView.getUserAccountVO().getUserAcId();
        Integer pwdHistCount = appResourceUtils.getPwdHistCount();
        if (pwdHistCount > 0) {
            List<UserPwdHistVO> userPwdHistList
                = userPwdHistClientService.findByUserAcId(new SearchVO<Integer>(userAcId,
                    new PageVO(0, appResourceUtils.getPwdHistCount(), "passwordDate", Direction.DESC.name())));
            boolean anyMatch = userPwdHistList.parallelStream()
                .anyMatch(pwdHist -> bCryptPasswordEncoder.matches(inputedPassword, pwdHist.getPreviousPassword()));
            if (anyMatch) {
                Messages.add(FacesMessage.SEVERITY_ERROR, null,
                    appResourceUtils.getMessage(MsgCodeConstant.MSG_INVALID_PWD));
                Faces.renderResponse();
            }
        }
        log.info("validateNewPassword() end");
    }
}
