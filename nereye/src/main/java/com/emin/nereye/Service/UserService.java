package com.emin.nereye.Service;

import com.emin.nereye.dto.userDto.UserCreateDto;
import com.emin.nereye.dto.userDto.UserReadDto;
import com.emin.nereye.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<User> findAll();
    UserReadDto findById(int theId);
    void deleteById(int theId);
    UserCreateDto save(User user);
    void updete(User user, int theId);

}
