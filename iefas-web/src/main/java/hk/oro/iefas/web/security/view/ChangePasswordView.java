/**
 * 
 */
package hk.oro.iefas.web.security.view;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.SysParamConstant;
import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.core.util.DateUtils;
import hk.oro.iefas.domain.security.vo.ForgotPwdLogVO;
import hk.oro.iefas.domain.security.vo.UserAccountVO;
import hk.oro.iefas.domain.security.vo.UserPwdHistVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.core.jsf.bean.BaseBean;
import hk.oro.iefas.web.core.jsf.scope.ViewScoped;
import hk.oro.iefas.web.security.service.ForgotPwdLogClientService;
import hk.oro.iefas.web.system.divisionadministration.user.service.UserAccountClientService;
import hk.oro.iefas.web.system.profile.service.UserPwdHistClientService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Named
@ViewScoped
public class ChangePasswordView extends BaseBean {

    private static final long serialVersionUID = 1L;

    private static final String PARAM_TOKEN = "token";

    @Inject
    private ForgotPwdLogClientService forgotPwdLogClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    @Inject
    private UserAccountClientService userAccountClientService;

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    private UserPwdHistClientService userPwdHistClientService;

    @Getter
    @Setter
    private String newPassword;

    @Getter
    @Setter
    private String confirmPassword;

    @Getter
    @Setter
    private Integer userAcId;

    @PostConstruct
    private void init() {
        log.info("======ChangePasswordView init======");
        checkToken();
    }

    public void checkToken() {
        log.info("checkToken() start");
        boolean isValid = true;
        String token = Faces.getRequestParameter(PARAM_TOKEN);

        if (CommonUtils.isBlank(token)) {
            isValid = false;
        } else {
            ForgotPwdLogVO forgotPwdLogVO = forgotPwdLogClientService.findByToken(token);
            if (forgotPwdLogVO != null) {
                Integer fptExpMin = (Integer)appResourceUtils.getSysParam(SysParamConstant.USER_FPTEXPMIN);
                Date expDate = DateUtils.addMinutes(forgotPwdLogVO.getForgotPwdDate(), fptExpMin);
                if (appResourceUtils.getBusinessDate().after(expDate) || forgotPwdLogVO.getUsed()) {
                    isValid = false;
                } else {
                    userAcId = forgotPwdLogVO.getUserAcId();
                    forgotPwdLogVO.setUsed(true);
                    forgotPwdLogClientService.save(forgotPwdLogVO);
                }
            } else {
                isValid = false;
            }

        }

        if (!isValid) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("change-password-error.xhtml");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }

        log.info("checkToken() end");
    }

    public String changePassword() {
        log.info("changePassword() start");
        if (userAcId != null) {
            UserAccountVO userAccountVO = userAccountClientService.findOne(userAcId);
            userAccountVO.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userAccountClientService.save(userAccountVO);
            userPwdHistClientService.saveUserPwdHist(new UserPwdHistVO(null, userAccountVO.getUserAcId(),
                userAccountVO.getPassword(), appResourceUtils.getBusinessDate(), CoreConstant.STATUS_ACTIVE));
        }
        log.info("changePassword() end");
        return "change-password-ack";
    }

}
