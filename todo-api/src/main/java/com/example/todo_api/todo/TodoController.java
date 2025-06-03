package com.example.todo_api.todo;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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
    @ApiResponse(responseCode = "404", description = "요청에 들어온 member id가 존재하지 않음")
    public ResponseEntity<Void> createTodo(@RequestBody @Valid TodoCreateRequest request){
        Long todoId = todoService.createTodo(request.getContent(), request.getMemberId());
        return ResponseEntity.created(URI.create("/todo/" + todoId)).build();
    }

    @PatchMapping("/{todoId}")
    @ApiResponse(responseCode = "404", description = "요청에 들어온 member id나 todo id가 존재하지 않음")
    public ResponseEntity<Void> updateTodo(@RequestBody @Valid TodoUpdateRequest request, @PathVariable Long todoId){
        todoService.updateContent(request.getMemberId(), todoId, request.getNewContent());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    @ApiResponse(responseCode = "404", description = "요청에 들어온 member id가 존재하지 않음")
    public ResponseEntity<List<Todo>> getTodoList(@RequestBody Long memberId){
        List<Todo> todos = todoService.readTodos(memberId);
        return ResponseEntity.ok(todos);
    }

    @DeleteMapping("/{todoId}")
    @ApiResponse(responseCode = "404", description = "요청에 들어온 member id가 존재하지 않음")
    public ResponseEntity<Void> deleteTodo(@RequestBody Long memberId, @PathVariable Long todoId){
        todoService.deleteContent(memberId, todoId);
        return ResponseEntity.noContent().build();
    }
}
