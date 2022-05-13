package com.example.technotree.controller;

import com.example.technotree.domain.Comment;
import com.example.technotree.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> findAllComments() {
        return commentService.findAllComments();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Comment findCommentById(@PathVariable Long id) {
        return commentService.findCommentById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> findCommentsByPostId(@RequestParam(name = "postId", required = true) Long postId) {
        return commentService.findCommentsByPostId(postId);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Comment saveComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> saveAllComments(@RequestBody List<Comment> comments) {
        return commentService.saveAllComments(comments);
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Comment patchCommentById(@RequestBody Comment newComment, @PathVariable Long id) {
        return commentService.patchCommentById(newComment, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCommentById(@PathVariable Long id) {
        commentService.deleteCommentById(id);
    }
}
