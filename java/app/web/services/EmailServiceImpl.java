package app.web.services;

import app.web.domain.Password;
import app.web.domain.Settings;
import app.web.domain.TempUser;
import app.web.domain.User;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;

import java.util.Properties;


@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    private static final String USERNAME = "ecs160web";
    private static final String PASSWORD = "Nitta160";
    private static final String EMAIL = "ecs160web@gmail.com";
    private static final String VERIFICATIONSUBJECT = "NittaCraft Email Verification";
    private static final String PASSWORDSUBJECT = "NittaCraft Password Recovery";
    private static final String VERIFICATIONTEMPLATE = "email-templates/verificationEmail.vm";
    private static final String MESSAGETEMPLATE = "email-templates/newMessageEmail.vm";
    private static final String PASSWORDTEMPLATE = "email-templates/passwordRecoveryEmail.vm";
    private static final String LOCALADDRESS = "http://localhost:8080/webcraft/#/";
    private static final String PRODADDRESS = "http://a-out.me/webcraft/#/";

    @Autowired
    private Environment env;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private SettingsService settingsService;

    private Properties props;

    private Session session;

    @Override
    public Boolean sendVerificationEmail(TempUser tempUser) {
        setProperties();
        try {
            String verifcationLink = "";
            Message message = new MimeMessage(session);
            StringWriter writer = new StringWriter();
            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            velocityEngine.init();
            Template template = velocityEngine.getTemplate(VERIFICATIONTEMPLATE);
            VelocityContext velocityContext = new VelocityContext();


            if (env.getActiveProfiles()[0].equals("local")) {
                verifcationLink = LOCALADDRESS + "verify/" + tempUser.getVerificationKey();
            }
            else {
                verifcationLink = PRODADDRESS + "verify/" + tempUser.getVerificationKey();
            }

            velocityContext.put("user", tempUser);
            velocityContext.put("verificationLink", verifcationLink);
            template.merge(velocityContext, writer);

            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(tempUser.getEmail()));
            message.setSubject(VERIFICATIONSUBJECT);
            message.setContent(writer.toString(),"text/html");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean sendNewMessageEmail(User user, String content){
        setProperties();
        try {
            Settings settings = settingsService.getByUser(user);
            Message message = new MimeMessage(session);
            StringWriter writer = new StringWriter();
            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            velocityEngine.init();
            Template template = velocityEngine.getTemplate(MESSAGETEMPLATE);
            VelocityContext velocityContext = new VelocityContext();

            String link = "";
            String settingsLink = "";
            String settingsContent = "";
            if (env.getActiveProfiles()[0].equals("local")) {
                link = LOCALADDRESS + "messages";
                settingsLink = LOCALADDRESS + "settings";
            }
            else {
                link = PRODADDRESS + "messages";
                settingsLink = PRODADDRESS + "settings";
            }
            if (settings.getDelay() == 0) {
                settingsContent = "Your settings are currently set to send a notification email immediately after you receive a private message.\n" +
                        "If you wish to change or remove this setting, please visit your settings at:";
            }
            else {
                settingsContent = "Your settings are currently set to send a notification email "+ settings.getDelay() + " " + settings.getDelayUnit() +" after you receive a private message.\n" +
                        "If you wish to change or remove this setting, please visit your settings at:";
            }

            velocityContext.put("user", user);
            velocityContext.put("content", content);
            velocityContext.put("settings", settings);
            velocityContext.put("link", link);
            velocityContext.put("settingsLink", settingsLink);
            velocityContext.put("settingsContent", settingsContent);

            template.merge(velocityContext, writer);

            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject("New Message on NittaCraft");
            message.setContent(writer.toString(),"text/html");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean sendPasswordRecoveryEmail(User user) {
        setProperties();
        try {
            Password password = passwordService.getPasswordByUser(user);
            Message message = new MimeMessage(session);
            StringWriter writer = new StringWriter();
            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            velocityEngine.init();
            Template template = velocityEngine.getTemplate(PASSWORDTEMPLATE);
            VelocityContext velocityContext = new VelocityContext();

            String settingsLink = "";
            if (env.getActiveProfiles()[0].equals("local")) {
                settingsLink = LOCALADDRESS + "settings";
            }
            else {
                settingsLink = PRODADDRESS + "settings";
            }

            velocityContext.put("user", user);
            velocityContext.put("password", password);
            velocityContext.put("settingsLink", settingsLink);
            template.merge(velocityContext, writer);

            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject(PASSWORDSUBJECT);
            message.setContent(writer.toString(),"text/html");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void setProperties() {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME,PASSWORD);
                    }
                });
    }
}
