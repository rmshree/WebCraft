/**
 * \file ForumsController.java
 * Back-End Forums services that are used by Front-End.
 * Called by using /api/forums/
 */

package app.web.controllers;

import app.web.domain.Comment;
import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.Post;
import app.web.domain.User;
import app.web.services.CommentService;
import app.web.services.FileArchiveService;
import app.web.services.PostService;
import app.web.services.UserService;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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

    @Autowired
    private FileArchiveService fileArchiveService;

    /** /api/forums/all
     *  \brief Get all posts in the forums/
     *  \return a list of Post.
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Post> all() {
        return postService.getAllPost();
    }

    /** /api/forums/get/{id}
     *  \brief get a Post by using its post id.
     *  \param id is an Integer.
     *  \return a Post.
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Post get(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    /** /api/forums/add
     *  \brief saves a Post to the Post database.
     *  \param post is a Post.
     *  \return a Post.
     */
    @RequestMapping(value = "add", method = RequestMethod.PUT)
    public ResponseDTO add(@RequestBody Post post) {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userService.getUserByUsername(post.getUser().getUsername());
        if (user != null)
        {
            Post newPost = postService.save(post);
            responseDTO.setData(newPost);
            responseDTO.setSuccess(true);
        }
        else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Invalid user.");
        }
        return responseDTO;
    }

    /** /api/forums/{id}/comments
     *  \brief get all of the comments associated to a post in order of creation.
     *  \param id is an Integer that represents a Post's ID
     *  \return a List of Comments.
     */
    @RequestMapping(value = "{id}/comments", method = RequestMethod.GET)
    public List<Comment> getComments(@PathVariable Integer id) {
        return commentService.getCommentsByPost(id);
    }

    /** /api/forums/{id}/add/comment
     *  \brief adds a comment to a post.
     *  \param id is an Integer that represents a Post's ID
     *  \param text is a String that contains the contents of a comment.
     *  \return a Comment.
     */
    @RequestMapping(value = "{id}/add/comment", method = RequestMethod.PUT)
    public ResponseDTO addComment(@PathVariable Integer id, @RequestBody Comment comment) {
        ResponseDTO responseDTO = new ResponseDTO();
        Post post = postService.getPostById(id);
        User user = userService.getUserByUsername(comment.getUser().getUsername());

        if (post != null && user != null) {
            comment.setPost(post);
            post.setComments_length(post.getComments_length() + 1);
            postService.save(post);
            commentService.save(comment);
            responseDTO.setData(comment);
            responseDTO.setSuccess(true);
        }
        else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Invalid request.");
        }
        return responseDTO;
    }

    /** /api/forums/comment/edit/{id}
     *  \brief edits the comment text that corresponds with {id}
     *  \param id is an Integer that represents a Post's ID
     *  \param text is a String that contains the contents of a comment.
     *  \return a Comment.
     */
    //TODO: Only give the user who created the comment the ability to edit the comment.
    @RequestMapping(value = "comment/edit/{id}", method = RequestMethod.POST)
    public ResponseDTO editComment(@PathVariable Integer id, @RequestBody Comment editComment) {
        ResponseDTO responseDTO = new ResponseDTO();
        Comment comment = commentService.getCommentByID(id);
        if (comment != null) {
            if (comment.getUser().getId().equals(editComment.getUser().getId())) {
                comment.setText(editComment.getText());
                responseDTO.setData(commentService.save(comment));
                responseDTO.setSuccess(true);
            }
            else {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Editing user and original user are not the same.");
            }
        }
        else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Editing comment that does not exist.");
        }
        return responseDTO;
    }

    @RequestMapping(value = "post/edit/{id}", method = RequestMethod.POST)
    public ResponseDTO editPost(@PathVariable Integer id, @RequestBody Post editPost) {
        ResponseDTO responseDTO = new ResponseDTO();
        Post post = postService.getPostById(id);
        if (post != null) {
            if (post.getUser().getId().equals(editPost.getUser().getId())) {
                responseDTO.setSuccess(true);
                responseDTO.setData(postService.save(editPost));
            }
            else {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Editing user is not original user.");
            }
        }
        else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Editing post that does not exist.");
        }
        return responseDTO;
    }

    /**
     * /api/forums/comment/uploadImage/{id}
     * \brief Gets an image file from the front-end and adds to a given comment
     * \param id is an Integer that represents a comment's ID
     * \param image file as multipart file. Max size in 20MB.
     * \return the saved Comment with image url.
     */
    @RequestMapping(value = "comment/uploadFile/{id}", method = RequestMethod.POST)
    public ResponseDTO uploadCommentFile(@PathVariable Integer id, MultipartFile file) {
        ResponseDTO responseDTO = new ResponseDTO();
        Comment comment = commentService.getCommentByID(id);
        if (comment != null) {
            try {
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType(file.getContentType());
                DateTime dateTime = new DateTime();
                String key = "comments/" + comment.getId().toString()+ '/'+ comment.getUser().getUsername() + '/' + dateTime.toString() + '/' + file.getOriginalFilename();
                comment.setFileURL(fileArchiveService.upload(file, key, objectMetadata));
                comment.setS3key(key);
                comment.setFilename(file.getOriginalFilename());
                responseDTO.setSuccess(true);
                responseDTO.setData(commentService.save(comment));
            } catch (Exception e) {
                e.printStackTrace();
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Unable to upload file. Check file size or internet connection.");
            } finally {
                return responseDTO;
            }
        } else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Comment does not exist. ");
            return responseDTO;
        }
    }

    /**
     * /api/forums/post/uploadImage/{id}
     * \brief Gets an image file from the front-end and adds to a given post
     * \param id is an Integer that represents a Post's ID
     * \param image file as multipart file. Max size in 20MB.
     * \return the saved Comment with image url.
     */
    @RequestMapping(value = "post/uploadFile/{id}", method = RequestMethod.POST)
    public ResponseDTO uploadPostFile(@PathVariable Integer id, MultipartFile file) {
        ResponseDTO responseDTO = new ResponseDTO();
        Post post = postService.getPostById(id);
        if (post != null) {
            try {
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType("image/jpeg");
                DateTime dateTime = new DateTime();
                String key = "post/" + post.getId().toString()+ '/'+ post.getUser().getUsername() + '/' + dateTime.toString() + '/' + file.getOriginalFilename();
                post.setFileURL(fileArchiveService.upload(file, key, objectMetadata));
                post.setFilename(file.getOriginalFilename());
                post.setS3key(key);
                responseDTO.setSuccess(true);
                responseDTO.setData(postService.save(post));
            } catch (Exception e) {
                e.printStackTrace();
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Unable to upload file. Check file size or internet connection.");
            } finally {
                return responseDTO;
            }
        } else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Post does not exist. ");
            return responseDTO;
        }
    }

    /** /api/forums/comment/delete/{id}
     *  \brief deletes the comment associated with {id}
     *  \param id is an Integer that represents a comment's ID
     *  \return number of entries deleted in Integer. Expected 1 or 0.
     */
    //TODO: Have requestBody take in user instead of hardcoding for root.
    @RequestMapping(value = "comment/delete/{id}", method = RequestMethod.DELETE)
    public Integer deleteComment(@PathVariable Integer id) {
        Comment comment = commentService.getCommentByID(id);
        if (comment != null) {
            Post post = postService.getPostById(comment.getPost().getId());
            if (post.getComments_length() > 0) {
                post.setComments_length(post.getComments_length() - 1);
                postService.save(post);
                return commentService.deleteCommentFromPost(id);
            }
        }
        return 0;
    }

    /** /api/forums/post/delete/{id}
     *  \brief deletes the post and its comments associated with {id}
     *  \param id is an Integer that represents a post's ID
     *  \return number of entries deleted in Integer. Expected 1 or 0.
     */
    //TODO: Have request body take in user to check if the deleting user is the post creator.
    @RequestMapping(value = "post/delete/{id}", method = RequestMethod.DELETE)
    public Integer deletePost(@PathVariable Integer id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            if (commentService.deleteAllCommentsFromPost(id) >= 0) {
                return postService.deletePost(id);
            }
        }
        return 0;
    }

    @RequestMapping(value = "post/category/{category}", method = RequestMethod.GET)
    public List<Post> getPostByCategory(@PathVariable Integer category) {
        return postService.getPostByCategory(category);
    }

}
