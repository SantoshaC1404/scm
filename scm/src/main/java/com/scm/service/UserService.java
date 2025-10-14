package com.scm.service;

import com.scm.dto.UserRequestDto;
import com.scm.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);

    UserResponseDto getAllUsers();
}
