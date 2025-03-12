package com.emin.nereye.mapper;

import com.emin.nereye.dto.userDto.UserCreateDto;
import com.emin.nereye.dto.userDto.UserReadDto;
import com.emin.nereye.dto.userDto.UserUpdateDto;
import com.emin.nereye.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-12T17:53:38+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User UserCreateDtoToUser(UserCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUser_name( dto.getUser_name() );
        user.setPassword( dto.getPassword() );
        user.setEmail( dto.getEmail() );
        user.setPhone_number( dto.getPhone_number() );
        user.setFirst_name( dto.getFirst_name() );
        user.setLast_name( dto.getLast_name() );
        user.setRole( dto.getRole() );

        return user;
    }

    @Override
    public UserCreateDto EntityToUserCreateDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserCreateDto userCreateDto = new UserCreateDto();

        userCreateDto.setUser_name( entity.getUser_name() );
        userCreateDto.setPassword( entity.getPassword() );
        userCreateDto.setEmail( entity.getEmail() );
        userCreateDto.setPhone_number( entity.getPhone_number() );
        userCreateDto.setFirst_name( entity.getFirst_name() );
        userCreateDto.setLast_name( entity.getLast_name() );
        userCreateDto.setRole( entity.getRole() );

        return userCreateDto;
    }

    @Override
    public UserReadDto entityToUserReadDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserReadDto userReadDto = new UserReadDto();

        userReadDto.setUser_name( user.getUser_name() );
        userReadDto.setEmail( user.getEmail() );
        userReadDto.setPhone_number( user.getPhone_number() );
        userReadDto.setFirst_name( user.getFirst_name() );
        userReadDto.setLast_name( user.getLast_name() );
        userReadDto.setRole( user.getRole() );

        return userReadDto;
    }

    @Override
    public User userReadDtoToUser(UserReadDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUser_name( dto.getUser_name() );
        user.setEmail( dto.getEmail() );
        user.setPhone_number( dto.getPhone_number() );
        user.setFirst_name( dto.getFirst_name() );
        user.setLast_name( dto.getLast_name() );
        user.setRole( dto.getRole() );

        return user;
    }

    @Override
    public UserUpdateDto userToUserUpdateDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserUpdateDto userUpdateDto = new UserUpdateDto();

        userUpdateDto.setUser_name( user.getUser_name() );
        userUpdateDto.setPassword( user.getPassword() );
        userUpdateDto.setEmail( user.getEmail() );
        userUpdateDto.setPhone_number( user.getPhone_number() );
        userUpdateDto.setFirst_name( user.getFirst_name() );
        userUpdateDto.setLast_name( user.getLast_name() );
        userUpdateDto.setRole( user.getRole() );

        return userUpdateDto;
    }

    @Override
    public User userUpdateDtoToUser(UserUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUser_name( dto.getUser_name() );
        user.setPassword( dto.getPassword() );
        user.setEmail( dto.getEmail() );
        user.setPhone_number( dto.getPhone_number() );
        user.setFirst_name( dto.getFirst_name() );
        user.setLast_name( dto.getLast_name() );
        user.setRole( dto.getRole() );

        return user;
    }
}
