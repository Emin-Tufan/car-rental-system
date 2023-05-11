package com.emintufan.carrentalsystem.controllers;

import com.emintufan.carrentalsystem.dto.requests.CreateCustomerRequest;
import com.emintufan.carrentalsystem.dto.requests.CreateVehicleRequest;
import com.emintufan.carrentalsystem.entitys.Reservation;
import com.emintufan.carrentalsystem.entitys.User;
import com.emintufan.carrentalsystem.entitys.Vehicle;
import com.emintufan.carrentalsystem.services.abstracts.CustomerService;
import com.emintufan.carrentalsystem.services.abstracts.ReservationService;
import com.emintufan.carrentalsystem.services.abstracts.VehicleService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
@NoArgsConstructor
public class AdminController {

    private CustomerService customerService;
    private ReservationService reservationService;
    private VehicleService vehicleService;


    @Autowired
    public AdminController(VehicleService vehicleService, CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
        this.vehicleService = vehicleService;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody CreateCustomerRequest customerRequest)
    {
        customerService.addUser(customerRequest);
        return "addedUser";
    }
    @DeleteMapping("delete/{userId}")
    public String deleteUser(@PathVariable int userId)
    {
        User user = customerService.findUserById(userId);
        if(customerService.deleteUser(user))
            return "deleted";

        return "error";
    }
    @PutMapping("update")
    public String updateUser(@RequestBody CreateCustomerRequest customerRequest)
    {
        customerService.addUser(customerRequest);
        return "update";
    }
    @PostMapping("/addCar")
    public String addCar(@RequestBody CreateVehicleRequest createVehicleRequest)
    {
        if(createVehicleRequest != null)
        {
            vehicleService.addVehicle(createVehicleRequest);
            return "success";
        }
        else return "error";
    }
    @PutMapping("addCar")
    public String updateCar(@RequestBody CreateVehicleRequest createVehicleRequest)
    {
        if(createVehicleRequest != null)
        {
            vehicleService.addVehicle(createVehicleRequest);
            return "success";
        }
        else return "error";
    }

    @DeleteMapping("delete/{vehicleId}")
    public String deleteCar(@PathVariable int vehicleId)
    {
        Vehicle vehicle = vehicleService.findByid(vehicleId);
        if(vehicleService.deleteVehicle(vehicle))
            return "deleted";

        return "error";
    }

    @PutMapping("reserve/{reserveId}")
    public String updateReservation(@PathVariable int reserveId)
    {
        Reservation reservation = reservationService.findReservationById(reserveId);


        return "reserve";
    }



}
