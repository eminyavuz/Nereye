package com.emin.nereye.domain.auth.web;

import com.emin.nereye.domain.user.api.UserDto;
import com.emin.nereye.domain.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = {
                "http://localhost",
                "http://localhost:8080",
                "http://localhost:3000"
        },
        allowCredentials = "true",
        allowedHeaders = {
                "Authorization",
                "Content-Type",
                "X-Requested-With"
        },
        exposedHeaders = {
                "Authorization"
        }
)
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.verify(userDto));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser() {
        // TODO: Implement get current user logic
        return ResponseEntity.ok().build();
    }
} 