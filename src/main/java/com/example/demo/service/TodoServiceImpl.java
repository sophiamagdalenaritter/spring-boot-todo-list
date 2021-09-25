package com.example.demo.service;

import com.example.demo.model.TodoItemEntity;
import com.example.demo.model.TodoListEntity;
import com.example.demo.repository.TodoItemRepository;
import com.example.demo.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoListRepository todoListRepository;
    private TodoItemRepository todoItemRepository;

    public TodoServiceImpl(TodoListRepository todoListRepository, TodoItemRepository todoItemRepository) {
        this.todoListRepository = todoListRepository;
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public TodoListEntity createTodoList(TodoListEntity todoListEntity) {
        return todoListRepository.save(todoListEntity);
    }

    @Override
    public TodoItemEntity createTodoItem(Integer id, TodoItemEntity todoItemEntity) {
        Optional<TodoListEntity> todoListEntity = todoListRepository.findById(id);
        if(todoListEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }
        todoItemEntity.setTodoListEntity(todoListEntity.get());
        return todoItemRepository.save(todoItemEntity);
    }

    @Override
    public List<TodoItemEntity> createTodoItems(Integer id, List<TodoItemEntity> todoItemEntityList) {
        Optional<TodoListEntity> todoListEntity = todoListRepository.findById(id);
        if(todoListEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }
        todoItemEntityList.forEach(todoItemEntity -> todoItemEntity.setTodoListEntity(todoListEntity.get()));
        return todoItemRepository.saveAll(todoItemEntityList);
    }

    @Override
    public TodoListEntity getTodoListById(Integer id) {
        Optional<TodoListEntity> todoListEntity = todoListRepository.findById(id);
        if(todoListEntity.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return todoListEntity.get();
    }

    @Override
    public List<TodoItemEntity> getAllItemsWithStatusById(Integer id, Boolean status) {
        // Über JPA-Query-Methods
        List<TodoItemEntity> todoItemEntityList1 = todoItemRepository.findAllByTodoListEntityIdAndStatus(id, status);
        // Über Query-Annotations (Wie HQL)
        List<TodoItemEntity> todoItemEntityList2 = todoItemRepository.findByQueryAnnotation(id, status);
        return todoItemEntityList2;
    }
}
