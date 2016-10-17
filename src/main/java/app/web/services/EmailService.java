package app.web.services;

import app.web.domain.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.mail.MailException;

import javax.mail.MessagingException;

public interface EmailService {

    /**   void sendVerificationEmail(User user);
     *  \brief Sends a verification email to the user's email.
     *  \param user is the User that is to be created.
     *  \return true when email is sent.
     */
    boolean sendVerificationEmail(User user);

    boolean sendPasswordRecoveryEmail(User user);
}
