/**
 * 
 */
package hk.oro.iefas.web.common.view.validator;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import hk.oro.iefas.core.util.CommonUtils;
import hk.oro.iefas.domain.search.vo.PageVO;
import hk.oro.iefas.domain.search.vo.SearchVO;
import hk.oro.iefas.domain.security.vo.UserPwdHistVO;
import hk.oro.iefas.web.common.util.AppResourceUtils;
import hk.oro.iefas.web.system.profile.service.UserPwdHistClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2596 $ $Date $
 * @author $Author: vicki.huang $
 */
@Slf4j
@Component
public class PasswordValidator implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String VALIDATOR_ATTR_USERACID = "userAcId";

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    private UserPwdHistClientService userPwdHistClientService;

    @Inject
    private AppResourceUtils appResourceUtils;

    public boolean validateFormat(String password) {
        log.info("validateFormat() start");
        if (CommonUtils.isNotBlank(password)) {
            String passwordRegex = appResourceUtils.getPasswordRegex();
            if (!password.matches(passwordRegex)) {
                return false;
            }

        }
        log.info("validateFormat() end");
        return true;
    }

    public boolean validateExist(String password, Integer userAcId) {
        log.info("validateExist() start");
        if (CommonUtils.isNotBlank(password)) {
            Integer pwdHistCount = appResourceUtils.getPwdHistCount();
            if (pwdHistCount > 0 && userAcId != null) {
                List<UserPwdHistVO> userPwdHistList
                    = userPwdHistClientService.findByUserAcId(new SearchVO<Integer>(userAcId,
                        new PageVO(0, appResourceUtils.getPwdHistCount(), "passwordDate", Direction.DESC.name())));
                boolean anyMatch = userPwdHistList.parallelStream()
                    .anyMatch(pwdHist -> bCryptPasswordEncoder.matches(password, pwdHist.getPreviousPassword()));
                if (anyMatch) {
                    return false;
                }
            }
        }
        log.info("validateExist() end");
        return true;
    }
}
