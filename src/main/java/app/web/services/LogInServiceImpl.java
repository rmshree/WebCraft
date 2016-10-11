package app.web.services;
import app.web.data.UserRepository;
import app.web.domain.User;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by misht on 10/10/2016.
 */
public class LogInServiceImpl implements LogInService {
        @Autowired
        private UserService userService;

        @Override
        public boolean confirmPassword(String username, String inputPassword){
            User curUser = userService.getUserByUsername(username);

            String userPass = curUser.getPassword();

            if (userPass.equals(inputPassword)) {
                return true;
            }

            return false;
        }
}
