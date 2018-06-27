/**
 * 
 */
package hk.oro.iefas.ws.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.UserAttemptLogDTO;
import hk.oro.iefas.domain.security.entity.UserAttemptLog;
import hk.oro.iefas.ws.security.repository.UserAttemptLogRepository;
import hk.oro.iefas.ws.security.service.UserAttemptLogService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class UserAttemptLogServiceImpl implements UserAttemptLogService {

    @Autowired
    private UserAttemptLogRepository userAttemptLogRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(UserAttemptLogDTO userAttemptLogDTO) {
        log.info("save() start - " + userAttemptLogDTO);
        UserAttemptLog userAttempLog = DataUtils.copyProperties(userAttemptLogDTO, UserAttemptLog.class);
        userAttemptLogRepository.save(userAttempLog);
        log.info("save() end");
    }

}
