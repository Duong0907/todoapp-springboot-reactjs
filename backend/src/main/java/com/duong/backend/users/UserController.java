package com.duong.backend.users;

import com.duong.backend.responses.Response;
import com.duong.backend.users.requests.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<Response> getAllUsers() {
        List<User> users = service.getAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(mapper::mapToUserDto)
                .collect(Collectors.toList());

        Response res = Response.builder()
                .message("Get all users successfully")
                .error(false)
                .data(userDtos)
                .build();

        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable("id") Integer id) {
        Response res;
        try {
            User user = service.getUserById(id);
            res = Response.builder()
                    .message("Get user by id successfully")
                    .error(false)
                    .data(mapper.mapToUserDto(user))
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res = Response.builder().message(e.getMessage()).error(true).build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Response> getMe(Authentication authentication) {
        User userPrinciple = (User)authentication.getPrincipal();
        Integer id = userPrinciple.getId();

        Response res;
        try {
            User user = service.getUserById(id);
            res = Response.builder()
                    .message("Get me successfully")
                    .error(false)
                    .data(mapper.mapToUserDto(user))
                    .build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res = Response.builder().message(e.getMessage()).error(true).build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUserById(@PathVariable Integer id, @RequestBody UpdateUserRequest request) {
        Response res;
        try {
            service.updateUser(id, request);
            res = Response.builder().message("Update user successfully").error(false).build();

            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res = Response.builder().message(e.getMessage()).error(true).build();

            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUserById(@PathVariable("id") Integer id) {
        Response res;
        try {
            service.deleteUserById(id);
            res = Response.builder().message("Delete user successfully").error(false).build();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res = Response.builder().message(e.getMessage()).error(true).build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
