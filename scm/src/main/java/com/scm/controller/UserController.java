package com.scm.controller;

import com.scm.dto.UserRequestDto;
import com.scm.dto.UserResponseDto;
import com.scm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.createUser(requestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String userId) {
        UserResponseDto userById = userService.getUserById(userId);
        return ResponseEntity.ok(userById);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String userId, @RequestBody UserRequestDto requestDto) {
        UserResponseDto updateUser = userService.updateUser(userId, requestDto);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
