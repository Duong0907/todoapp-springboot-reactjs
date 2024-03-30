package com.duong.backend.advices;

import com.duong.backend.responses.Response;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Response handleSecurityException(Exception ex) {

        if (ex instanceof BadCredentialsException) {
            return Response.builder().message("Authentication Failure").error(true).build();
        }

        if (ex instanceof AccessDeniedException) {
            return Response.builder().message("Not authorized").error(true).build();
        }

        return Response.builder().message("Something went wrong").error(true).build();
    }
}
