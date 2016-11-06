/**
 * \file UserController.java
 * Back-End User services that are used by Front-End.
 * Called by using /api/user/
 */

package app.web.controllers;

import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.Password;
import app.web.domain.TempUser;
import app.web.domain.User;
import app.web.services.FileArchiveService;
import app.web.services.PasswordService;
import app.web.services.UserService;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileArchiveService fileArchiveService;

    @Autowired
    private PasswordService passwordService;

    /**
     * /api/user/get/{username}
     * \brief Get User associated with {username}
     * \param username is a String.
     * \return a user or NULL
     */
    @RequestMapping(value = "get/{username}", method = RequestMethod.GET)
    public User getUserByName(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }


    /**
     * /api/user/getByEmail/{email}
     * \brief Get User associated with {email}
     * \param email is a String.
     * \return a user or NULL
     */
    @RequestMapping(value = "getByEmail/{email:.+}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }


    /**
     * /api/user/{username}/upload/avatar
     * \brief Gets an image file from the front-end and makes that User's avatar image
     * \param username is the associated username.
     * \param image file as multipart file. Max size in 20MB.
     * \return the saved User with avatar image url.
     */
    @RequestMapping(value = "{username}/upload/avatar", method = RequestMethod.POST)
    public User uploadAvatar(@PathVariable String username, MultipartFile imageFile) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            try {
                if (user.getS3key() != null) {
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
            } catch (Exception e) {
                e.printStackTrace();
                return user;
            }
        } else {
            return null;
        }
    }


    /**
     * /api/user/getCurrentUser
     * \brief Utilizes cookies to get the current user logged-in user
     * \return the current User
     */
    @RequestMapping(value = "getCurrentUser", method = RequestMethod.GET)
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }


    /**
     * /api/user/getOnsiteUsers
     * \brief looks up users in the database who are currently on site using the isCurrentlyOnsite attribute on User table
     * \return a list of Users
     */
    @RequestMapping(value = "getOnsiteUsers", method = RequestMethod.GET)
    public List<User> getOnsiteUsers() {
        List<User> onsiteUsers = userService.getOnsiteUsers();
        if (onsiteUsers.size() != 0) {
            return onsiteUsers;
        } else {
            return null;
        }
    }


    /**
     * /api/user/getOnlineUsers
     * \brief looks up users in the database who are currently logged in thru a platform
     * \return a list of Users
     */
    @RequestMapping(value = "getOnlineUsers", method = RequestMethod.GET)
    public List<User> getOnlineUsers() {
        List<User> onlineUsers = userService.getOnlineUsers();
        if (onlineUsers.size() != 0) {
            return onlineUsers;
        } else {
            return null;
        }
    }


    @RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        if (allUsers.size() != 0) {
            return allUsers;
        } else {
            //System.out.printf("NO Users");
            return null;
        }
    }

    /**
     * /api/user/update
     * \brief updates the user object with the new values
     * \return a User object
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseDTO update(@RequestBody TempUser tempUser) {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userService.getUserByUsername(tempUser.getUsername());
        if (user != null && user.getId() != null) {
            Password password = passwordService.getPasswordByUser(user);
            if (password != null) {
                password.setPassword(tempUser.getPassword());
                passwordService.save(password);

                user.setFirstName(tempUser.getFirstName());
                user.setLastName(tempUser.getLastName());
                user.setUsername(tempUser.getUsername());
                user.setEmail(tempUser.getEmail());
                user = userService.save(user);

                responseDTO.setData(user);
                responseDTO.setMessage("SUCCESS");
                responseDTO.setSuccess(true);
                return responseDTO;
            }
        }

        responseDTO.setData(null);
        responseDTO.setSuccess(false);
        responseDTO.setMessage("User does not exist.");
        return responseDTO;
    }
    
//    /** /api/forums/all
//     *  \brief Get all posts in the forums/
//     *  \return a list of Post.
//     */
//    @RequestMapping(value = "all", method = RequestMethod.GET)
//    public List<Post> all (){
//        return postService.getAllPost();
//    }
//
//
//
//    /** /api/forums/get/{id}
//     *  \brief get a Post by using its post id.
//     *  \param id is an Integer.
//     *  \return a Post.
//     */
//    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
//    public Post get (@PathVariable Integer id) {
//        return postService.getPostById(id);
//    }
//
}