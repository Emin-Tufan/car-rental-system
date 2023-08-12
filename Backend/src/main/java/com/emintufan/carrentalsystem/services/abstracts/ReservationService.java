package com.emintufan.carrentalsystem.services.abstracts;

import com.emintufan.carrentalsystem.dto.request.CreateReservationRequest;
import com.emintufan.carrentalsystem.dto.response.CreateReservationResponse;
import com.emintufan.carrentalsystem.entities.Reservation;

import java.util.List;

public interface ReservationService {

    List<CreateReservationResponse> getReservations();
    String updateReservation(Reservation reservation);
    boolean deleteReservation(int id);
    Reservation findReservationById(int id);

    boolean addReservation(int vehicleId, int userId, CreateReservationRequest request);
}
