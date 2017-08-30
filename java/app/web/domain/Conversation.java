package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "conversation")
public class Conversation implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name ="FK_User"), name = "user1_id", referencedColumnName = "id")
    @JsonProperty
    private User user1;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name ="FK_User"), name = "user2_id", referencedColumnName = "id")
    @JsonProperty
    private User user2;

    @Column(name = "last_message_time")
    @JsonProperty
    private Date lastMessageTime;

    @Column(name = "last_message")
    @JsonProperty
    private String lastMessage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Date getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(Date lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}