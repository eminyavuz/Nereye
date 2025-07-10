package com.emin.nereye.domain.user.impl;

import com.emin.nereye.domain.user.api.UserDto;
import com.emin.nereye.domain.user.api.UserService;
import com.emin.nereye.security.JWTService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private JWTService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AuthenticationManager authenticationManager,JWTService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtService=jwtService;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> dto = new ArrayList<>();
        for (User u : users) {
            dto.add(userMapper.toUserDto(u));
        }
        return dto;
    }

    @Override
    public UserDto findById(int theId) {
        Optional<User> result = userRepository.findById(theId);

        User user = null;
        if (result.isPresent())
            user = result.get();
        else throw new EntityNotFoundException("Kullanıcı bulunamadı - Lütfen geçerli bir id girin");
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public UserDto save(UserDto user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(userMapper.toUser(user));
        return user;
    }

    @Override
    @Transactional
    public UserDto update(UserDto user, int theId) {
        User tmp = userMapper.toUser(findById(theId));
        tmp = userMapper.toUser(user);
        tmp.setId(theId);

        userRepository.save(tmp);
        return userMapper.toUserDto(tmp);

    }

    @Override
    public String verify(UserDto user) {
        System.out.println("Verify process started ");
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUser_name(),user.getPassword()));
       if(authentication.isAuthenticated())
       {
           System.out.println("verify complated");
           return jwtService.generateToken(user.getUser_name());
       }
        return "Failed";
    }
}
