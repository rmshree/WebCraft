package app.web.controllers;

import app.web.domain.User;
import app.web.services.UserService;
import app.web.services.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;

/**
 * Created by misht on 10/10/2016.
 */
@RestController
@RequestMapping(value = "/api/user/")

public class LogInController {
    @Autowired
    private UserService userService;
    private CookieService cookieService;

    @RequestMapping(value = "login/{username}", method = RequestMethod.GET)
    public String confirmPassword(String username, String inputPassword){
        User curUser = userService.getUserByUsername(username);
        String userPass = curUser.getPassword();

        if (userPass.equals(inputPassword)) {
            return username;
        }

        return null;
    }

    @RequestMapping(value = "logout/", method = RequestMethod.GET)
    public void userLogout() {
        cookieService.setCurrentUser(null);
    }


}
