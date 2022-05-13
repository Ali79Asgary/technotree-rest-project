package com.example.technotree.service;

import com.example.technotree.domain.Comment;
import com.example.technotree.exception.CommentNotFoundException;
import com.example.technotree.repository.CommentRepository;
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

public class SimpleCommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Autowired
    private SimpleCommentService commentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        commentService = new SimpleCommentService(commentRepository);
    }

    @Test
    void testFindAllComments_CommentExists_ReturnCommentsList() {
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
        given(commentRepository.findAll()).willReturn(commentList);
        List<Comment> exceptedList = commentService.findAllComments();
        assertThat(exceptedList).isNotNull();
    }

    @Test
    void testFindAllComments_NoCommentExist_ThrowException() {
        List<Comment> emptyList = Collections.emptyList();
        given(commentRepository.findAll()).willReturn(emptyList);
        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.findAllComments());
    }

    @Test
    void testFindCommentById_Successful_ReturnComment() {
        Comment comment = new Comment();
        given(commentRepository.getById(1L)).willReturn(comment);
        Comment excepted = commentService.findCommentById(1L);
        assertThat(excepted).isNotNull();
    }

    @Test
    void testFindCommentById_Unsuccessful_ThrowException() {
    }

    @Test
    void testFindCommentsByPostId_CommentExist_ReturnPostList() {
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
        given(commentRepository.existsCommentsByPostId(1L)).willReturn(Boolean.TRUE);
        given(commentRepository.findCommentsByPostId(1L)).willReturn(commentList);
        List<Comment> exceptedList = commentService.findCommentsByPostId(1L);
        assertThat(exceptedList).isNotNull();
    }

    @Test
    void testFindCommentsByPostId_NoCommentExist_ThrowException() {
        List<Comment> emptyList = Collections.emptyList();
        given(commentRepository.findCommentsByPostId(1L)).willReturn(emptyList);
        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.findCommentsByPostId(1L));
    }

    @Test
    void testSaveComment_SaveSuccessful_ReturnComment() {
        Comment comment = new Comment();
        given(commentRepository.save(comment)).willReturn(comment);
        Comment excepted = commentService.saveComment(comment);
        assertThat(excepted).isNotNull();
    }

    @Test
    void testSaveComment_SaveUnsuccessful_ThrowException() {
    }

    @Test
    void testSaveAllComments_SaveSuccessful_ReturnCommentList() {
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
        given(commentRepository.saveAll(commentList)).willReturn(commentList);
        List<Comment> excepted = commentService.saveAllComments(commentList);
        assertThat(excepted).isNotNull();
    }

    @Test
    void testSaveAllComments_SaveUnsuccessful_ThrowException() {
    }

    @Test
    void testPatchCommentById_CommentExist_ReturnComment() {
        Comment comment = new Comment();
        given(commentRepository.findById(1L)).willReturn(Optional.of(comment));
        given(commentRepository.save(comment)).willReturn(comment);
        Comment excepted = commentService.patchCommentById(comment, 1L);
        assertThat(excepted).isNotNull();
    }

    @Test
    void testPatchCommentById_NoCommentExist_ThrowException() {
        Comment comment = new Comment();
        given(commentRepository.findById(1L)).willReturn(Optional.empty());
        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.patchCommentById(comment, 1L));
    }

    @Test
    void testDeleteCommentById_CommentExist_DeleteComment() {
        given(commentRepository.existsById(1L)).willReturn(Boolean.TRUE);
        commentService.deleteCommentById(1L);
        verify(commentRepository).deleteById(1L);
    }

    @Test
    void testDeleteCommentById_NoCommentExist_ThrowException() {
        given(commentRepository.existsById(1L)).willReturn(Boolean.FALSE);
        Assertions.assertThrows(CommentNotFoundException.class, () -> commentService.deleteCommentById(1L));
    }
}
