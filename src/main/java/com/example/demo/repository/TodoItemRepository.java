package com.example.demo.repository;

import com.example.demo.model.TodoItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItemEntity, Integer> {

    List<TodoItemEntity> findAllByTodoListEntityIdAndStatus(Integer id, Boolean status);

    @Query("select item from TodoItemEntity item where item.todoListEntity.id = :id and item.status = :status")
    List<TodoItemEntity> findByQueryAnnotation(Integer id, Boolean status);
}
