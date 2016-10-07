package app.web.data;

import app.web.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>  {

    @Query("select c from Comment c where c.post.id = ?1")
    List <Comment> getCommentsByPost(Integer id);

}
