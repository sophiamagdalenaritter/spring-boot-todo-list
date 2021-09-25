package com.example.demo.repository;

import com.example.demo.model.TodoListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoListEntity, Integer> {

}
