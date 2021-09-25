package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TODOLIST")
public class TodoListEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "besitzer")
    private String besitzer;

    @OneToMany(mappedBy="todoListEntity")
    private Set<TodoItemEntity> todoItemEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(String besitzer) {
        this.besitzer = besitzer;
    }

    public Set<TodoItemEntity> getTodoItemEntity() {
        return todoItemEntity;
    }

    public void setTodoItemEntity(Set<TodoItemEntity> todoItemEntity) {
        this.todoItemEntity = todoItemEntity;
    }
}
