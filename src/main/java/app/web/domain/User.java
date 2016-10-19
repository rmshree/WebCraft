/** \class User
 *  \brief This is the User class.
 *
 * The User class contains confidential information regarding the user in addition to the user's win/loss record.
 */

package app.web.domain;

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

    @Column(name = "password", nullable = false)
    @JsonProperty
    private String password;

    @Column(name = "email", unique = true)
    @JsonProperty
    private String email;

    @Column(name = "avatar_url", unique = true)
    @JsonProperty
    private String avatarUrl;

    @Column(name = "isActive")
    @JsonProperty
    private Boolean isActive = false;

    @Column(name = "veriKey")
    @JsonProperty
    private String veriKey;

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getVeriKey() {
        return veriKey;
    }

    public void setVeriKey(String veriKey) {
        this.veriKey = veriKey;
    }
}