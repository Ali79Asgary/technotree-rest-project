package com.example.technotree.service;

import com.example.technotree.domain.Comment;
import com.example.technotree.domain.Post;
import com.example.technotree.exception.PostNotFoundException;
import com.example.technotree.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class SimplePostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Autowired
    private SimplePostService postService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        postService = new SimplePostService(postRepository);
    }

    @Test
    void testFindAllPosts_PostExists_ReturnPostsList() {
        Post post1 = new Post();
        Post post2 = new Post();
        List<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post2);
        given(postRepository.findAll()).willReturn(postList);
        List<Post> exceptedList = postService.findAllPosts();
        assertThat(exceptedList).isNotNull();
    }

    @Test
    void testFindAllPosts_NoPostExist_ThrowException() {
        List<Post> emptyList = Collections.emptyList();
        given(postRepository.findAll()).willReturn(emptyList);
        Assertions.assertThrows(PostNotFoundException.class, () -> postService.findAllPosts());
    }

    @Test
    void testFindPostById_Successful_ReturnPost() {
        Post post = new Post();
        given(postRepository.getById(1L)).willReturn(post);
        Post excepted = postService.findPostById(1L);
        assertThat(excepted).isNotNull();
    }

    @Test
    void testFindPostById_Unsuccessful_ThrowException() {
    }

    @Test
    void testFindCommentsByPostId_PostExist_ReturnPostList() {
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
        given(postRepository.existsById(1L)).willReturn(Boolean.TRUE);
        given(postRepository.findCommentsByPostId(1L)).willReturn(commentList);
        List<Comment> exceptedList = postService.findCommentsByPostId(1L);
        assertThat(exceptedList).isNotNull();
    }

    @Test
    void testFindCommentsByPostId_NoPostExist_ThrowException() {
        List<Comment> emptyList = Collections.emptyList();
        given(postRepository.findCommentsByPostId(1L)).willReturn(emptyList);
        Assertions.assertThrows(PostNotFoundException.class, () -> postService.findCommentsByPostId(1L));
    }

    @Test
    void testFindPostByTitleKeyword_PostExist_ReturnPostList() {
        Post post1 = new Post();
        Post post2 = new Post();
        List<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post2);
        given(postRepository.findPostsByTitleContains("keyword")).willReturn(postList);
        List<Post> exceptedList = postService.findPostsByTitleKeyword("keyword");
        assertThat(exceptedList).isNotNull();
    }

    @Test
    void testFindPostByTitleKeyword_NoPostExist_ThrowException() {
        List<Post> emptyList = Collections.emptyList();
        given(postRepository.findPostsByTitleContains("keyword")).willReturn(emptyList);
        Assertions.assertThrows(PostNotFoundException.class, () -> postService.findPostsByTitleKeyword("keyword"));
    }

    @Test
    void testSavePost_SaveSuccessful_ReturnPost() {
        Post post = new Post();
        given(postRepository.save(post)).willReturn(post);
        Post excepted = postService.savePost(post);
        assertThat(excepted).isNotNull();
    }

    @Test
    void testSavePost_SaveUnsuccessful_ThrowException() {
    }

    @Test
    void testSaveAllPosts_SaveSuccessful_ReturnPostList() {
        Post post1 = new Post();
        Post post2 = new Post();
        List<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post2);
        given(postRepository.saveAll(postList)).willReturn(postList);
        List<Post> excepted = postService.saveAllPosts(postList);
        assertThat(excepted).isNotNull();
    }

    @Test
    void testSaveAllPosts_SaveUnsuccessful_ThrowException() {
    }

    @Test
    void testPatchPostById_PostExist_ReturnPost() {
        Post post = new Post();
        given(postRepository.findById(1L)).willReturn(Optional.of(post));
        given(postRepository.save(post)).willReturn(post);
        Post excepted = postService.patchPostById(post, 1L);
        assertThat(excepted).isNotNull();
    }

    @Test
    void testPatchPostById_NoPostExist_ThrowException() {
        Post post = new Post();
        given(postRepository.findById(1L)).willReturn(Optional.empty());
        Assertions.assertThrows(PostNotFoundException.class, () -> postService.patchPostById(post, 1L));
    }

    @Test
    void testDeletePostById_PostExist_DeletePost() {
        given(postRepository.existsById(1L)).willReturn(Boolean.TRUE);
        postService.deletePostById(1L);
        verify(postRepository).deleteById(1L);
    }

    @Test
    void testDeletePostById_NoPostExist_ThrowException() {
        given(postRepository.existsById(1L)).willReturn(Boolean.FALSE);
        Assertions.assertThrows(PostNotFoundException.class, () -> postService.deletePostById(1L));
    }
}
