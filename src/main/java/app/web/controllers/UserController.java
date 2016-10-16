/** \file UserController.java
 * Back-End User services that are used by Front-End.
 * Called by using /api/user/
 */

package app.web.controllers;

import app.web.domain.User;
import app.web.services.EmailService;
import app.web.services.FileArchiveService;
import app.web.services.UserService;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FileArchiveService fileArchiveService;

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
            newUser.setUserKey(UUID.randomUUID().toString());
            emailService.sendVerificationEmail(newUser);
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
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType("image/jpeg");
                DateTime now = new DateTime();
                String key = "avatar/" + user.getUsername() + now.toString();
                user.setAvatarUrl(fileArchiveService.upload(imageFile, key, objectMetadata));
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

    @RequestMapping(value = "activate/{userID}", method = RequestMethod.GET)
    public User activateUserAccount(@PathVariable String userKey) {
        User user = userService.getUserByUserKey(userKey);
        if (user != null && !user.isActive()) {
            user.setActive(true);
            userService.save(user);
            return user;
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "passwordRecovery/{email}", method = RequestMethod.GET)
    public boolean passwordRecovery(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user != null && user.isActive())
        {
            return emailService.sendPasswordRecoveryEmail(user);
        }
        return false;
    }

}