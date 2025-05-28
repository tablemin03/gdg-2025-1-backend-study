package com.example.todo_api.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Void> createTodo(@RequestBody TodoCreateRequest request){
        Long todoId = todoService.createTodo(request.getContent(), request.getMemberId());
        return ResponseEntity.created(URI.create("/todo/" + todoId)).build();
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<Void> updateTodo(@RequestBody TodoUpdateRequest request, @PathVariable Long todoId){
        todoService.updateContent(request.getMemberId(), todoId, request.getNewContent());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Todo>> getTodoList(@RequestBody Long memberId){
        List<Todo> todos = todoService.readTodos(memberId);
        return ResponseEntity.ok(todos);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@RequestBody Long memberId, @PathVariable Long todoId){
        todoService.deleteContent(memberId, todoId);
        return ResponseEntity.noContent().build();
    }
}
