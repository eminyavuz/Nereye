package com.emin.nereye.domain.user.api;

import com.emin.nereye.domain.user.api.userDto.UserCreateDto;
import com.emin.nereye.domain.user.api.userDto.UserReadDto;
import com.emin.nereye.domain.user.impl.User;

import java.util.List;


public interface UserService {
    List<User> findAll();

    UserReadDto findById(int theId);

    void deleteById(int theId);

    UserCreateDto save(User user);

    void updete(User user, int theId);

}
