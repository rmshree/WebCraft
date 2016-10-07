package app.web.controllers;

import app.web.domain.Comment;
import app.web.domain.Post;
import app.web.domain.User;
import app.web.services.CommentService;
import app.web.services.PostService;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/forums/")
public class ForumsController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

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

    @RequestMapping(value = "{id}/comments", method = RequestMethod.GET)
    public List<Comment> getComments(@PathVariable Integer id){
        return commentService.getCommentsByPost(id);
    }
}
