package com.emintufan.carrentalsystem.dto.responses;

import java.time.LocalDateTime;

public class CreateReservationResponse {

    private int id;
    private String name;
    private String phone;
    private String email;
    private String vehicle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
