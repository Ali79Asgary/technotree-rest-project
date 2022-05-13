package com.example.technotree.service;

import com.example.technotree.domain.Todo;
import com.example.technotree.exception.TodoNotFoundException;
import com.example.technotree.repository.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class SimpleTodoService implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public SimpleTodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> findAllTodos() {
        log.info("going to get all todos");
        List<Todo> todos = todoRepository.findAll();
        if (!todos.isEmpty()) {
            return todos;
        } else {
            log.warn("no todo exist");
            throw new TodoNotFoundException();
        }
    }

    @Override
    public Todo findTodoById(Long id) {
        log.info("going to get todo by id : " + id);
        return todoRepository.getById(id);
    }

    @Override
    public List<Todo> findTodosByUserIdAndCompletedField(Long userId, Boolean completed) {
        log.info("going to get todos by userId and completedField");
        List<Todo> todos = todoRepository.findAllByUserIdAndAndCompleted(userId, completed);
        if (!todos.isEmpty()) {
            return todos;
        } else {
            log.warn("no todo exist");
            throw new TodoNotFoundException();
        }
    }

    @Override
    public List<Todo> saveAllTodos(List<Todo> todos) {
        log.info("going to save a list of todos");
        return todoRepository.saveAll(todos);
    }
}
