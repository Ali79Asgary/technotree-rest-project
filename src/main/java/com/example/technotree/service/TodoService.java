package com.example.technotree.service;

import com.example.technotree.domain.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAllTodos();

    Todo findTodoById(Long id);

    List<Todo> findTodosByUserIdAndCompletedField(Long userId, Boolean completed);

    List<Todo> saveAllTodos(List<Todo> todos);
}
