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
     * /api/login/signUp/{username}
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
            newUser.setEmail(userDetails.getEmail());
            newUser.setIsActive(false);
            emailService.sendVerificationEmail(newUser);
            return userService.save(newUser);
        } else {
            return null;
        }
    }

    /**
     * /api/login/web/{username}
     * \brief Logins in a user given username and password. Only used for Website.
     * \param username is the associated username.
     * \param RequestBody is a String of password.
     * \return a user object or null.
     */
    @RequestMapping(value = "web/{username}", method = RequestMethod.PUT)
    public User userLoginWeb(@PathVariable String username, @RequestBody String password) {
        return loginService.logInUser(username, password, true);

    }

    /**
     * /api/login/platform/{username}
     * \brief Logins in a user given username and password. Only used for Platforms, and multiplayer.
     * \param username is the associated username.
     * \param RequestBody is a String of password.
     * \return a user object or null.
     */
    @RequestMapping(value = "platform/{username}", method = RequestMethod.PUT)
    public User userLoginPlatform(@PathVariable String username, @RequestBody String password) {
        return loginService.logInUser(username, password, false);

    }

    /**
     * /api/login/logout
     * \brief Logout for a user given a username. Only used for WebSite
     * \param username is the associated username.
     * \return void.
     */
    @RequestMapping(value = "logout/web", method = RequestMethod.PUT)
    public User userLogoutWeb(@RequestBody String username) {
        User user = userService.getUserByUsername(username);
        if(user != null){
            user.setCurrentlyOnsite(false);
            cookieService.setCurrentUser(null);
            return userService.save(user);
        }else {
            return null;
        }

    }

    /**
     * /api/login/logout
     * \brief Logout for a user given a username. Only used for Platforms, and Multiplayer
     * \param username is the associated username.
     * \return void.
     */
    @RequestMapping(value = "logout/platform", method = RequestMethod.PUT)
    public User userLogoutPlatform(@RequestBody String username) {
        User user = userService.getUserByUsername(username);
        if(user != null){
            user.setCurrentlyOnline(false);
            return userService.save(user);
        }else{
            return null;
        }
    }

    /**
     * /api/login/activate/{userKey}
     * \brief Finds a user by userKey and sets the user to isActive = true
     * \param userKey is the associated userKey.
     * \return a user object or null.
     */
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

    /**
     * /api/login/password/{email}
     * \brief Sends a password recovery email to the user
     * \param email is the associated email.
     * \return a Boolean.
     */
    @RequestMapping(value = "passwordRecovery/{email}", method = RequestMethod.GET)
    public Boolean passwordRecovery(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user != null && user.getIsActive()) {
            return emailService.sendPasswordRecoveryEmail(user);
        }
        return false;
    }

}
