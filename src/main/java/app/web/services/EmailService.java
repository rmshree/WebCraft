package app.web.services;

import app.web.domain.TempUser;
import app.web.domain.User;

public interface EmailService {

    /**   void sendVerificationEmail(User user);
     *  \brief Sends a verification email to the user's email.
     *  \param user is the User that is to be created.
     *  \return true when email is sent.
     */
    Boolean sendVerificationEmail(TempUser tempUser);

    Boolean sendPasswordRecoveryEmail(User user);

    Boolean sendNewMessageEmail(User user, String content);
}
