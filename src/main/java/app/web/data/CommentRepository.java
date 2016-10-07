package app.web.data;

import app.web.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>  {

    @Query("select c from Comment c where c.postID =?1")
    List <Comment> getCommentsByPostID(Integer postid);

}
