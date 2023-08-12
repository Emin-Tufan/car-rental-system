package com.emintufan.carrentalsystem.dto.response;

import com.emintufan.carrentalsystem.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateReservationResponse {

    private int id;
    private String name;
    private String surName;
    private String phone;
    private String email;
    private String vehicle;
    private LocalDate startDate;
    private LocalDate endDate;

    public CreateReservationResponse(Reservation reservation, LocalDate startDate, LocalDate endDate) {
        this.name = reservation.getUser().getName();
        this.surName = reservation.getUser().getSurName();
        this.phone = reservation.getUser().getPhone();
        this.email = reservation.getUser().getEmail();
        this.vehicle = reservation.getVehicle().getName();
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
    }
}
