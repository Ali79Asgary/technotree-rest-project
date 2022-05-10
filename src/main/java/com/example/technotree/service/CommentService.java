package com.example.technotree.service;

import com.example.technotree.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllComments();

    Comment findCommentById(Long id);

    List<Comment> findCommentsByPostId(Long postId);

    Comment saveComment(Comment comment);

    List<Comment> saveAllComments(List<Comment> comments);

    Comment patchCommentById(Comment newComment, Long id);

    void deleteCommentById(Long id);
}
