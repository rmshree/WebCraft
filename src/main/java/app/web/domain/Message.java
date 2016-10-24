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
}