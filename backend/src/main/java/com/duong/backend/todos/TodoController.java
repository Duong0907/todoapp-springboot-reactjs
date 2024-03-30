package com.duong.backend.todos;

import com.duong.backend.responses.Response;
import com.duong.backend.todos.requests.CreateTodoReq;
import com.duong.backend.todos.requests.UpdateTodoReq;
import com.duong.backend.users.User;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;
    private final TodoMapper mapper;

    @GetMapping("/")
    public ResponseEntity<Response> getAllTodos() throws Exception {
//        throw new Exception("This is demo exception");
        List<Todo> todos = service.getAllTodos();
        List<TodoDto> todoDtos = todos.stream()
                .map(mapper::mapToTodoDto)
                .collect(Collectors.toList());

        Response res = Response.builder()
                .message("Get all todo successfully")
                .error(false)
                .data(todoDtos)
                .build();

        return ResponseEntity.ok(res);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Response> getAllTodosByUserId(@PathVariable @NotNull("id") Integer id, Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        Integer userId = user.getId();
        if (userId != id) {
            return new ResponseEntity<>(Response.builder().message("You are not allowed").error(true).build(), HttpStatus.FORBIDDEN);
        }

        try {
            List<Todo> todos = service.getAllTodosByUserId(id);
            List<TodoDto> todoDtos = todos.stream()
                    .map(mapper::mapToTodoDto)
                    .collect(Collectors.toList());

            Response res = Response.builder()
                    .message("Get todo by user id successfully")
                    .error(false)
                    .data(todoDtos)
                    .build();
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            Response res = Response.builder().message(e.getMessage()).error(true).build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getTodoById(@PathVariable @NotNull("id") Integer id) {
        try {
            Todo todo = service.getTodoById(id);
            Response res = Response.builder()
                    .message("Get todo by id successfully")
                    .error(false)
                    .data(mapper.mapToTodoDto(todo))
                    .build();
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            Response res = Response.builder().message(e.getMessage()).error(true).build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Response> createTodo(@RequestBody @NotNull CreateTodoReq request, Authentication authentication) {
        // Get current authenticated user
        User user = (User)authentication.getPrincipal();
        Integer userId = user.getId();

        Todo todo = service.createTodo(request, userId);
        Response res = Response.builder()
                .message("Create todo successfully")
                .error(false)
                .data(mapper.mapToTodoDto(todo))
                .build();
        return ResponseEntity.ok(res);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response> updateTodo(@RequestBody @NotNull UpdateTodoReq request, @PathVariable @NotNull Integer id) {
        try {
            Todo todo = service.updateTodo(request, id);
            Response res = Response.builder()
                    .message("Update todo successfully")
                    .error(false)
                    .data(mapper.mapToTodoDto(todo))
                    .build();
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            Response res = Response.builder().message(e.getMessage()).error(true).build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTodo(@PathVariable @NotNull Integer id) {
        try {
            service.deleteTodo(id);
            Response res = Response.builder()
                    .message("Delete todo successfully")
                    .error(false)
                    .build();
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            Response res = Response.builder().message(e.getMessage()).error(true).build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
