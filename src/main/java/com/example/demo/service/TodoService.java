package com.example.demo.service;

import com.example.demo.model.TodoItemEntity;
import com.example.demo.model.TodoListEntity;

import java.util.List;

public interface TodoService {

    TodoListEntity createTodoList(TodoListEntity todoListEntity);

    TodoItemEntity createTodoItem(Integer id, TodoItemEntity todoItemEntity);

    List<TodoItemEntity> createTodoItems(Integer id, List<TodoItemEntity> todoItemEntityList);

    TodoListEntity getTodoListById(Integer id);

    List<TodoItemEntity> getAllItemsWithStatusById(Integer id, Boolean status);
}
