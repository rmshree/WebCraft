package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @Column(name = "notifications")
    @JsonProperty
    private Boolean notifications = true;

    @Column(name = "delay")
    @JsonProperty
    private Integer delay = 0;

    @Column(name = "delay_unit")
    @JsonProperty
    private String delayUnit = "Minutes";

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name ="FK_User"), name = "user_id", referencedColumnName = "id")
    @JsonProperty
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getNotifications() {
        return notifications;
    }

    public void setNotifications(Boolean notifications) {
        this.notifications = notifications;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public String getDelayUnit() {
        return delayUnit;
    }

    public void setDelayUnit(String delayUnit) {
        this.delayUnit = delayUnit;
    }
}
