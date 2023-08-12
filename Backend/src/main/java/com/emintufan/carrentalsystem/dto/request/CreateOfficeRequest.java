package com.emintufan.carrentalsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfficeRequest {

    private String name;
    private String phone;
    private String address;
    private String email;
    private String time;
}
