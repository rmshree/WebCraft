package app.web.controllers;

import app.web.domain.DTOs.ResponseDTO;
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
    public ResponseDTO signUp(@RequestBody User userDetails) {
        ResponseDTO responseDTO = new ResponseDTO();
        if(userService.getUserByEmail(userDetails.getEmail()) != null){
            // email account already taken
            responseDTO.setMessage("Email is already take. Please use forgot password to recover your password");
            responseDTO.setSuccess(false);
            return responseDTO;
        }
        if (userService.getUserByUsername(userDetails.getUsername()) == null) {
            User newUser = new User();
            newUser.setUsername(userDetails.getUsername());
            newUser.setPassword(userDetails.getPassword());
            newUser.setFirstName(userDetails.getFirstName());
            newUser.setLastName(userDetails.getLastName());
            newUser.setEmail(userDetails.getEmail());
            newUser.setIsActive(false);
            emailService.sendVerificationEmail(newUser);
            cookieService.setCurrentUser(null);
            responseDTO.setData(userService.save(newUser));
            responseDTO.setMessage("SUCCESS");
            responseDTO.setSuccess(true);
            return responseDTO;
        } else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Username is already in use");
            return responseDTO;
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
    public ResponseDTO userLoginWeb(@PathVariable String username, @RequestBody String password) {
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
    public ResponseDTO userLoginPlatform(@PathVariable String username, @RequestBody String password) {
        return loginService.logInUser(username, password, false);
    }

    /**
     * /api/login/logout
     * \brief Logout for a user given a username. Only used for WebSite
     * \param username is the associated username.
     * \return user object.
     */
    @RequestMapping(value = "logout/web", method = RequestMethod.PUT)
    public ResponseDTO userLogoutWeb(@RequestBody String username) {
        return loginService.logOutUser(username,true);
    }

    /**
     * /api/login/logout
     * \brief Logout for a user given a username. Only used for Platforms, and Multiplayer
     * \param username is the associated username.
     * \return user object.
     */
    @RequestMapping(value = "logout/platform", method = RequestMethod.PUT)
    public ResponseDTO userLogoutPlatform(@RequestBody String username) {
       return loginService.logOutUser(username, false);
    }

    /**
     * /api/login/activate/{userKey}
     * \brief Finds a user by userKey and sets the user to isActive = true
     * \param userKey is the associated userKey.
     * \return a user object or null.
     */
    @RequestMapping(value = "activate/{userKey}", method = RequestMethod.GET)
    public ResponseDTO activateUserAccount(@PathVariable String userKey) {
        User user = userService.getUserByUserKey(userKey);
        ResponseDTO responseDTO = new ResponseDTO();
        if (user != null && !user.getIsActive()) {
            user.setIsActive(true);
            cookieService.setCurrentUser(user);
            user.setCurrentlyOnsite(true);
            responseDTO.setSuccess(true);
            responseDTO.setMessage("SUCCESS");
            responseDTO.setData(userService.save(user));
            return responseDTO;
        } else {
            responseDTO.setData(null);
            responseDTO.setMessage("Incorrect or expired link. Please sign up to login");
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

    /**
     * /api/login/password/{email}
     * \brief Sends a password recovery email to the user
     * \param email is the associated email.
     * \return a Boolean.
     */
    @RequestMapping(value = "passwordRecovery", method = RequestMethod.PUT)
    public ResponseDTO passwordRecovery(@RequestBody String email) {
        User user = userService.getUserByEmail(email);
        ResponseDTO responseDTO = new ResponseDTO();
        if(user == null){
            responseDTO.setMessage("No account created with " + email);
            responseDTO.setSuccess(false);
            return responseDTO;
        }else if(!user.getIsActive()){
            responseDTO.setMessage("Please activate your account");
            responseDTO.setSuccess(false);
            return responseDTO;
        }else if (user.getIsActive()) {
            emailService.sendPasswordRecoveryEmail(user);
            responseDTO.setData(null);
            responseDTO.setMessage("Password Recovery email has been sent");
            responseDTO.setSuccess(true);
            return responseDTO;
        }
        responseDTO.setSuccess(false);
        responseDTO.setMessage("Unexpected error");
        return responseDTO;
    }

}
