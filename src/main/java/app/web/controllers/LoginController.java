package app.web.controllers;

import app.web.domain.User;
import app.web.services.CookieService;
import app.web.services.EmailService;
import app.web.services.LoginService;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/login/")

public class LoginController {

    @Autowired
    private CookieService cookieService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    /**
     * /api/login/create/{username}
     * \brief Creates and saves a new User into the User database.
     * \param username is the associated username.
     * \param userDetails is a User that contains all of data associated with the user.
     * \return the saved User or null.
     */
    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public User signUp(@RequestBody User userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        if (user == null) {
            User newUser = new User();
            newUser.setUsername(userDetails.getUsername());
            newUser.setPassword(userDetails.getPassword());
            newUser.setFirstName(userDetails.getFirstName());
            newUser.setLastName(userDetails.getLastName());
            newUser.setIsActive(false);
            emailService.sendVerificationEmail(newUser);
            return userService.save(newUser);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "{username}", method = RequestMethod.PUT)
    public User userLogin(@PathVariable String username, @RequestBody String password) {
        return loginService.logInUser(username, password);

    }

    @RequestMapping(value = "logout", method = RequestMethod.PUT)
    public void userLogout(@RequestBody String username) {
        User user = userService.getUserByUsername(username);
        if(user != null){
            user.setCurrentlyOnline(false);
            user.setCurrentlyOnsite(false);
            userService.save(user);
        }
        cookieService.setCurrentUser(null);
    }

    @RequestMapping(value = "activate/{userKey}", method = RequestMethod.GET)
    public User activateUserAccount(@PathVariable String userKey) {
        User user = userService.getUserByUserKey(userKey);
        if (user != null && !user.getIsActive()) {
            user.setIsActive(true);
            cookieService.setCurrentUser(user);
            return userService.save(user);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "passwordRecovery/{email}", method = RequestMethod.GET)
    public boolean passwordRecovery(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user != null && user.getIsActive()) {
            return emailService.sendPasswordRecoveryEmail(user);
        }
        return false;
    }

}
