package app.web.controllers;

import app.web.data.UserRepository;
import app.web.domain.User;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "check/{name}", method = RequestMethod.GET)
    public User check(@PathVariable String name){
        User user = userService.getUserByUsername(name);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    @RequestMapping(value = "create/{name}", method = RequestMethod.PUT)
    public User create(@PathVariable String name, @RequestBody User userDetails) {
        User user = check(name);
        if (user == null){
            User newUser = new User();
            newUser.setUsername(name);
            newUser.setPassword(userDetails.getPassword());
            newUser.setEmail("MockEmail@ECS160.edu");
            newUser.setLoss(0);
            newUser.setWin(0);
            return userService.save(newUser);
        }
        else {
            return null;
        }
    }
}