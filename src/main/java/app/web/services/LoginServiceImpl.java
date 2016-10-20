package app.web.services;

import app.web.domain.DTOs.ResponseDTO;
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

    @Override
    public ResponseDTO logInUser(String username, String inputPassword, Boolean isWeb) {
        User user = userService.getUserByUsername(username);
        ResponseDTO responseDTO = new ResponseDTO();
        if (user != null) {
            String storedPassword = user.getPassword();
            if (inputPassword.equals(storedPassword)) {
                if (user.getIsActive()) {
                    //Web or Platform?
                    if (isWeb) {
                        user.setCurrentlyOnsite(true);
                        cookieService.setCurrentUser(user);
                        responseDTO.setMessage("SUCCESS");
                        responseDTO.setSuccess(true);
                        responseDTO.setData(userService.save(user));
                        return responseDTO;
                    } else { // is platform
                        user.setCurrentlyOnline(true);
                        responseDTO.setMessage("SUCCESS");
                        responseDTO.setSuccess(true);
                        responseDTO.setData(userService.save(user));
                        return responseDTO;
                    }
                } else {
                    // user isn't active yet
                    responseDTO.setMessage("Please activate your account first");
                    responseDTO.setSuccess(false);
                    return responseDTO;
                }
            } else {
                // incorrect password
                responseDTO.setMessage("Incorrect password");
                responseDTO.setSuccess(false);
                return responseDTO;
            }
        } else {
            responseDTO.setMessage("No account found with " + username);
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

}
