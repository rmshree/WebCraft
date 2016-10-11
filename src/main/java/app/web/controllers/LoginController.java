package app.web.controllers;

import app.web.domain.User;
import app.web.services.LoginService;
import app.web.services.UserService;
import app.web.services.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;


@RestController
@RequestMapping(value = "/api/login/")

public class LoginController {


    @Autowired
    private CookieService cookieService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "{username}/{password}", method = RequestMethod.GET)
    public User userLogin(@PathVariable String username, @PathVariable String password){
        return loginService.logInUser(username,password);

    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void userLogout() {
        cookieService.setCurrentUser(null);
    }


}
