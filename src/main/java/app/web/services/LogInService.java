package app.web.services;
import app.web.domain.User;


/**
 * Created by misht on 10/10/2016.
 */

public interface LogInService {
    //User getUserByUsername(String username);
    /**
     *
     * @param password will be taken from the H2 database
     *                 and compared to what the user inputs
     */
    boolean confirmPassword(String username, String password);

}