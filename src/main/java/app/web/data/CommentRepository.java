/** \file CommentRepository.java
 * Comments queries from the comments database.
 */
package app.web.data;

import app.web.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>  {

    /** List <Comment> getCommentsByPost(Integer id);
     *  \brief Gets all of the comments related to the post in order of creation.
     *  \return a list of comment.
     */
    @Query("select c from Comment c where c.post.id = ?1")
    List <Comment> getCommentsByPost(Integer id);

}
