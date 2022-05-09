package com.example.technotree.service;

import com.example.technotree.domain.Comment;
import com.example.technotree.domain.Post;
import com.example.technotree.exception.PostNotFoundException;
import com.example.technotree.repository.PostRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class SimplePostService implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public SimplePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAllPosts() {
        log.info("going to get all posts");
        List<Post> posts = postRepository.findAll();
        if (!posts.isEmpty()) {
            return posts;
        } else {
            log.warn("no post exist");
            throw new PostNotFoundException();
        }
    }

    @Override
    public Post findPostById(Long id) {
        log.info("going to get post by id : " + id);
        return postRepository.getById(id);
    }

    @Override
    public List<Comment> findCommentsByPostId(Long postId) {
        log.info("going to get comments of a post by id : " + postId);
        if (postRepository.existsById(postId)) {
            return postRepository.findCommentsByPostId(postId);
        } else {
            log.warn("no post exist by id : " + postId);
            throw new PostNotFoundException();
        }
    }

    @Override
    public List<Post> findPostsByTitleKeyword(String titleKeyword) {
        log.info("going to get posts by title which contains keyword : "+titleKeyword);
        List<Post> posts = postRepository.findPostsByTitleContains(titleKeyword);
        if (!posts.isEmpty()) {
            return posts;
        } else {
            log.warn("no post exist by keyword : "+titleKeyword);
            throw new PostNotFoundException();
        }
    }

    @Override
    public Post savePost(Post post) {
        log.info("going to save a post");
        return postRepository.save(post);
    }

    @Override
    public List<Post> saveAllPosts(List<Post> posts) {
        log.info("going to save a list of posts");
        return postRepository.saveAll(posts);
    }

    @Override
    public Post patchPostById(Post newPost, Long id) {
        log.info("going to patch a post by id : "+id);
        return postRepository.findById(id).map(post -> {
            post.setUserId(newPost.getUserId());
            post.setTitle(newPost.getTitle());
            post.setBody(newPost.getBody());
            post.setComments(newPost.getComments());
            return postRepository.save(post);
        }).orElseThrow(() -> {
            log.warn("no post exist by id : "+id);
            throw new PostNotFoundException();
        });
    }

    @Override
    public void deletePostById(Long id) {
        log.info("going to delete a post by id : "+id);
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            log.warn("no post exist by id : "+id);
            throw new PostNotFoundException();
        }
    }
}
