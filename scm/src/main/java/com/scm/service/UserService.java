package com.scm.service;

import com.scm.dto.UserRequestDto;
import com.scm.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(String userId);

    UserResponseDto updateUser(String userId, UserRequestDto requestDto);

    void deleteUser(String userId);
}
