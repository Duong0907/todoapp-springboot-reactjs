package com.duong.backend.welcome;

import com.duong.backend.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @GetMapping("/ping")
    public ResponseEntity<Response> ping() {
        Response res = Response.builder().message("Ping successfully").error(false).build();
        return ResponseEntity.ok(res);
    }
}



