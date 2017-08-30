package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "gamemap")
public class GameMap {

    @Id
    @JsonProperty
    private String id = UUID.randomUUID().toString();

    @Column(name = "title", nullable = false, unique = true)
    @JsonProperty
    private String title;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name ="FK_User"), name = "user_id", referencedColumnName = "id")
    @JsonProperty
    private User user;

    @Column(name = "primary_image_url", length = 500)
    @JsonProperty
    private String primaryImageUrl;

    @Column(name = "download_url", length = 500)
    @JsonProperty
    private String downloadUrl;

    @Column(name = "count")
    @JsonProperty
    private Integer count = 0;

    @Column(name = "number_of_players")
    @JsonProperty
    private Integer numberOfPlayers = 1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
