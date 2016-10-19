/** \class User
 *  \brief This is the User class.
 *
 * The User class contains confidential information regarding the user in addition to the user's win/loss record.
 */
/** Class Definitions **/



package app.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @JsonProperty
    private String id = UUID.randomUUID().toString();

    @Column(name = "win")
    @JsonProperty
    private Integer win = 0;

    @Column(name = "loss")
    @JsonProperty
    private Integer loss = 0;

    @Column(name = "username", unique = true)
    @JsonProperty
    private String username;

    @Column(name = "firstName")
    @JsonProperty
    private String firstName;

    @Column(name = "lastName")
    @JsonProperty
    private String lastName;

    @Column(name = "password", nullable = false)
    @JsonProperty
    private String password;

    @Column(name = "email", unique = true)
    @JsonProperty
    private String email;

    @Column(name = "avatar_url", unique = true)
    @JsonProperty
    private String avatarUrl;

    @Column(name = "s3key", unique = true)
    @JsonIgnore
    private String s3key;

    @Column(name = "isActive")
    @JsonProperty
    private Boolean isActive = false;

    @Column(name = "userKey", unique = true)
    @JsonProperty
    private String userKey = UUID.randomUUID().toString();

    @Column(name = "isCurrentlyOnline") //For users logged in to the game
    @JsonProperty
    private Boolean isCurrentlyOnline = false;

    @Column(name = "isCurrentlyOnsite") //For users logged in to the website
    @JsonProperty
    private Boolean isCurrentlyOnsite = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Boolean getCurrentlyOnline() {
        return isCurrentlyOnline;
    }

    public void setCurrentlyOnline(Boolean currentlyOnline) {
        isCurrentlyOnline = currentlyOnline;
    }

    public Boolean getCurrentlyOnsite() {
        return isCurrentlyOnsite;
    }

    public void setCurrentlyOnsite(Boolean currentlyOnsite) {
        isCurrentlyOnsite = currentlyOnsite;
    }

    public void setS3key(String s3key) { this.s3key = s3key; }

    public String getS3key() {return s3key;}
}