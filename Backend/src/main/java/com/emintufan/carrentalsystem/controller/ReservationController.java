package com.emintufan.carrentalsystem.controller;

import com.emintufan.carrentalsystem.dto.request.CreateReservationRequest;
import com.emintufan.carrentalsystem.dto.response.CreateReservationResponse;
import com.emintufan.carrentalsystem.exceptions.EntityExceptions;
import com.emintufan.carrentalsystem.services.abstracts.ReservationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@NoArgsConstructor
@CrossOrigin(origins = "*")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping("/")
    public ResponseEntity<List<CreateReservationResponse>> getUserReservations() {
        try {
            List<CreateReservationResponse> response = reservationService.getReservations();
            if (response != null)
                return ResponseEntity.ok(response);
            else
                return (ResponseEntity.badRequest().build());
        } catch (Exception e) {
            throw new EntityExceptions("Reservations Not Found !");
        }
    }

    @PostMapping("/add/{vehicleId}/{userId}")
    public ResponseEntity<String>
    addReservation(@PathVariable int vehicleId,
                   @PathVariable int  userId, @RequestBody CreateReservationRequest request) {
        try {
            URI uri = URI.create("reservation/add/vehicleId/userId");
            boolean result = reservationService.addReservation(vehicleId, userId, request);
            if(result)
                return (ResponseEntity.created(uri).body("Reservation added!"));
            else
                return (ResponseEntity.badRequest().body("Bad Request!"));
        }
        catch (Exception e)
        {
            throw new EntityExceptions("Reservation Was Not Added !");
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteReservation(@PathVariable int id)
    {
        try {
            boolean isDeleted = reservationService.deleteReservation(id);
            if (isDeleted)
                return (ResponseEntity.ok(isDeleted));
            else
                return (ResponseEntity.badRequest().body(isDeleted));
        }
        catch (Exception e)
        {
            throw new EntityExceptions("User Was Not Deleted!");
        }
    }
}
