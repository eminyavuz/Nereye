package com.emin.nereye.domain.user.api;
import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(int theId);

    void deleteById(int theId);

    UserDto save(UserDto user);

    UserDto updete(UserDto user, int theId);

}
