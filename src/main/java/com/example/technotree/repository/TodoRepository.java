package com.example.technotree.repository;

import com.example.technotree.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByUserIdAndAndCompleted(@Param("userId") Long userId, @Param("completed") Boolean completed);
}
