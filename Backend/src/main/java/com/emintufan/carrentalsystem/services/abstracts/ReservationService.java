package com.emintufan.carrentalsystem.services.abstracts;

import com.emintufan.carrentalsystem.dto.requests.CreateVehicleRequest;
import com.emintufan.carrentalsystem.entitys.Reservation;
import com.emintufan.carrentalsystem.entitys.Vehicle;
import org.springframework.stereotype.Repository;

public interface ReservationService {

    boolean addCar(Vehicle createVehicleRequest);
    String updateReservation(Reservation reservation);
    String deleteReservation(int id);
    String addReservation(int userId);
    Reservation findReservationById(int id);

}
