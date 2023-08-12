package com.emintufan.carrentalsystem.dto.request;


import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserRequest {

    @NotNull(message = "Name can not be empty !")
    private String name;

    @NotNull(message = "Surname can not be empty !")
    private String surName;

    @NotNull(message = "Id can not be empty !")
    private String identityNo;

    @NotNull(message = "Phone can not be empty !")
    private String phone;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format")
    private String email;

    private String address;


    @Min(value = 8,message = "password must be greater than 8 characters !")
    private String password;

    private String type = "ROLE_CUSTOMER";

    @NotNull
    private String gender;
}
