package com.duong.backend.auth;

import com.duong.backend.auth.requests.AuthenticationRequest;
import com.duong.backend.auth.requests.RegisterRequest;
import com.duong.backend.responses.Response;
import com.duong.backend.users.User;
import com.duong.backend.users.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    private final UserMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<Response> register(
            @RequestBody RegisterRequest request
    ) {
        User user = service.register(request);
        Response res = Response.builder().
                message("Register successfully")
                .error(false)
                .data(mapper.mapToUserDto(user))
                .build();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(
            @RequestBody AuthenticationRequest request
    ) {
        String accessToken = service.login(request);
        Response res = Response.builder().
                message("Login successfully")
                .error(false)
                .data(accessToken)
                .build();
        return ResponseEntity.ok(res);
    }
}
