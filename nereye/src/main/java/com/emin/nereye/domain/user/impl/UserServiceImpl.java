package com.emin.nereye.domain.user.impl;

import com.emin.nereye.domain.user.api.UserService;
import com.emin.nereye.domain.user.api.userDto.UserCreateDto;
import com.emin.nereye.domain.user.api.userDto.UserReadDto;
import com.emin.nereye.domain.user.impl.User;
import com.emin.nereye.mapper.UserMapper;
import com.emin.nereye.domain.user.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public UserReadDto findById(int theId) {
        Optional<User> result = userRepository.findById(theId);
        User user = null;
        if (result.isPresent())
            user = result.get();
        else throw new RuntimeException("User could not be founded");
        return userMapper.entityToUserReadDto(user);

    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public UserCreateDto save(User user) {
        userRepository.save(user);
        UserCreateDto dto = new UserCreateDto();
        dto = userMapper.EntityToUserCreateDto(user);

        return dto;

    }

    @Override
    @Transactional
    public void updete(User user, int theId) {
//User theUser= findById(theId);
//theUser=user;
///userRepository.save(theUser);
    }
}
