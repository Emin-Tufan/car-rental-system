package com.emintufan.carrentalsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateLast5RegisteredUserResponse {
    private String name;
    private String surName;
    private String phone;
    private String email;
    private String gender;

}
