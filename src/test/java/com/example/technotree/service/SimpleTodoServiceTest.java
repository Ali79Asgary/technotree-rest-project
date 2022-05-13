package com.example.technotree.service;

import com.example.technotree.domain.Todo;
import com.example.technotree.exception.TodoNotFoundException;
import com.example.technotree.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class SimpleTodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Autowired
    private SimpleTodoService todoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        todoService = new SimpleTodoService(todoRepository);
    }

    @Test
    void testFindAllTodos_TodoExists_ReturnTodosList() {
        Todo todo1 = new Todo();
        Todo todo2 = new Todo();
        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo1);
        todoList.add(todo2);
        given(todoRepository.findAll()).willReturn(todoList);
        List<Todo> exceptedList = todoService.findAllTodos();
        assertThat(exceptedList).isNotNull();
    }

    @Test
    void testFindAllTodos_NoTodoExist_ThrowException() {
        List<Todo> emptyList = Collections.emptyList();
        given(todoRepository.findAll()).willReturn(emptyList);
        Assertions.assertThrows(TodoNotFoundException.class, () -> todoService.findAllTodos());
    }

    @Test
    void testFindTodoById_Successful_ReturnTodo() {
        Todo todo = new Todo();
        given(todoRepository.getById(1L)).willReturn(todo);
        Todo excepted = todoService.findTodoById(1L);
        assertThat(excepted).isNotNull();
    }

    @Test
    void testFindTodoById_Unsuccessful_ThrowException() {
    }

    @Test
    void testFindTodosByUserIdAndCompletedField_TodoExist_ReturnTodoList() {
        Todo todo1 = new Todo();
        Todo todo2 = new Todo();
        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo1);
        todoList.add(todo2);
        given(todoRepository.findAllByUserIdAndAndCompleted(1L, Boolean.TRUE)).willReturn(todoList);
        List<Todo> exceptedList = todoService.findTodosByUserIdAndCompletedField(1L, Boolean.TRUE);
        assertThat(exceptedList).isNotNull();
    }

    @Test
    void testFindTodosByUserIdAndCompletedField_NoTodoExist_ThrowException() {
        List<Todo> emptyList = Collections.emptyList();
        given(todoRepository.findAllByUserIdAndAndCompleted(1L, Boolean.TRUE)).willReturn(emptyList);
        Assertions.assertThrows(TodoNotFoundException.class, () -> todoService.findTodosByUserIdAndCompletedField(1L, Boolean.TRUE));
    }

    @Test
    void testSaveAllTodos_SaveSuccessful_ReturnTodoList() {
        Todo todo1 = new Todo();
        Todo todo2 = new Todo();
        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo1);
        todoList.add(todo2);
        given(todoRepository.saveAll(todoList)).willReturn(todoList);
        List<Todo> exceptedList = todoService.saveAllTodos(todoList);
        assertThat(exceptedList).isNotNull();
    }

    @Test
    void testSaveAllTodos_SaveUnsuccessful_ThrowException() {
    }
}
