package com.emin.nereye.domain.user.impl;

import com.emin.nereye.domain.user.api.UserDto;
import com.emin.nereye.domain.user.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.security.SecureRandom;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.user_name = :username")
    User findByUsername(@Param("username") String username);
}
