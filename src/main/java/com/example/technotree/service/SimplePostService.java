package com.example.technotree.service;

import com.example.technotree.domain.Comment;
import com.example.technotree.domain.Post;
import com.example.technotree.exception.PostNotFoundException;
import com.example.technotree.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimplePostService implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public SimplePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        if (!posts.isEmpty()) {
            return posts;
        } else {
            throw new PostNotFoundException();
        }
    }

    @Override
    public Post findPostById(Long id) {
        Post post = postRepository.getById(id);
        return post;
    }

    @Override
    public List<Comment> findCommentsByPostId(Long postId) {
        List<Comment> comments = postRepository.findCommentsByPostId(postId);
        return comments;
    }

    @Override
    public List<Post> findPostsByTitleKeyword(String titleKeyword) {
        List<Post> posts = postRepository.findPostsByTitleContains(titleKeyword);
        if (!posts.isEmpty()) {
            return posts;
        } else {
            throw new PostNotFoundException();
        }
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> saveAllPosts(List<Post> posts) {
        return postRepository.saveAll(posts);
    }

    @Override
    public Post patchPostById(Post newPost, Long id) {
        return postRepository.findById(id).map(post -> {
            post.setUserId(newPost.getUserId());
            post.setTitle(newPost.getTitle());
            post.setBody(newPost.getBody());
            post.setComments(newPost.getComments());
            return postRepository.save(post);
        }).orElseThrow(() -> {
            throw new PostNotFoundException();
        });
    }

    @Override
    public void deletePostById(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            throw new PostNotFoundException();
        }
    }
}
