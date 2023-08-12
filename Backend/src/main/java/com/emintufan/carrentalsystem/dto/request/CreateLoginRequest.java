package com.emintufan.carrentalsystem.dto.request;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoginRequest {
    private String email;
    private String password;
    private String authorities;
}
