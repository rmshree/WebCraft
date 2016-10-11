package app.web.services;
import app.web.domain.User;
import org.apache.http.cookie.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LoginServiceImpl implements LoginService {
        @Autowired
        private UserService userService;

        @Autowired
        private CookieService cookieService;

        @Override
        public User logInUser(String username, String inputPassword){
            User currentUser = userService.getUserByUsername(username);

            if (currentUser.getId() != null) {
                String storedPassword = currentUser.getPassword();
                if (inputPassword.equals(storedPassword)) {
                        cookieService.setCurrentUser(currentUser);
                        return currentUser;
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
