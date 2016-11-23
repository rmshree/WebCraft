package app.web.controllers;

import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.Password;
import app.web.domain.Settings;
import app.web.domain.TempUser;
import app.web.domain.User;
import app.web.services.*;
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

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private TempUserService tempUserService;

    @Autowired
    private PasswordService passwordService;

    /**
     * /api/login/signUp/{username}
     * \brief Creates and saves a new User into the User database.
     * \param username is the associated username.
     * \param userDetails is a User that contains all of data associated with the user.
     * \return the saved User or null.
     */
    @RequestMapping(value = "signUp/{apiKey}", method = RequestMethod.POST)
    public ResponseDTO signUp(@PathVariable String apiKey, @RequestBody TempUser tempUser) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (apiKey.equals("Nitta160")) {
            if (userService.getUserByEmail(tempUser.getEmail()) != null || tempUserService.getTempUserByEmail(tempUser.getEmail()) != null) {
                // email account already taken
                responseDTO.setMessage("Email is already taken.");
                responseDTO.setSuccess(false);
                return responseDTO;
            }
            if (userService.getUserByUsername(tempUser.getUsername()) == null && tempUserService.getTempUserByUsername(tempUser.getUsername()) == null) {
                TempUser newUser = new TempUser();

                newUser.setUsername(tempUser.getUsername());
                newUser.setPassword(tempUser.getPassword());
                newUser.setFirstName(tempUser.getFirstName());
                newUser.setLastName(tempUser.getLastName());
                newUser.setEmail(tempUser.getEmail());

                emailService.sendVerificationEmail(newUser);
                cookieService.setCurrentUser(null);

                responseDTO.setData(tempUserService.save(newUser));
                responseDTO.setMessage("SUCCESS");
                responseDTO.setSuccess(true);
                return responseDTO;
            } else {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Username is already in use");
                return responseDTO;
            }
        }
        else {
            responseDTO.setMessage("Access denied.");
            responseDTO.setSuccess(false);
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
    @RequestMapping(value = "web/{username}/{apiKey}", method = RequestMethod.PUT)
    public ResponseDTO userLoginWeb(@PathVariable String username, @PathVariable String apiKey, @RequestBody String password) {
        if (apiKey.equals("Nitta160")) {
            return loginService.logInUser(username, password, true);
        }
        else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Access denied");
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

    /**
     * /api/login/platform/{username}
     * \brief Logins in a user given username and password. Only used for Platforms, and multiplayer.
     * \param username is the associated username.
     * \param RequestBody is a String of password.
     * \return a user object or null.
     */
    @RequestMapping(value = "platform/{username}/{apiKey}", method = RequestMethod.PUT)
    public ResponseDTO userLoginPlatform(@PathVariable String username, @PathVariable String apiKey, @RequestBody String password) {
        if (apiKey.equals("Nitta160")) {
            return loginService.logInUser(username, password, false);
        }
        else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Access denied");
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

    /**
     * /api/login/logout
     * \brief Logout for a user given a username. Only used for WebSite
     * \param username is the associated username.
     * \return user object.
     */
    @RequestMapping(value = "logout/web/{apiKey}", method = RequestMethod.PUT)
    public ResponseDTO userLogoutWeb(@PathVariable String apiKey, @RequestBody String username) {
        if (apiKey.equals("Nitta160")) {
            return loginService.logOutUser(username, true);
        }
        else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Access denied");
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

    /**
     * /api/login/logout
     * \brief Logout for a user given a username. Only used for Platforms, and Multiplayer
     * \param username is the associated username.
     * \return user object.
     */
    @RequestMapping(value = "logout/platform/{apiKey}", method = RequestMethod.PUT)
    public ResponseDTO userLogoutPlatform(@PathVariable String apiKey, @RequestBody String username) {
        if (apiKey.equals("Nitta160")) {
            return loginService.logOutUser(username, false);
        }
        else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Access denied");
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

    /**
     * /api/login/activate/{userKey}
     * \brief Finds a user by userKey and sets the user to isActive = true
     * \param userKey is the associated userKey.
     * \return a user object or null.
     */
    @RequestMapping(value = "activate/{userKey}/{apiKey}", method = RequestMethod.GET)
    public ResponseDTO activateUserAccount(@PathVariable String userKey, @PathVariable String apiKey) {
        TempUser tempUser = tempUserService.getTempUserByVerificationKey(userKey);
        ResponseDTO responseDTO = new ResponseDTO();
        if (apiKey.equals("Nitta160")) {
            if (tempUser != null) {
                User user = new User();
                Password password = new Password();

                user.setUsername(tempUser.getUsername());
                user.setFirstName(tempUser.getFirstName());
                user.setLastName(tempUser.getLastName());
                user.setEmail(tempUser.getEmail());

                password.setPassword(tempUser.getPassword());
                password.setUser(userService.save(user));
                passwordService.save(password);

                tempUserService.deleteTempUser(tempUser);
                cookieService.setCurrentUser(user);
                user.setCurrentlyOnsite(true);

                Settings settings = new Settings();
                settings.setUser(user);
                settingsService.save(settings);

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
        else {
            responseDTO.setMessage("Access denied");
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
    @RequestMapping(value = "passwordRecovery/{apiKey}", method = RequestMethod.PUT)
    public ResponseDTO passwordRecovery(@PathVariable String apiKey, @RequestBody String email) {
        User user = userService.getUserByEmail(email);
        ResponseDTO responseDTO = new ResponseDTO();
        if (apiKey.equals("Nitta160")) {
            if (user == null) {
                responseDTO.setMessage("No active account created with " + email);
                responseDTO.setSuccess(false);
                return responseDTO;
            } else {
                emailService.sendPasswordRecoveryEmail(user);
                responseDTO.setData(null);
                responseDTO.setMessage("Password Recovery email has been sent");
                responseDTO.setSuccess(true);
                return responseDTO;
            }
        }
        else {
            responseDTO.setMessage("Access denied");
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

}
