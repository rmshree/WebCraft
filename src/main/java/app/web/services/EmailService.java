package app.web.services;

import app.web.domain.User;

public interface EmailService {

    /**   void sendVerificationEmail(User user);
     *  \brief Sends a verification email to the user's email.
     *  \param user is the User that is to be created.
     *  \return true when email is sent.
     */
    Boolean sendVerificationEmail(User user);

    Boolean sendPasswordRecoveryEmail(User user);
}
