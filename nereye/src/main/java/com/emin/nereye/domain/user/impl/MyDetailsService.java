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
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user= repo.findByUsername(username);

        if(user== null){
            System.out.println("Kullanıcı bulunamadı");
            throw new UsernameNotFoundException("Kullanıcı bulunamadı");
        }
        return new UserPrincipal(user);
    }
}
