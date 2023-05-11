package com.emintufan.carrentalsystem.dto.requests;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateReservationRequest {

    @NotNull(message = "Name can not be empty !")
    private String name;

    @NotNull(message = "Surname can not be empty !")
    private String surName;

    @Digits(integer = 11,fraction = 0,message ="Invalid phone number" )
    @NotNull(message = "Phone can not be empty !")
    private String phone;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format")
    private String email;

    private String vehicle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
