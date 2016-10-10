/** \file PostService.java
 * Backend service that works with the Post Database.
 */

package app.web.services;

import app.web.domain.Post;

import java.util.List;

public interface PostService {

    /** Post save(Post post);
     *  \brief Saves post into the Post Database.
     *  \param post is a Post.
     *  \return the saved Post or null.
     */
    Post save(Post post);

    /** List <Post> getAllPost();
     *  \brief Get all posts that are in the posts database.
     *  \return a list of post.
     */
    List<Post> getAllPost();

    /** Post getPostById(Integer id);
     *  \brief get a Post by it's Post ID.
     *  \return a Post or null.
     */
    Post getPostById(Integer id);

}
