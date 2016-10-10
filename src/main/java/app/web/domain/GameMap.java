package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "gamemap")
public class GameMap {

    @Id
    @JsonProperty
    private String id = UUID.randomUUID().toString();

    @Column(name = "title", nullable = false)
    @JsonProperty
    private String title;

    @Column(name = "description")
    @JsonProperty
    private String description;

    @Column(name = "primary_image_url")
    @JsonProperty
    private String primaryImageUrl;

    @Column(name = "secondary_image_url")
    @JsonProperty
    private String secondaryImageUrl;

    @Column(name = "download_url")
    @JsonProperty
    private String downloadUrl;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
    }

    public String getSecondaryImageUrl() {
        return secondaryImageUrl;
    }

    public void setSecondaryImageUrl(String secondaryImageUrl) {
        this.secondaryImageUrl = secondaryImageUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
