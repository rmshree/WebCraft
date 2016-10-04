package app.web.services;

import app.web.domain.User;

import java.util.List;

public interface UserService{

    User save(User number);

    User getUserByUsername (String username);
}