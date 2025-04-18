package com.emin.nereye.domain.user.impl;

import com.emin.nereye.domain.user.api.UserDto;
import com.emin.nereye.domain.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
        else throw new RuntimeException("User could not be founded");
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
        userRepository.save(userMapper.toUser(user));
        return user;
    }

    @Override
    @Transactional
    public UserDto updete(UserDto user, int theId) {
        User tmp = userMapper.toUser(findById(theId));
        tmp.setId(theId);
        userRepository.save(tmp);
        return user;

    }
}
