package com.scm.service.impl;

import com.scm.dto.UserRequestDto;
import com.scm.dto.UserResponseDto;
import com.scm.entity.User;
import com.scm.mapper.UserMapper;
import com.scm.repository.UserRepository;
import com.scm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = userMapper.toUser(requestDto);
        user.setUserId(UUID.randomUUID().toString());
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto getAllUsers() {

        return null;
    }
}
