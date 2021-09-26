package com.example.demo;

import com.example.demo.model.TodoItemEntity;
import com.example.demo.model.TodoListEntity;
import com.example.demo.service.TodoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// Integration-Test (mit Mock-Anteil) mit JUnit 5 -> Mockito (Spring-Boot, MockMvc (wie Postman), Baeldung)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestEndpointTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @BeforeEach
    void setUpBeforeEach() throws IOException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Should find single todoList by id successfully.")
    void getTodoListById() throws Exception {
        TodoListEntity todoListEntity = new TodoListEntity();
        todoListEntity.setId(1);
        todoListEntity.setBezeichnung("learn a programming language");
        todoListEntity.setBesitzer("Sophia");
        Set<TodoItemEntity> todoItemEntitySet = new HashSet<>();
        TodoItemEntity todoItemEntity = new TodoItemEntity();
        todoItemEntity.setId(2);
        todoItemEntity.setBezeichnung("Zähne putzen");
        todoItemEntity.setStatus(true);
        todoItemEntitySet.add(todoItemEntity);
        todoListEntity.setTodoItemEntity(todoItemEntitySet);

        when(todoService.getTodoListById(todoListEntity.getId())).thenReturn(todoListEntity);
        mockMvc.perform(get("/api/todolists/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.todoItemEntity", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.todoItemEntity[0].id", Matchers.equalTo(2)))
        ;
    }
}
