/** \file PostService.java
 * Backend service that works with the Post Database.
 */

package app.web.services;

import app.web.domain.Comment;

import java.util.List;

public interface CommentService {

    /** Comment save(Comment comment);
     *  \brief Saves comment into the Comment Database.
     *  \param comment is a Comment.
     *  \return the saved Comment or null.
     */
    Comment save(Comment comment);

    /**  Comment getCommentByID(Integer id);
     *  \brief Gets the comment associated with the given id
     *  \param id is the Comment Id in the database.
     *  \return a list of comment.
     */
    Comment getCommentByID(Integer id);

    /** List<Comment> getCommentsByPost(Integer id);
     *  \brief Gets all of the comments related to a Post using the Post ID.
     *  \param id is an integer.
     *  \return a list of Comments.
     */
    List<Comment> getCommentsByPost(Integer id);

}
