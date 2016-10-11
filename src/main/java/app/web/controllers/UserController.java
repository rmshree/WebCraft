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
import org.springframework.web.multipart.MultipartFile;

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
            //newUser.setPassword(DatatypeConverter.printBase64Binary(userDetails.getPassword().getBytes()));
            newUser.setPassword(userDetails.getPassword());
            return userService.save(newUser);
        }
        else {
            return null;
        }
    }

    /** /api/user/{username}/upload/avatar
     *  \brief Gets an image file from the front-end and makes that User's avatar image
     *  \param username is the associated username.
     *  \param image file as multipart file. Max size in 20MB.
     *  \return the saved User with avatar image url.
     */
    @RequestMapping(value = "{username}/upload/avatar", method = RequestMethod.POST)
    public User uploadAvatar(@PathVariable String username, MultipartFile imageFile) {
        User user = userService.getUserByUsername(username);
        if(user != null){
            try{
                System.out.println(imageFile.getInputStream().toString());
                // figure out how to take this multipart file, and upload to AWS S3 servers and get a url for that file back.
                // once we have the url, set that to user's avatar_url object and save + return the user object.
                String urlFromAWS = "";
                user.setAvatarUrl(urlFromAWS);
                userService.save(user);
                return user;
            }catch (Exception e){
                e.printStackTrace();
                return user;
            }
        }else {
            return null;
        }

    }

    /** /api/user/getCurrentUser
     *  \brief Utilizes cookies to get the current user logged-in user
     *  \return the current User
     */
    @RequestMapping(value="getCurrentUser", method = RequestMethod.GET)
    public User getCurrentUser() { return userService.getCurrentUser(); }



}