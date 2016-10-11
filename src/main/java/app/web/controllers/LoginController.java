package app.web.controllers;

import app.web.domain.User;
import app.web.services.CookieService;
import app.web.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/login/")

public class LoginController {


    @Autowired
    private CookieService cookieService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "{username}", method = RequestMethod.PUT)
    public User userLogin(@PathVariable String username, @RequestBody String password){
        return loginService.logInUser(username,password);

    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void userLogout() {
        cookieService.setCurrentUser(null);
    }


}
