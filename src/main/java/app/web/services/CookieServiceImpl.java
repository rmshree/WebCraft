package app.web.services;

import com.amazonaws.services.dynamodbv2.xspec.N;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import io.jsonwebtoken.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.apache.taglibs.standard.lang.jstl.StringLiteral.getValueFromToken;

@Service
public class CookieServiceImpl implements CookieService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    /*Declare the secret key, which is defined in app.*.properties file */
    @Value("${jwt.io.secret}")
    private String secretKey;

    private static final Integer SECONDS_IN_A_DAY = 84000;

    @Override
    public void setCurrentUser(User user) {

        if (user != null) {
            Cookie cookie = new Cookie("user_cookie", createSecretToken(user));
            cookie.setMaxAge(SECONDS_IN_A_DAY * 2);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        else { //user is null, i.e logging out
            Cookie cookie = new Cookie("user_cookie", null);
            cookie.setMaxAge(1000);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

    }

    @Override
    public String getValueFromCookie() {
        Cookie[] cookies = request.getCookies();
        String value = "";
        if (cookies != null) {
            for(Cookie cookie: cookies){
                if(cookie.getName().equalsIgnoreCase("user_cookie")) {
                    value = getValueFromToken(cookie.getValue());
                    break;
                }
            }
        }
        return value;
    }


    private String createSecretToken(User user) {

        //JWT Algorithm used to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //Signing the algorithm with our own secret key
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());


        String theirUsername = "";
        if (user.getId() != null) {
            theirUsername = user.getUsername();
        }

        //Set the JWT Claims
        String issuer = "SYSTEM";
        String subject= "Logging In";
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + (TimeUnit.DAYS.toMillis(1)); //Set expiration date to 1 day in the future
        Date exp = new Date(expMillis);

        JwtBuilder builder = Jwts.builder().setId(theirUsername)
                .setIssuedAt(now)
                .setExpiration(exp)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }

    private String getValueFromToken(String token){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(token).getBody();
            return claims.getId();
        }catch (Exception e){
            return null;
        }
    }

}


