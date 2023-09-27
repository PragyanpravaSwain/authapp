package com.pragyan.authapp.authapp.services;

import com.pragyan.authapp.authapp.entity.User;
import com.pragyan.authapp.authapp.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
     List<UserDto> getAllUsers();
    void deleteUser(Integer userId);



}
