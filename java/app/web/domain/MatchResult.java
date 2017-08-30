package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jackson.map.util.JSONPObject;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "matchresult")
public class MatchResult implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @Column(name = "winners")
    @JsonProperty
    private String winners = "";

    @Column(name = "losers")
    @JsonProperty
    private String losers = "";

    @Column(name = "map_name")
    @JsonProperty
    private String mapname = "Unknown";

    @Column(name ="date")
    @JsonProperty
    private Date date = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getWinners() {
        if (winners.contains("/")) {
            return winners.split("/");
        }
        return null;
    }

    public void setWinners(List<User> winnersArray) {
        String winningUsers = "";
        for (User user: winnersArray) {
            winningUsers = winningUsers + user.getUsername() + "/";
        }
        this.winners = winningUsers;
    }

    public String[] getLosers() {
        if (losers.contains("/")) {
            return losers.split("/");
        }
        return null;
    }

    public void setLosers(List<User> losersArray) {
        String losingUsers = "";
        for (User user: losersArray) {
            losingUsers = losingUsers + user.getUsername() + "/";
        }
        this.losers = losingUsers;
    }

    public String getMapname() {
        return mapname;
    }

    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
