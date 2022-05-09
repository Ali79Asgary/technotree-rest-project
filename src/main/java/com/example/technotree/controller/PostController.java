package com.example.technotree.controller;

import com.example.technotree.domain.Comment;
import com.example.technotree.domain.Post;
import com.example.technotree.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> findAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Post findPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    @GetMapping(path = "/{postId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> findCommentsByPostId(@PathVariable Long postId) {
        return postService.findCommentsByPostId(postId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> findPostsByTitleKeyword(@RequestParam(name = "titleKeyword", required = true) String titleKeyword) {
        return postService.findPostsByTitleKeyword(titleKeyword);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post savePost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> saveAllPosts(@RequestBody List<Post> posts) {
        return postService.saveAllPosts(posts);
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post patchPostById(@RequestBody Post newPost, @PathVariable Long id) {
        return postService.patchPostById(newPost, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
    }
}
