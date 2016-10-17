/** \file CommentRepository.java
 * Comments queries from the comments database.
 */
package app.web.data;

import app.web.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.servlet.annotation.MultipartConfig;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>  {

    /**  Comment getCommentByID(Integer id);
     *  \brief Gets the comment associated with the given id
     *  \param id is the Comment Id in the database.
     *  \return a list of comment.
     */
    @Query("select c from Comment c where c.id = ?1")
    Comment getCommentByID(Integer id);

    /** List <Comment> getCommentsByPost(Integer id);
     *  \brief Gets all of the comments related to the post in order of creation.
     *  \param id is the Post Id associated with the comment.
     *  \return a list of comment.
     */
    @Query("select c from Comment c where c.post.id = ?1")
    List <Comment> getCommentsByPost(Integer id);

    /** Integer deleteCommentFromPost(Integer id);
     *  \brief delete the comment associated with post id: {id}
     *  \param id is the Post Id associated with the comment.
     *  \return an integer representing the number of entries deleted. 1 or 0 expected.
     */
    @Modifying
    @Query("delete from Comment c where c.id = ?1")
    Integer deleteCommentFromPost(Integer id);

    /** Integer deleteAllCommentsFromPost(Integer id);
     *  \brief deleted all of the comments associated with post id: {id}
     *  \param id is the Post Id associated with the post.
     *  \return an integer representing the number of entries deleted. 1 or 0 expected.
     */
    @Modifying
    @Query ("delete from Comment c where c.post.id = ?1")
    Integer deleteAllCommentsFromPost(Integer id);
}
