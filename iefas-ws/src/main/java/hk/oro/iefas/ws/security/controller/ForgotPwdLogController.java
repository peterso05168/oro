/**
 * 
 */
package hk.oro.iefas.ws.security.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.CoreConstant;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.dto.ForgotPwdLogDTO;
import hk.oro.iefas.ws.security.service.ForgotPwdLogService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2174 $ $Date: 2018-04-23 17:52:58 +0800 (週一, 23 四月 2018) $
 * @author $Author: Garrett.han $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_FORGOT_PWD_LOG)
public class ForgotPwdLogController {

    @Autowired
    private ForgotPwdLogService forgotPwdLogService;

    @PostMapping(value = RequestUriConstant.URI_SAVE)
    public Integer save(@RequestBody ForgotPwdLogDTO forgotPwdLogDTO) {
        log.info("save() start - " + forgotPwdLogDTO);
        Integer id = forgotPwdLogService.save(forgotPwdLogDTO);
        log.info("save() end Id: " + id);
        return id;
    }

    @PostMapping(value = RequestUriConstant.URI_FIND_ONE)
    public ForgotPwdLogDTO findOne(@RequestBody Integer id) {
        log.info("findOne() start Id: " + id);
        ForgotPwdLogDTO forgotPwdLogDTO = forgotPwdLogService.findOne(id);
        log.info("findOne() end - " + forgotPwdLogDTO);
        return forgotPwdLogDTO;
    }

    @PostMapping(value = RequestUriConstant.URI_FORGOT_PWD_LOG_CREATE)
    public String createForgotPwdLog(Integer userAcId,
        @DateTimeFormat(pattern = CoreConstant.DATE_TIME_PATTERN) Date forgotPwdDate) {
        log.info("createForgotPwdLog() start UserAcId: " + userAcId);
        String token = forgotPwdLogService.createForgotPwdLog(userAcId, forgotPwdDate);
        log.info("createForgotPwdLog() end");
        return token;
    }

    @PostMapping(value = RequestUriConstant.URI_FORGOT_PWD_LOG_FINDBY_TOKEN)
    public ForgotPwdLogDTO findByToken(@RequestBody String token) {
        log.info("findByToken() start - token: " + token);
        ForgotPwdLogDTO forgotPwdLogDTO = forgotPwdLogService.findByToken(token);
        log.info("findByToken() end - " + forgotPwdLogDTO);
        return forgotPwdLogDTO;
    }

}
