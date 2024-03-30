package com.duong.backend.todos.requests;

import lombok.Data;

@Data
public class CreateTodoReq {
    private String title;
    private String status;
}
