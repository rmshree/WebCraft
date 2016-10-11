package app.web.services;
import app.web.domain.User;




public interface LoginService {

    /**
     *
     * @param inputPassword will be taken from the H2 database
     *                 and compared to what the user inputs
     */
    User logInUser(String username, String inputPassword);

}