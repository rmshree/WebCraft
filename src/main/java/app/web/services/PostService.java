package app.web.services;

import app.web.domain.Post;

import java.util.List;

public interface PostService {

    Post save(Post post);

    List<Post> getAllPost();

    Post getPostById(Integer id);

}
