package com.emintufan.carrentalsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfficeResponse {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String time;
}
