package com.emin.nereye.domain.user.impl;

import com.emin.nereye.domain.user.api.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T20:39:34+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDto dto) {
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
    public UserDto toUserDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.user_name( entity.getUser_name() );
        userDto.password( entity.getPassword() );
        userDto.email( entity.getEmail() );
        userDto.phone_number( entity.getPhone_number() );
        userDto.first_name( entity.getFirst_name() );
        userDto.last_name( entity.getLast_name() );
        userDto.role( entity.getRole() );

        return userDto.build();
    }
}
