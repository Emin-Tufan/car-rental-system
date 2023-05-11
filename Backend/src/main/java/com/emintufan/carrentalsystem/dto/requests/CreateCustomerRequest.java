package com.emintufan.carrentalsystem.dto.requests;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateCustomerRequest {

    @NotNull(message = "Name can not be empty !")
    private String name;

    @NotNull(message = "Surname can not be empty !")
    private String surName;

    @Digits(integer = 11,fraction = 0,message ="Invalid Id number" )
    @NotNull(message = "Id can not be empty !")
    private String identityNo;

    @Digits(integer = 11,fraction = 0,message ="Invalid phone number" )
    @NotNull(message = "Phone can not be empty !")
    private String phone;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format")
    private String email;


    @Min(value = 8,message = "password must be greater than 8 characters !")
    private String password;

    private String type = "CUSTOMER";

    @NotNull
    private String gender;
}
