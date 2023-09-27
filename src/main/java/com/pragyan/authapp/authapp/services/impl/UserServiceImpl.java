package com.pragyan.authapp.authapp.services.impl;

import com.pragyan.authapp.authapp.entity.User;
import com.pragyan.authapp.authapp.exceptions.ResourceNotFoundException;
import com.pragyan.authapp.authapp.payloads.UserDto;
import com.pragyan.authapp.authapp.repositories.UserRepo;
import com.pragyan.authapp.authapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto){
        User user = dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId){
        User user = this.userRepo.findById(userId)
                .orElseThrow((() -> new ResourceNotFoundException("User", "Id", userId)));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }
    @Override
    public UserDto getUserById(Integer userId){
        User user = this.userRepo.findById(userId)
                .orElseThrow((() -> new ResourceNotFoundException("User", "Id", userId)));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();


        List<UserDto> userDtos =  users.stream()
                .map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow((() -> new ResourceNotFoundException("User", "Id", userId)));
        this.userRepo.delete(user);

    }

    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        //user.setId(userDto.getId());
        //user.setName(userDto.getName());
        //user.setEmail(userDto.getEmail());
        //user.setAbout(userDto.getAbout());
        //user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;

    }

}
