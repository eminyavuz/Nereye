package com.emin.nereye.domain.user.web;

import com.emin.nereye.domain.user.api.UserDto;
import com.emin.nereye.domain.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
/*@CrossOrigin(
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
)*/
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponse> create(@RequestBody UserDto user) {
        return ResponseEntity.ok(toResponse(userService.save(user)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(userDto, id));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto user) {
        System.out.println("Login proccess started");
        return ResponseEntity.ok(userService.verify(user));
    }

    public static UserResponse toResponse(UserDto dto) {
        return UserResponse
                .builder()
                .user_name(dto.getUser_name())
                .first_name(dto.getFirst_name())
                .user_email(dto.getUser_name())
                .last_name(dto.getLast_name())
                .role(dto.getRole())
                .build();
    }
}
