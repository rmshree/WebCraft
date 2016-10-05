package app.web.controllers;

import app.web.domain.User;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.DatatypeConverter;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "get/{username}", method = RequestMethod.GET)
    public User getUserByName(@PathVariable String username){
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
            DatatypeConverter.parseBase64Binary(userDetails.getPassword());
            newUser.setPassword(DatatypeConverter.printBase64Binary(userDetails.getPassword().getBytes()));
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