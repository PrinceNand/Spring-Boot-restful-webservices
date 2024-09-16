package com.spring.springboot_restful_webservices.service;

import com.spring.springboot_restful_webservices.dto.UserDto;
import com.spring.springboot_restful_webservices.entity.User;

import java.util.List;

public interface UserServices {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUserById(Long userId);
}
