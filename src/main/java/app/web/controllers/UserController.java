/** \file UserController.java
 * Back-End User services that are used by Front-End.
 * Called by using /api/user/
 */

package app.web.controllers;

import app.web.domain.User;
import app.web.services.FileArchiveService;
import app.web.services.UserService;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

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
                if(user.getS3key() != null){
                    fileArchiveService.delete(user.getS3key());
                }
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType("image/jpeg");
                DateTime now = new DateTime();
                String key = "avatar/" + user.getUsername() + now.toString();
                user.setAvatarUrl(fileArchiveService.upload(imageFile, key, objectMetadata));
                user.setS3key(key);
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

    /** /api/user/getOnsiteUsers
     *  \brief looks up users in the database who are currently on site using the isCurrentlyOnsite attribute on User table
     *  \return a list of Users
     */
    @RequestMapping(value="getOnsiteUsers", method = RequestMethod.GET)
    public List<User> getOnsiteUsers() {
        List<User> onsiteUsers = userService.getOnsiteUsers();
        if (onsiteUsers.size() != 0) {
            return onsiteUsers;
        } else {
            return null;
        }
    }

    /** /api/user/getOnlineUsers
     *  \brief looks up users in the database who are currently logged in thru a platform
     *  \return a list of Users
     */
    @RequestMapping(value="getOnlineUsers", method = RequestMethod.GET)
    public List<User> getOnlineUsers() {
        List<User> onlineUsers = userService.getOnlineUsers();
        if (onlineUsers.size() != 0) {
            return onlineUsers;
        } else {
            return null;
        }
    }

    @RequestMapping(value="edit/email/{username}", method = RequestMethod.POST)
    public boolean editEmail(@PathVariable String username, String email){
        User user = userService.getUserByUsername(username);
        if(user != null){
            user.setEmail(email);
            userService.save(user);
            return true;
        }
        else{
            return false;
        }
    }

    @RequestMapping(value="edit/password/{username}", method = RequestMethod.POST)
    public boolean editPassword(@PathVariable String username, String oldPassword, String newPassword){
        User user = userService.getUserByUsername(username);
        if(user != null){
            String currentPassword = user.getPassword();
            if(currentPassword.equals(oldPassword)){
                user.setPassword(newPassword);
                userService.save(user);
                return true;
            }
            else{
                //user entered incorrect current password
                return false;
            }
        }
        else{
            //user does not exist
            return false;
        }
    }


    @RequestMapping(value="edit/name/{username}", method = RequestMethod.POST)
    public boolean editName(@PathVariable String username, String first, String last){
        User user = userService.getUserByUsername(username);
        if(user != null){
            user.setFirstName(first);
            user.setLastName(last);
            userService.save(user);
            return true;
        }
        else{
            //use does not exist
            return false;
        }
    }


}