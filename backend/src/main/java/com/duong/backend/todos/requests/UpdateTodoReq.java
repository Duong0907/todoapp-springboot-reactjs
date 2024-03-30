package com.duong.backend.todos.requests;

import lombok.Data;

@Data
public class UpdateTodoReq {
    private String title;
    private String status;
}
