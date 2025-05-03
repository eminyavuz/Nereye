package com.emin.nereye.domain.user.impl;

import com.emin.nereye.domain.user.api.UserDto;
import com.emin.nereye.domain.user.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.SecureRandom;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    UserDto findByUsername(String username);
}
