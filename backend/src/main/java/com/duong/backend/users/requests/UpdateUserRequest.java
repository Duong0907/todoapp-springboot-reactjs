package com.duong.backend.users.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateUserRequest {
    private String firstname;
    private String lastname;
    private String email;
}
