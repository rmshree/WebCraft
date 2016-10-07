package app.web.controllers;

import app.web.domain.Post;
import app.web.domain.User;
import app.web.services.PostService;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/forums/")
public class ForumsController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Post> all (){
        return postService.getAllPost();
    }

    @RequestMapping(value = "add", method = RequestMethod.PUT)
    public Post add(@RequestBody Post post){
        User user = userService.getUserByUsername("root");
        post.setUser(user);
        return postService.save(post);
    }
}
