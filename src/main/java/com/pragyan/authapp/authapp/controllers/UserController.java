package com.pragyan.authapp.authapp.controllers;

import com.pragyan.authapp.authapp.payloads.ApiResponse;
import com.pragyan.authapp.authapp.payloads.UserDto;
import com.pragyan.authapp.authapp.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserDto);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uId){
        UserDto updateUser = this.userService.updateUser(userDto, uId);
        return ResponseEntity.ok(updateUser);

    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uId){
        userService.deleteUser(uId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.NO_CONTENT);
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getSingleUsers(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}