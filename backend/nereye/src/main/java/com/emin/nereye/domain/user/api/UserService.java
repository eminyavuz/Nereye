package com.emin.nereye.domain.user.api;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findByUsername(String username);

    UserDto findById(int theId);

    void deleteById(int theId);

    UserDto save(UserDto user);

    UserDto update(UserDto user);

    String verify(UserDto user);

}

