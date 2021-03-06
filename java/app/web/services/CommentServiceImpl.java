package app.web.services;

import app.web.data.CommentRepository;
import app.web.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentByID(Integer id) {
        return commentRepository.getCommentByID(id);
    }

    @Override
    public List<Comment> getCommentsByPost(Integer id) {
        return commentRepository.getCommentsByPost(id);
    }

    @Override
    public Integer deleteCommentFromPost(Integer id) {
        return commentRepository.deleteCommentFromPost(id);
    }

    @Override
    public Integer deleteAllCommentsFromPost(Integer id) {
        return commentRepository.deleteAllCommentsFromPost(id);
    }
}
