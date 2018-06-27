/**
 * 
 */
package hk.oro.iefas.ws.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.security.dto.UserAttemptLogDTO;
import hk.oro.iefas.ws.security.service.UserAttemptLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_USER_ATTEMPT_LOG)
public class UserAttemptLogController {

    @Autowired
    private UserAttemptLogService userAttemptLogService;

    @PostMapping(value = RequestUriConstant.URI_SAVE)
    public void save(@RequestBody UserAttemptLogDTO userAttemptLogDTO) {
        log.info("save() start - " + userAttemptLogDTO);
        userAttemptLogService.save(userAttemptLogDTO);
        log.info("save() end");
    }
}
