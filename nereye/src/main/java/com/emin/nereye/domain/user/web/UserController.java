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
    public UserReadDto getUser(@PathVariable int id) {

        return userService.findById(id);
    }
}
