package com.emin.nereye.domain.user.impl;

import com.emin.nereye.domain.user.api.UserDto;
import com.emin.nereye.domain.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;
    @Autowired
            UserMapper mapper;
    MyDetailsService(UserRepository repo){
        this.repo=repo;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        UserDto userDto = mapper.toUserDto(user);

        if(user== null){
            System.out.println("Kullanıcı bulunamadı");
            throw new UsernameNotFoundException("Kullanıcı bulunamadı");
        }
        return new UserPrincipal(userDto);
    }
}
