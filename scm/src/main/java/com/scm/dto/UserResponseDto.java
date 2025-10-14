package com.scm.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String about;
}
