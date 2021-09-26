package com.example.demo;

import com.example.demo.model.TodoListEntity;
import com.example.demo.repository.TodoItemRepository;
import com.example.demo.repository.TodoListRepository;
import com.example.demo.service.TodoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

// Unit-Test mit JUnit 5 -> Mockito (Spring-Boot, Baeldung)
@ExtendWith(MockitoExtension.class)
public class TodoServiceImplTest {

    @Mock
    TodoListRepository todoListRepository;

    @Mock
    TodoItemRepository todoItemRepository;

    @InjectMocks
    TodoServiceImpl todoService;

    @Test
    @DisplayName("Should find single todoList by id successfully.")
    void shouldFindSingleTodoItemByIdSuccessfully() {
        when(todoListRepository.findById(1)).thenReturn(Optional.of(new TodoListEntity()));
        TodoListEntity todoListEntity = todoService.getTodoListById(1);
        Assertions.assertNotNull(todoListEntity);
    }
}
