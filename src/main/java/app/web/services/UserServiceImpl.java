package app.web.services;

import java.net.URL;
import app.web.data.UserRepository;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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
    public User getCurrentUser() {
        return getUserByUsername(cookieService.getValueFromCookie());
    }

    @Override
    public User getUserByVerificationKey(String verikey) {
        return userRepository.getUserByVerificationKey(verikey);
    }

}