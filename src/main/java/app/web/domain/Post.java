/** \class Post
 *  \brief This is the Post class.
 *
 * The Post class contains information on the post's title, the user who created the post, the date of creation and contents of the post.
 */

package app.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;

    @Column(name = "title", nullable = false)
    @JsonProperty
    private String title;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name ="FK_User"), name = "user_id", referencedColumnName = "id")
    @JsonProperty
    private User user;

    @Column(name = "date", nullable = false)
    @JsonProperty
    private Date date = new Date();

    @Column(name = "text", nullable = false, length = 1000)
    @JsonProperty
    private String text;

    @Column(name = "comments_length")
    @JsonProperty
    private Integer comments_length = 0;

    @Column(name = "category")
    @JsonProperty
    private Integer category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getComments_length() {
        return comments_length;
    }

    public void setComments_length(Integer comments_length) {
        this.comments_length = comments_length;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
