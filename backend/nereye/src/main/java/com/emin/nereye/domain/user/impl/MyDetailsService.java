package com.emin.nereye.domain.user.impl;

import com.emin.nereye.domain.user.api.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyDetailsService implements UserDetailsService {

    @Autowired
    UserMapper mapper;
    @Autowired
    private UserRepository repo;

    MyDetailsService(UserRepository repo) {
        this.repo = repo;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username= " + username);

        User user = repo.findByUsername(username);
        UserDto userDto = mapper.toUserDto(user);
        System.out.println(user);
        System.out.println(userDto);

        if (user == null) {

            throw new UsernameNotFoundException("Kullanıcı bulunamadıMYd");
        }
        return new UserPrincipal(userDto);
    }
}
