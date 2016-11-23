package app.web.services;

import app.web.data.PasswordRepository;
import app.web.domain.Password;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PasswordServiceImpl implements PasswordService{

    @Autowired
    private PasswordRepository passwordRepository;

    @Override
    public Password save(Password password) {
        return passwordRepository.save(password);
    }

    @Override
    public Password  getPasswordByUser(User user) {
        return passwordRepository.getPasswordByUser(user);
    }
}
