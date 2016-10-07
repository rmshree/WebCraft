package app.web.data;

import app.web.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer>{

    @Query("select p from Post p")
    List <Post> getAllPost();
}
