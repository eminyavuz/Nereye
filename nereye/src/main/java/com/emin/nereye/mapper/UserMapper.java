package com.emin.nereye.mapper;

import com.emin.nereye.domain.user.api.userDto.UserCreateDto;
import com.emin.nereye.domain.user.api.userDto.UserReadDto;
import com.emin.nereye.domain.user.api.userDto.UserUpdateDto;
import com.emin.nereye.domain.user.impl.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user_name", target = "user_name")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone_number", target = "phone_number")
    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    @Mapping(source = "role", target = "role")
    User UserCreateDtoToUser(UserCreateDto dto);

    @Mapping(source = "user_name", target = "user_name")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone_number", target = "phone_number")
    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    @Mapping(source = "role", target = "role")
    UserCreateDto EntityToUserCreateDto(User entity);

    @Mapping(source = "user_name", target = "user_name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone_number", target = "phone_number")
    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    @Mapping(source = "role", target = "role")
    UserReadDto entityToUserReadDto(User user);

    @Mapping(source = "user_name", target = "user_name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone_number", target = "phone_number")
    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    @Mapping(source = "role", target = "role")
    User userReadDtoToUser(UserReadDto dto);

    @Mapping(source = "user_name", target = "user_name")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone_number", target = "phone_number")
    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    @Mapping(source = "role", target = "role")
    UserUpdateDto userToUserUpdateDto(User user);

    @Mapping(source = "user_name", target = "user_name")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone_number", target = "phone_number")
    @Mapping(source = "first_name", target = "first_name")
    @Mapping(source = "last_name", target = "last_name")
    @Mapping(source = "role", target = "role")
    User userUpdateDtoToUser(UserUpdateDto dto);


}
