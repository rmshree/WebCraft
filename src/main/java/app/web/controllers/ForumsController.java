/** \file ForumsController.java
 * Back-End Forums services that are used by Front-End.
 * Called by using /api/forums/
 */

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

    /** /api/forums/all
     *  \brief Get all posts in the forums/
     *  \return a list of Post.
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Post> all (){
        return postService.getAllPost();
    }

    /** /api/forums/get/{id}
     *  \brief get a Post by using its post id.
     *  \param id is an Integer.
     *  \return a Post.
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Post get (@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    /** /api/forums/add
     *  \brief saves a Post to the Post database.
     *  \param post is a Post.
     *  \return a Post.
     */
    @RequestMapping(value = "add", method = RequestMethod.PUT)
    public Post add(@RequestBody Post post){
        User user = userService.getUserByUsername("root");
        post.setUser(user);
        return postService.save(post);
    }

    /** /api/forums/{id}/comments
     *  \brief get all of the comments associated to a post in order of creation.
     *  \param id is an Integer that represents a Post's ID
     *  \return a List of Comments.
     */
    @RequestMapping(value = "{id}/comments", method = RequestMethod.GET)
    public List<Comment> getComments(@PathVariable Integer id){
        return commentService.getCommentsByPost(id);
    }

    /** /api/forums/{id}/add/comment
     *  \brief adds a comment to a post.
     *  \param id is an Integer that represents a Post's ID
     *  \param text is a String that contains the contents of a comment.
     *  \return a Comment.
     */
    @RequestMapping(value = "{id}/add/comment", method = RequestMethod.PUT)
    public Comment addComment(@PathVariable Integer id, @RequestBody String text){
        Comment comment = new Comment();
        Post post = postService.getPostById(id);
        post.setComments_length(post.getComments_length() + 1);
        comment.setPost(post);
        User user = userService.getUserByUsername("root");
        comment.setUser(user);
        comment.setText(text);
        return commentService.save(comment);
    }
}
