package com.emintufan.carrentalsystem.controllers;

import com.emintufan.carrentalsystem.dto.requests.CreateVehicleRequest;
import com.emintufan.carrentalsystem.services.abstracts.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "reserve")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(name = "added")
    public boolean addCar(@RequestBody CreateVehicleRequest createVehicleRequest)
    {


        return true;
    }
}
