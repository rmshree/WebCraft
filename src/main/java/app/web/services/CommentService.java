package app.web.services;

import app.web.domain.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    List<Comment> getCommentsByPost(Integer postid);

}
