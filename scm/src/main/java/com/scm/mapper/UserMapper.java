package com.scm.mapper;

import com.scm.dto.UserRequestDto;
import com.scm.dto.UserResponseDto;
import com.scm.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // from entity to response dto
    UserResponseDto toUserResponseDto(User user);

    // user request dto to entity
    @Mapping(target = "userId", ignore = true)
    User toUser(UserRequestDto requestDto);
}
