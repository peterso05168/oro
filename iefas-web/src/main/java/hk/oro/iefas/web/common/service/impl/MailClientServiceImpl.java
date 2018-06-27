/**
 * 
 */
package hk.oro.iefas.web.common.service.impl;

import org.springframework.stereotype.Service;

import hk.oro.iefas.core.constant.RequestUriConstant;
import hk.oro.iefas.domain.system.vo.EmailTemplateVO;
import hk.oro.iefas.web.common.service.MailClientService;
import hk.oro.iefas.web.core.service.BaseClientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2545 $ $Date: 2018-05-23 17:41:26 +0800 (週三, 23 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class MailClientServiceImpl extends BaseClientService implements MailClientService {

    @Override
    public void sendModuleMail(EmailTemplateVO emailTemplate) {
        log.info("sendModuleMail() start - " + emailTemplate);
        postForEntity(RequestUriConstant.CLIENT_URI_EMAIL_SEND_MODULE_EMAIL, emailTemplate, Void.class);
        log.info("sendModuleMail() end");
    }

}
