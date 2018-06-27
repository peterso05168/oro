/**
 * 
 */
package hk.oro.iefas.ws.mail.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.TemplateException;
import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.dto.EmailTemplateDTO;
import hk.oro.iefas.ws.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@RestController
@RequestMapping(value = RequestUriConstant.URI_ROOT_EMAIL)
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping(value = RequestUriConstant.URI_EMAIL_SEND_MODULE_EMAIL)
    public void sendModuleMail(@RequestBody EmailTemplateDTO emailTemplateDTO)
        throws MessagingException, IOException, TemplateException {
        log.info("sendModuleMail() start - " + emailTemplateDTO);
        mailService.sendModuleMail(emailTemplateDTO.getTo(), emailTemplateDTO.getModuleCode(),
            emailTemplateDTO.getSubModuleCode(), emailTemplateDTO.getParams());
        log.info("sendModuleMail() end");
    }

}
