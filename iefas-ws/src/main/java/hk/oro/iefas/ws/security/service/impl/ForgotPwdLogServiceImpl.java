/**
 * 
 */
package hk.oro.iefas.ws.security.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hk.oro.iefas.core.util.DataUtils;
import hk.oro.iefas.domain.security.dto.ForgotPwdLogDTO;
import hk.oro.iefas.domain.security.entity.ForgotPwdLog;
import hk.oro.iefas.ws.security.repository.ForgotPwdLogRepository;
import hk.oro.iefas.ws.security.repository.assembler.ForgotPwdLogAssembler;
import hk.oro.iefas.ws.security.service.ForgotPwdLogService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision $ $Date: 2018-04-23 17:52:58 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
@Slf4j
@Service
public class ForgotPwdLogServiceImpl implements ForgotPwdLogService {

    @Autowired
    private ForgotPwdLogRepository forgotPwdLogRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer save(ForgotPwdLogDTO forgotPwdLogDTO) {
        log.info("save() start - " + forgotPwdLogDTO);
        ForgotPwdLog forgotPwdLog = DataUtils.copyProperties(forgotPwdLogDTO, ForgotPwdLog.class);
        forgotPwdLog = forgotPwdLogRepository.save(forgotPwdLog);
        log.info("save() end");
        return forgotPwdLog.getForgotPwdLogId();
    }

    @Transactional(readOnly = true)
    @Override
    public ForgotPwdLogDTO findOne(Integer id) {
        log.info("findOne() start Id: " + id);
        ForgotPwdLog forgotPwdLog = forgotPwdLogRepository.findOne(id);
        ForgotPwdLogDTO forgotPwdLogDTO = ForgotPwdLogAssembler.toDTO(forgotPwdLog);
        log.info("findOne() end - " + forgotPwdLogDTO);
        return forgotPwdLogDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String createForgotPwdLog(Integer userAcId, Date forgotPwdDate) {
        log.info("createForgotPwdLog() start UserAcId: " + userAcId);
        String token = UUID.randomUUID().toString();
        ForgotPwdLog forgotPwdLog = new ForgotPwdLog(null, userAcId, token, false, forgotPwdDate);
        forgotPwdLogRepository.save(forgotPwdLog);
        log.info("createForgotPwdLog() end");
        return token;
    }

    @Transactional(readOnly = true)
    @Override
    public ForgotPwdLogDTO findByToken(String token) {
        log.info("findByToken() start token: " + token);
        ForgotPwdLog forgotPwdLog = forgotPwdLogRepository.findByToken(token);
        ForgotPwdLogDTO forgotPwdLogDTO = ForgotPwdLogAssembler.toDTO(forgotPwdLog);
        log.info("findByToken() end - " + forgotPwdLogDTO);
        return forgotPwdLogDTO;
    }

}
