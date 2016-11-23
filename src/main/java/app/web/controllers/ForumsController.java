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
    @RequestMapping(value = "all/{apiKey}", method = RequestMethod.GET)
    public List<Post> all(@PathVariable String apiKey) {
        if (apiKey.equals("Nitta160")) {
            return postService.getAllPost();
        }
        return null;
    }

    /** /api/forums/get/{id}
     *  \brief get a Post by using its post id.
     *  \param id is an Integer.
     *  \return a Post.
     */
    @RequestMapping(value = "get/{id}/{apiKey}", method = RequestMethod.GET)
    public Post get(@PathVariable Integer id, @PathVariable String apiKey) {
        if (apiKey.equals("Nitta160")) {
            return postService.getPostById(id);
        }
        return null;
    }

    /** /api/forums/add
     *  \brief saves a Post to the Post database.
     *  \param post is a Post.
     *  \return a Post.
     */
    @RequestMapping(value = "add/{apiKey}", method = RequestMethod.PUT)
    public ResponseDTO add(@PathVariable String apiKey, @RequestBody Post post) {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userService.getUserByUsername(post.getUser().getUsername());
        if (apiKey.equals("Nitta160")) {
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
        }
        else {
            responseDTO.setMessage("Access denied.");
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

    /** /api/forums/{id}/comments
     *  \brief get all of the comments associated to a post in order of creation.
     *  \param id is an Integer that represents a Post's ID
     *  \return a List of Comments.
     */
    @RequestMapping(value = "{id}/comments/{apiKey}", method = RequestMethod.GET)
    public List<Comment> getComments(@PathVariable Integer id, @PathVariable String apiKey) {
        if (apiKey.equals("Nitta160")) {
            return commentService.getCommentsByPost(id);
        }
        return null;
    }

    /** /api/forums/{id}/add/comment
     *  \brief adds a comment to a post.
     *  \param id is an Integer that represents a Post's ID
     *  \param text is a String that contains the contents of a comment.
     *  \return a Comment.
     */
    @RequestMapping(value = "{id}/add/comment/{apiKey}", method = RequestMethod.PUT)
    public ResponseDTO addComment(@PathVariable Integer id, @PathVariable String apiKey, @RequestBody Comment comment) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (apiKey.equals("Nitta160")) {
            Post post = postService.getPostById(id);
            User user = userService.getUserByUsername(comment.getUser().getUsername());

            if (post != null && user != null) {
                comment.setPost(post);
                post.setComments_length(post.getComments_length() + 1);
                postService.save(post);
                commentService.save(comment);
                responseDTO.setData(comment);
                responseDTO.setSuccess(true);
            } else {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Invalid request.");
            }
        }
        else {
            responseDTO.setMessage("Access denied.");
            responseDTO.setSuccess(false);
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
    @RequestMapping(value = "comment/edit/{id}/{apiKey}", method = RequestMethod.POST)
    public ResponseDTO editComment(@PathVariable Integer id, @PathVariable String apiKey, @RequestBody Comment editComment) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (apiKey.equals("Nitta160")) {
            Comment comment = commentService.getCommentByID(id);
            if (comment != null) {
                if (comment.getUser().getId().equals(editComment.getUser().getId())) {
                    comment.setText(editComment.getText());
                    responseDTO.setData(commentService.save(comment));
                    responseDTO.setSuccess(true);
                } else {
                    responseDTO.setSuccess(false);
                    responseDTO.setMessage("Editing user and original user are not the same.");
                }
            } else {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Editing comment that does not exist.");
            }
        }
        else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Access denied.");
        }
        return responseDTO;
    }

    @RequestMapping(value = "post/edit/{id}/{apiKey}", method = RequestMethod.POST)
    public ResponseDTO editPost(@PathVariable Integer id, @PathVariable String apiKey, @RequestBody Post editPost) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (apiKey.equals("Nitta160")) {
            Post post = postService.getPostById(id);
            if (post != null) {
                if (post.getUser().getId().equals(editPost.getUser().getId())) {
                    responseDTO.setSuccess(true);
                    responseDTO.setData(postService.save(editPost));
                } else {
                    responseDTO.setSuccess(false);
                    responseDTO.setMessage("Editing user is not original user.");
                }
            } else {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Editing post that does not exist.");
            }
        }
        else {
            responseDTO.setMessage("Access denied.");
            responseDTO.setSuccess(false);
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
    @RequestMapping(value = "comment/uploadFile/{id}/{apiKey}", method = RequestMethod.POST)
    public ResponseDTO uploadCommentFile(@PathVariable Integer id, @PathVariable String apiKey, MultipartFile file) {
        ResponseDTO responseDTO = new ResponseDTO();
        Comment comment = commentService.getCommentByID(id);
        if (apiKey.equals("Nitta160")) {
            if (comment != null) {
                try {
                    ObjectMetadata objectMetadata = new ObjectMetadata();
                    objectMetadata.setContentType(file.getContentType());
                    DateTime dateTime = new DateTime();
                    String key = "comments/" + comment.getId().toString() + '/' + comment.getUser().getUsername() + '/' + dateTime.toString() + '/' + file.getOriginalFilename();
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
        else {
            responseDTO.setMessage("Access denied.");
            responseDTO.setSuccess(false);
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
    @RequestMapping(value = "post/uploadFile/{id}/{apiKey}", method = RequestMethod.POST)
    public ResponseDTO uploadPostFile(@PathVariable Integer id, @PathVariable String apiKey, MultipartFile file) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (apiKey.equals("Nitta160")) {
            Post post = postService.getPostById(id);
            if (post != null) {
                try {
                    ObjectMetadata objectMetadata = new ObjectMetadata();
                    objectMetadata.setContentType("image/jpeg");
                    DateTime dateTime = new DateTime();
                    String key = "post/" + post.getId().toString() + '/' + post.getUser().getUsername() + '/' + dateTime.toString() + '/' + file.getOriginalFilename();
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
        else {
            responseDTO.setMessage("Access denied.");
            responseDTO.setSuccess(false);
            return responseDTO;
        }
    }

    /** /api/forums/comment/delete/{id}
     *  \brief deletes the comment associated with {id}
     *  \param id is an Integer that represents a comment's ID
     *  \return number of entries deleted in Integer. Expected 1 or 0.
     */
    //TODO: Have requestBody take in user instead of hardcoding for root.
    @RequestMapping(value = "comment/delete/{id}/{apiKey}", method = RequestMethod.DELETE)
    public Integer deleteComment(@PathVariable Integer id, @PathVariable String apiKey) {
        Comment comment = commentService.getCommentByID(id);
        if (comment != null && (apiKey.equals("Nitta160"))) {
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
    @RequestMapping(value = "post/delete/{id}/{apiKey}", method = RequestMethod.DELETE)
    public Integer deletePost(@PathVariable Integer id, @PathVariable String apiKey) {
        Post post = postService.getPostById(id);
        if (post != null && (apiKey.equals("Nitta160"))) {
            if (commentService.deleteAllCommentsFromPost(id) >= 0) {
                return postService.deletePost(id);
            }
        }
        return 0;
    }

    @RequestMapping(value = "post/category/{category}/{apiKey}", method = RequestMethod.GET)
    public List<Post> getPostByCategory(@PathVariable Integer category, @PathVariable String apiKey) {
        if (apiKey.equals("Nitta160")) {
            return postService.getPostByCategory(category);
        }
        return null;
    }

}
