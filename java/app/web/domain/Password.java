package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "password")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_User"), name = "user_id", referencedColumnName = "id")
    @JsonProperty
    private User user;

    @Column(name = "password", nullable = false)
    @JsonProperty
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
