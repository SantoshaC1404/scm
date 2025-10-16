package com.scm.service.impl;

import com.scm.dto.UserRequestDto;
import com.scm.dto.UserResponseDto;
import com.scm.entity.User;
import com.scm.enums.Role;
import com.scm.exception.ResourceAlreadyExistException;
import com.scm.exception.ResourceNotFoundException;
import com.scm.mapper.UserMapper;
import com.scm.repository.UserRepository;
import com.scm.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {

        // check if email is present
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistException("User with Email already exist.");
        }
        User user = userMapper.toUser(requestDto);
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRole(Role.ROLE_USER);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(String userId, UserRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        // check if email is already used or not. if used throw error or else save.
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistException("Email is used, try with different email.");
        } else {
            user.setName(requestDto.getName());
            user.setEmail(requestDto.getEmail());
            user.setPassword(requestDto.getPassword());
            user.setPhoneNumber(requestDto.getPhoneNumber());
        }
        User saved = userRepository.save(user);
        return userMapper.toUserResponseDto(saved);
    }

    @Override
    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found.");
        }
        userRepository.deleteById(userId);
    }
}
