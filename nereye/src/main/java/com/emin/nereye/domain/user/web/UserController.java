package com.emin.nereye.domain.user.web;

import com.emin.nereye.domain.user.api.UserDto;
import com.emin.nereye.domain.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteById(id);
    }

    @PostMapping("/save")
    public UserResponse create(@RequestBody UserDto user) {
        System.out.println("girrdi");
        return toResponse(userService.save(user));
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable int id) {
        System.out.println("Get user ");
        return userService.findById(id);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        return userService.updete(userDto, id);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto user) {

        return userService.verify(user);
    }



    public static UserResponse toResponse(UserDto dto){
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
