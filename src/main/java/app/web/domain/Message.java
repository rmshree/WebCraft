package app.web.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @Column(name = "convo_id", nullable = false)
    @JsonProperty
    private Integer convo_id;

    @Column(name = "message_body", nullable = false, length = 1000)
    @JsonProperty
    private String message_body;

    @Column(name = "create_date", nullable = false)
    @JsonProperty
    private Date create_date = new Date();

    @Column(name = "sender", nullable = false)
    @JsonProperty
    private User sender;

    @Column(name = "receiver", nullable = false)
    @JsonProperty
    private User receiver;

    @Column(name = "read")
    @JsonProperty
    private boolean read;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConvo_id() {
        return convo_id;
    }

    public void setConvo_id(Integer convo_id) {
        this.convo_id = convo_id;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}