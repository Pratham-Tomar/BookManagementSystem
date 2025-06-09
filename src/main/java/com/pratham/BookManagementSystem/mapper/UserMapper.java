package com.pratham.BookManagementSystem.mapper;

import com.pratham.BookManagementSystem.dtos.UserDto;
import com.pratham.BookManagementSystem.entity.User;

public class UserMapper {

    public static UserDto toDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setMobileNumber(user.getMobileNumber());
        userDto.setAddress(user.getAddress());
        userDto.setRegistrationNumber(user.getRegistrationNumber());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public static User toEntity(UserDto userDto){
        User user = new User();

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setAddress(userDto.getAddress());
        user.setRegistrationNumber(userDto.getRegistrationNumber());
        user.setRole(userDto.getRole());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
