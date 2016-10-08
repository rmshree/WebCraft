package app.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.apache.taglibs.standard.lang.jstl.StringLiteral.getValueFromToken;

@Service
public class CookieService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public void setCurrentUser(){
        Cookie cookie = new Cookie("testCookie", "testValue");
        cookie.setMaxAge(1000000);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public String getValueFromCookie() {
        Cookie[] cookies = request.getCookies();
        String value = "";
        if (cookies != null) {
            for(Cookie cookie: cookies){
                if(cookie.getName().equalsIgnoreCase("testCookie")) {
                    value = cookie.getValue();
                    break;
                }
            }
        }
        return value;
    }

}


