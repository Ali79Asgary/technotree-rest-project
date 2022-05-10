package com.example.technotree.repository;

import com.example.technotree.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select * from Comment where Comment.postId = :postId")
    List<Comment> findCommentsByPostId(@Param("postId") Long postId);

    Boolean existsCommentsByPostId(@Param("postId") Long postId);
}
