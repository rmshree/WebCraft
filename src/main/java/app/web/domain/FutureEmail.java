package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "futureemail")
public class FutureEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name ="FK_User"), name = "user_id", referencedColumnName = "id")
    @JsonProperty
    private User user;

    @Column(name = "content", nullable = false, length = 1000)
    @JsonProperty
    private String content;

    @Column(name = "date", nullable = false)
    @JsonProperty
    private Date date;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
