/** \class Comment
 *  \brief This is the Comment class.
 *
 * The Comment class contains information regarding its parent post, the user who created the comment and the comment text itself.
 */

package app.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_Post"), name = "post_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private Post post;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_User"), name = "user_id", referencedColumnName = "id")
    @JsonProperty
    private User user;

    @Column(name = "s3key", unique = true)
    @JsonIgnore
    private String s3key;

    @Column(name = "fileURL", unique = true)
    @JsonProperty
    private String fileURL;

    @Column(name = "filename")
    @JsonProperty
    private String filename;

    @Column(name = "date", nullable = false)
    @JsonProperty
    private Date date = new Date();

    @Column(name = "text", nullable = false, length = 1000)
    @JsonProperty
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getS3key() {
        return s3key;
    }

    public void setS3key(String s3key) {
        this.s3key = s3key;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
