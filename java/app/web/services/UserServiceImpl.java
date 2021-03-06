package app.web.services;

import app.web.data.UserRepository;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CookieService cookieService;

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername (String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getUserByEmail (String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getUserByID(String id){
        return userRepository.findOne(id);
    }

    @Override
    public User getCurrentUser() {
        return getUserByUsername(cookieService.getValueFromCookie());
    }

    @Override
    public List<User> getOnsiteUsers() {return userRepository.getOnsiteUsers();}

    @Override
    public List<User> getOnlineUsers() {return userRepository.getOnlineUsers();}

    @Override
    public List<User> getAllUsers() {return userRepository.getAllUsers();}
}