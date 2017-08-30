package app.web.services;

import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.Password;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private PasswordService passwordService;

    @Override
    public ResponseDTO logInUser(String username, String inputPassword, Boolean isWeb) {
        User user = userService.getUserByUsername(username);
        Password password = passwordService.getPasswordByUser(user);
        ResponseDTO responseDTO = new ResponseDTO();
        if (user != null) {
            String storedPassword = password.getPassword();
            if (inputPassword.equals(storedPassword)) {
                    //Web or Platform?
                if (isWeb) {
                    user.setCurrentlyOnsite(true);
                    cookieService.setCurrentUser(user);
                    responseDTO.setMessage("LOGIN SUCCESS");
                    responseDTO.setSuccess(true);
                    responseDTO.setData(userService.save(user));
                    return responseDTO;
                } else { // is platform
                    user.setCurrentlyOnline(true);
                    responseDTO.setMessage("LOGIN SUCCESS");
                    responseDTO.setSuccess(true);
                    responseDTO.setData(userService.save(user));
                    return responseDTO;
                }

            } else {
                // incorrect password
                responseDTO.setMessage("Incorrect password");
                responseDTO.setSuccess(false);
                return responseDTO;
            }
        } else {
            responseDTO.setMessage("No active account found with " + username);
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

    @Override
    public ResponseDTO logOutUser(String username, Boolean isWeb) {
        User user = userService.getUserByUsername(username);
        ResponseDTO responseDTO = new ResponseDTO();
        if (user != null) {
            //Web or Platform?
            if (isWeb) {
                user.setCurrentlyOnsite(false);
                cookieService.setCurrentUser(null);
                responseDTO.setMessage("LOGOUT SUCCESS");
                responseDTO.setSuccess(true);
                responseDTO.setData(userService.save(user));
                return responseDTO;
            } else { //is platform
                user.setCurrentlyOnline(false);
                responseDTO.setMessage("LOGOUT SUCCESS");
                responseDTO.setSuccess(true);
                responseDTO.setData(userService.save(user));
                return responseDTO;
            }
        } else {
            responseDTO.setMessage("No active account found with " + username);
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

}
