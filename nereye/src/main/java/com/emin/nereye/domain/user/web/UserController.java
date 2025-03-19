package com.emin.nereye.domain.user.web;

import com.emin.nereye.domain.user.api.userDto.UserCreateDto;
import com.emin.nereye.domain.user.api.userDto.UserReadDto;
import com.emin.nereye.domain.user.impl.User;
import com.emin.nereye.domain.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public UserCreateDto create(@RequestBody User user) {
        User tmp = user;
        return userService.save(tmp);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable int id) {

        return  toCreateDto(userService.findById(id);
    }
    public static UserCreateDto toCreateDto (UserRequest req){
        return  UserCreateDto.builder()
                .first_name(req.getFirst_name())
                .last_name(req.getLast_name())
                .user_name(req.getUser_name())
                .email(req.getEmail())
                .password(req.getPassword())
                .build();
    }
    public static UserResponse toResponse(UserCreateDto dto){
        return UserResponse.builder()
                .phone_number(dto.getPhone_number())
                .role(dto.getRole())
                .email(dto.getEmail())
                .first_name(dto.getFirst_name())
                .last_name(dto.getLast_name())
                .user_name(dto.getUser_name())
                .phone_number(dto.getPhone_number())
                .build();
    }
}
