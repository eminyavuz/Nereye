package com.emin.nereye.domain.user.api;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(int theId);

    void deleteById(int theId);

    UserDto save(UserDto user);

    UserDto update(UserDto user, int theId);

    String verify (UserDto user);

}

