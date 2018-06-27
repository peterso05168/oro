package hk.oro.iefas.ws.mail.service;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.core.io.InputStreamSource;

import freemarker.template.TemplateException;

/**
 * @version $Revision: 2545 $ $Date: 2018-05-23 17:41:26 +0800 (週三, 23 五月 2018) $
 * @author $Author: marvel.ma $
 */
public interface MailService {

    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content) throws MessagingException;

    void sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException;

    void sendAttachmentsMail(String to, String subject, String content, String fileName,
        InputStreamSource inputStreamSource) throws MessagingException;

    void sendModuleMail(String to, String moduleCode, String subModuleCode, Map<String, Object> params)
        throws MessagingException, IOException, TemplateException;

}
