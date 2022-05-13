package com.example.technotree.service;

import com.example.technotree.domain.Comment;
import com.example.technotree.exception.CommentNotFoundException;
import com.example.technotree.repository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class SimpleCommentService implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public SimpleCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAllComments() {
        log.info("going to get all comments");
        List<Comment> comments = commentRepository.findAll();
        if (!comments.isEmpty()) {
            return comments;
        } else {
            log.warn("no comment exist");
            throw new CommentNotFoundException();
        }
    }

    @Override
    public Comment findCommentById(Long id) {
        log.info("going to get comment by id : " + id);
        return commentRepository.getById(id);
    }

    @Override
    public List<Comment> findCommentsByPostId(Long postId) {
        log.info("going to get comments of a post by id : " + postId);
        if (commentRepository.existsCommentsByPostId(postId)) {
            return commentRepository.findCommentsByPostId(postId);
        } else {
            log.warn("no comment exist by postId : " + postId);
            throw new CommentNotFoundException();
        }
    }

    @Override
    public Comment saveComment(Comment comment) {
        log.info("going to save a comment");
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> saveAllComments(List<Comment> comments) {
        log.info("going to save a list of comments");
        return commentRepository.saveAll(comments);
    }

    @Override
    public Comment patchCommentById(Comment newComment, Long id) {
        log.info("going to patch a comment by id : "+id);
        return commentRepository.findById(id).map(comment -> {
            comment.setName(newComment.getName());
            comment.setEmail(newComment.getEmail());
            comment.setBody(newComment.getBody());
            comment.setPost(newComment.getPost());
            return commentRepository.save(comment);
        }).orElseThrow(() -> {
            log.warn("no comment exist by id : "+id);
            throw new CommentNotFoundException();
        });
    }

    @Override
    public void deleteCommentById(Long id) {
        log.info("going to delete a comment by id : "+id);
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            log.warn("no comment exist by id : "+id);
            throw new CommentNotFoundException();
        }
    }
}
