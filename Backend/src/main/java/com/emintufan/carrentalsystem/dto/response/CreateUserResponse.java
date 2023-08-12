package com.emintufan.carrentalsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserResponse {
    private int id;
    private String name;
    private String surName;
    private String phone;
    private String address;
    private String email;
    private String type;
    private String gender;
    private String identityNo;
}
