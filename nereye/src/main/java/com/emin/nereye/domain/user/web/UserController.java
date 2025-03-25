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
    public UserDto create(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable int id) {
        return userService.findById(id);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        return userService.updete(userDto, id);
    }

}
