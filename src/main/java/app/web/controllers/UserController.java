package app.web.controllers;

import app.web.domain.User;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "create/{name}", method = RequestMethod.GET)
    public User create(@PathVariable String name){
        User user = new User();
        user.setName(name);
        return userService.save(user);
    }

}