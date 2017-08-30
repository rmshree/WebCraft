/** \file PostRepository.java
 * Repository queries for forum posts.
 */
package app.web.data;

import app.web.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer>{

    /** List <Post> getAllPost();
     *  \brief Get all posts that are in the posts database.
     *  \return a list of post.
     */
    @Query("select p from Post p")
    List <Post> getAllPost();

    @Query("select p from Post p where p.category =?1 order by date desc ")
    List <Post> getPostByCategory(Integer category);

    /** Integer deletePost(Integer id);
     *  \brief deletes a post by its Post ID.
     *  \return a Integer, 1 or 0.
     */
    @Modifying
    @Query("delete from Post p where p.id = ?1")
    Integer deletePost(Integer id);
}
