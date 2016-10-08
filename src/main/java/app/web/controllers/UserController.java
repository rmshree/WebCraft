package app.web.controllers;

import app.web.domain.User;
import app.web.services.CookieService;
import app.web.services.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import static javax.management.Query.value;


@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CookieService cookieService;


    @RequestMapping(value = "get/{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    @RequestMapping(value = "getByEmail/{email}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    @RequestMapping(value = "create/{username}", method = RequestMethod.PUT)
    public User create(@PathVariable String username, @RequestBody User userDetails) {
        User user = userService.getUserByUsername(username);
        if (user == null){
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(DatatypeConverter.printBase64Binary(userDetails.getPassword().getBytes()));
            return userService.save(newUser);
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "getCurrent", method = RequestMethod.GET)
    public Object getCurrentUser() {
            cookieService.setCurrentUser();
            return cookieService.getValueFromCookie();

    }


}