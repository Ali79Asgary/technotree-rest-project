package com.example.technotree.service;

import com.example.technotree.domain.Comment;
import com.example.technotree.domain.Post;

import java.util.List;

public interface PostService {

    List<Post> findAllPosts();

    Post findPostById(Long id);

    List<Comment> findCommentsByPostId(Long postId);

    List<Post> findPostsByTitleKeyword(String titleKeyword);

    Post savePost(Post post);

    List<Post> saveAllPosts(List<Post> posts);

    Post patchPostById(Post newPost, Long id);

    void deletePostById(Long id);
}
