package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "matchplayer")
public class MatchPlayer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name ="FK_MatchResult"), name = "match_id", referencedColumnName = "id")
    @JsonProperty
    private MatchResult matchResult;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name ="FK_User"), name = "user_id", referencedColumnName = "id")
    @JsonProperty
    private User user;

    @Column(name = "elo_at_match")
    @JsonProperty
    private Integer elo;

    @Column(name = "player_win")
    @JsonProperty
    private Boolean won;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getElo() {
        return elo;
    }

    public void setElo(Integer elo) {
        this.elo = elo;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }
}
