package app.web.services;

import app.web.data.PostRepository;
import app.web.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.getAllPost();
    }

    @Override
    public Post getPostById(Integer id){
        return postRepository.findOne(id);
    }

    @Override
    public List<Post> getPostByCategory(Integer category) {
        return postRepository.getPostByCategory(category);
    }

    @Override
    public Integer deletePost(Integer id) {
        return postRepository.deletePost(id);
    }


}
