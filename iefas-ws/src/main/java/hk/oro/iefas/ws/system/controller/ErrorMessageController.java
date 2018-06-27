/**
 * 
 */
package hk.oro.iefas.ws.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.dto.ErrorMessageDTO;
import hk.oro.iefas.ws.system.service.ErrorMessageService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_ERROR_MESSAGE)
public class ErrorMessageController {

    @Autowired
    private ErrorMessageService errorMessageService;

    @PostMapping(value = RequestUriConstant.URI_FIND_ALL)
    public List<ErrorMessageDTO> findAll() {
        log.info("findAll() start");
        List<ErrorMessageDTO> result = errorMessageService.findAll();
        log.info("findAll() end - " + result);
        return result;
    }

}
