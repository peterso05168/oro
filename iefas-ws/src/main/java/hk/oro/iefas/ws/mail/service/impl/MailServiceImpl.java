package hk.oro.iefas.ws.mail.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import hk.oro.iefas.domain.system.entity.EmailTemplate;
import hk.oro.iefas.ws.mail.service.MailService;
import hk.oro.iefas.ws.system.repository.EmailTemplateRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Value(value = "${spring.mail.from}")
    private String from;

    @Value(value = "${spring.mail.default-encoding}")
    private String encoding;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        log.info("sendSimpleMail() start - To: " + to + ", Subject: " + subject + ", Content: " + content);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);
        mailSender.send(message);
        log.info("sendSimpleMail() end");
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        log.info("sendHtmlMail() start - To: " + to + ", Subject: " + subject + ", Content: " + content);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, encoding);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
        log.info("sendHtmlMail() end");
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath)
        throws MessagingException {
        log.info("sendAttachmentsMail() start - To: " + to + ", Subject: " + subject + ", Content: " + content
            + ", FilePath: " + filePath);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, encoding);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, file);
        mailSender.send(message);
        log.info("sendAttachmentsMail() end");
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String fileName,
        InputStreamSource inputStreamSource) throws MessagingException {
        log.info("sendAttachmentsMail() start - To: " + to + ", Subject: " + subject + ", Content: " + content
            + ", FileName: " + fileName);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, encoding);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        helper.addAttachment(fileName, inputStreamSource);
        mailSender.send(message);
        log.info("sendAttachmentsMail() end");
    }

    @Override
    public void sendModuleMail(String to, String moduleCode, String subModuleCode, Map<String, Object> params)
        throws MessagingException, IOException, TemplateException {
        log.info(
            "sendModuleMail() start - To: " + to + ", ModuleCode: " + moduleCode + ", SubModuleCode: " + subModuleCode);
        EmailTemplate emailTemplate
            = emailTemplateRepository.findByModuleCodeAndSubModuleCode(moduleCode, subModuleCode);
        if (emailTemplate != null) {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
            Template template = new Template(emailTemplate.getSubModuleCode(),
                new StringReader(emailTemplate.getEmailContent()), configuration);
            Writer out = new StringWriter();
            template.process(params, out);
            sendHtmlMail(to, emailTemplate.getEmailSubject(), out.toString());
        }

        log.info("sendModuleMail() end");
    }

}
