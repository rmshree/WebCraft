package app.web.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TempUser")
public class TempUser {
    @Id
    @JsonProperty
    private String verificationKey = UUID.randomUUID().toString();

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

    public String getVerificationKey() {
        return verificationKey;
    }

    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
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
}
