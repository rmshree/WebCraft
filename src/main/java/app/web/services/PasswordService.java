package app.web.services;

import app.web.domain.Password;
import app.web.domain.User;

public interface PasswordService {

    Password save(Password password);

    Password  getPasswordByUser(User user);
}
