/**
 * 
 */
package hk.oro.iefas.web.security.service.impl;

import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.vo.UserAttemptLogVO;
import hk.oro.iefas.web.core.service.BaseClientService;
import hk.oro.iefas.web.security.service.UserAttemptLogClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2176 $ $Date: 2018-04-23 18:25:01 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
@Slf4j
@Service
public class UserAttemptLogClientServiceImpl extends BaseClientService implements UserAttemptLogClientService {

    @Override
    public void save(UserAttemptLogVO userAttemptLogVO) {
        log.info("save() start - " + userAttemptLogVO);
        postForEntity(RequestUriConstant.CLIENT_URI_USER_ATTEMPT_LOG_SAVE, userAttemptLogVO, Void.class);
        log.info("save() end");
    }

}
