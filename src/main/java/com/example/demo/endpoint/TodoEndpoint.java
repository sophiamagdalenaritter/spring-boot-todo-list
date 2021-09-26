package com.example.demo.endpoint;

import com.example.demo.model.TodoItemEntity;
import com.example.demo.model.TodoListEntity;
import com.example.demo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api")
public class TodoEndpoint {

    private TodoService todoService;

    public TodoEndpoint(TodoService todoService) {
        this.todoService = todoService;
    }

    @PutMapping("/todolist")
    public ResponseEntity<TodoListEntity> putToDoLists(@RequestBody TodoListEntity todoListEntity) {
        TodoListEntity result = todoService.createTodoList(todoListEntity);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/todolists/{id}")
    public ResponseEntity<TodoItemEntity> putToDoListsById(@PathVariable(name = "id") Integer id, @RequestBody TodoItemEntity todoItemEntity) {
        TodoItemEntity result = todoService.createTodoItem(id, todoItemEntity);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/todolists/{id}")
    public ResponseEntity<List<TodoItemEntity>> patchToDoListsById(@PathVariable(name = "id") Integer id, @RequestBody List<TodoItemEntity> todoItemEntityList) {
        List<TodoItemEntity> result = todoService.createTodoItems(id, todoItemEntityList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/todolists/{id}")
    public ResponseEntity<TodoListEntity> getToDoList(@PathVariable(name = "id") Integer id) {
       TodoListEntity result = todoService.getTodoListById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/todolists/{id}/{status}")
    public ResponseEntity<List<TodoItemEntity>> getAllItemsByIdAndStatus(@PathVariable(name = "id") Integer id, @PathVariable(name = "status") Boolean status) {
        List<TodoItemEntity> result = todoService.getAllItemsWithStatusById(id, status);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/todolists/{id}/query")
    public ResponseEntity<List<TodoItemEntity>> getAllItemsByIdAndStatusQuery(@PathVariable(name = "id") Integer id, @RequestParam(name = "status") Boolean status) {
        List<TodoItemEntity> result = todoService.getAllItemsWithStatusById(id, status);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/todolists")
    public ResponseEntity<Void> deleteAll() {
        todoService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
