/** \file UserController.java
 * Back-End User services that are used by Front-End.
 * Called by using /api/user/
 */

package app.web.controllers;

import app.web.domain.User;
import app.web.services.CookieService;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.DatatypeConverter;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CookieService cookieService;

    /** /api/user/get/{username}
     *  \brief Get User associated with {username}
     *  \param username is a String.
     *  \return a user or NULL
     */
    @RequestMapping(value = "get/{username}", method = RequestMethod.GET)
    public User getUserByName(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }
    /** /api/user/getByEmail/{email}
     *  \brief Get User associated with {email}
     *  \param email is a String.
     *  \return a user or NULL
     */
    @RequestMapping(value = "getByEmail/{email}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    /** /api/user/create/{username}
     *  \brief Creates and saves a new User into the User database.
     *  \param username is the associated username.
     *  \param userDetails is a User that contains all of data associated with the user.
     *  \return the saved User or null.
     */
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


    @RequestMapping(value="getCurrent", method = RequestMethod.GET)
    public Object getCurrentUser() {
        cookieService.setCurrentUser();
        return cookieService.getValueFromCookie();
    }

}