package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "TODOITEM")
public class TodoItemEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "status")
    private boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "listeId", nullable = false)
    private TodoListEntity todoListEntity;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TodoListEntity getTodoListEntity() {
        return todoListEntity;
    }

    public void setTodoListEntity(TodoListEntity todoListEntity) {
        this.todoListEntity = todoListEntity;
    }
}
