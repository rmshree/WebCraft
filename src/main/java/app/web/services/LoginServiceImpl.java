package app.web.services;

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
        public User logInUser(String username, String inputPassword){
            User currentUser = userService.getUserByUsername(username);

            if (currentUser != null) {
                String storedPassword = currentUser.getPassword();
                if (inputPassword.equals(storedPassword)) {
                        currentUser.setCurrentlyOnsite(true);
                        cookieService.setCurrentUser(currentUser);
                        return userService.save(currentUser);
                }
                else {
                    //password is incorrect
                    return null;
                }
            }
            else {
                // username does not exist
                return null;
            }
        }


}
