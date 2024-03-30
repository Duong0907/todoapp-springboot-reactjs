package com.duong.backend.todos;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoMapper {
    private final ModelMapper modelMapper;
    public Todo mapToTodo(TodoDto todoDto) {
        return modelMapper.map(todoDto, Todo.class);
    }

    public TodoDto mapToTodoDto(Todo todo) {
        return modelMapper.map(todo, TodoDto.class);
    }
}
