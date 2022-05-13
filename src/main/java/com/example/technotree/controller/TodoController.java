package com.example.technotree.controller;

import com.example.technotree.domain.Todo;
import com.example.technotree.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> findAllTodos() {
        return todoService.findAllTodos();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo findTodoById(@PathVariable Long id) {
        return todoService.findTodoById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> findTodosByUserIdAndCompletedField(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "completed") Boolean completed) {
        return todoService.findTodosByUserIdAndCompletedField(userId, completed);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> saveAllTodos(@RequestBody List<Todo> todos) {
        return todoService.saveAllTodos(todos);
    }
}
