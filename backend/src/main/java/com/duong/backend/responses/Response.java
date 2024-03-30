package com.duong.backend.responses;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Builder
@Data
public class Response {
    @NotNull String message;
    @NotNull boolean error;
    private Object data = null;
}
