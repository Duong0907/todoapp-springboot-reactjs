package com.duong.backend.todos;

import com.duong.backend.todos.requests.CreateTodoReq;
import com.duong.backend.todos.requests.UpdateTodoReq;
import com.duong.backend.users.User;
import com.duong.backend.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;
    private final TodoMapper mapper;
    private final UserRepository userRepository;
    public List<Todo> getAllTodos() {
        List<Todo> todos = repository.findAll();
        return todos;
    }

    public List<Todo> getAllTodosByUserId(Integer userId) {
        List<Todo> todos = repository.findAllByUserId(userId);
        return todos;
    }

    public Todo getTodoById(Integer id) throws Exception {
        Optional<Todo> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception("Todo not found");
        }
    }

    public Todo createTodo(CreateTodoReq request, Integer userId) {
        Optional<User> result = userRepository.findById(userId);
        User user = result.get();
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .status(request.getStatus())
                .user(user)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        repository.save(todo);
        return todo;
    }

    public Todo updateTodo(UpdateTodoReq request, Integer id) throws Exception {
        Optional<Todo> result = repository.findById(id);
        if (result.isPresent()) {
            Todo todo = result.get();
            if (request.getTitle() != null) {
                todo.setTitle(request.getTitle());
            }
            if (request.getStatus() != null) {
                todo.setStatus(request.getStatus());
            }

            repository.save(todo);
            return todo;
        } else {
            throw new Exception("Todo not found");
        }
    }

    public void deleteTodo(Integer id) throws Exception {
        Optional<Todo> result = repository.findById(id);
        if (result.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new Exception("Todo not found");
        }
    }

}
