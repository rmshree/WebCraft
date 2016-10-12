/** \class User
 *  \brief This is the User class.
 *
 * The User class contains confidential information regarding the user in addition to the user's win/loss record.
 */

package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URL;


@Entity
@Table(name = "user")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @Column(name = "win")
    @JsonProperty
    private Integer win = 0;

    @Column(name = "loss")
    @JsonProperty
    private Integer loss = 0;

    @Column(name = "username", unique = true)
    @JsonProperty
    private String username;

    @Column(name = "password", nullable = false)
    @JsonProperty
    private String password;

    @Column(name = "email", unique = true)
    @JsonProperty
    private String email;

    @Column(name = "avatar_url", unique = true, length = 500)
    @JsonProperty
    private String avatarUrl;

    // TODO: cleanup
    //S3 amazon client information for storing images
    @Column(name = "user_Image")
    @JsonProperty
    private URL userImage;

    @Column(name = "s3_key", unique = true)
    @JsonProperty
    private String s3_key;


    public void setUserURL(URL url) {userImage = url;}

    public URL getUserURL() { return userImage; }

    public void setS3Key(String key) { s3_key = key; }

    public String getS3Key(){ return s3_key; }

    public Integer getId() {return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLoss() {
        return loss;
    }

    public void setLoss(Integer loss) {
        this.loss = loss;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}