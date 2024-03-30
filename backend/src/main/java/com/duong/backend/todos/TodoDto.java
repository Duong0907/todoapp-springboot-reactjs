package com.duong.backend.todos;

import lombok.Data;

import java.time.Instant;
@Data

public class TodoDto {
    private Integer id;
    private String title;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}
