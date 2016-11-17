package app.web.services;

import app.web.domain.User;


public interface CookieService {



    void setCurrentUser(User user);

    String getValueFromCookie();



}



