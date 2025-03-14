package com.emin.nereye.domain.user.impl;

import com.emin.nereye.domain.user.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
