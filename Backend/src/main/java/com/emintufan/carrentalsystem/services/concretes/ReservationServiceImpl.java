package com.emintufan.carrentalsystem.services.concretes;

import com.emintufan.carrentalsystem.configuration.JwtService;
import com.emintufan.carrentalsystem.dao.ReservationDao;
import com.emintufan.carrentalsystem.dao.UserDao;
import com.emintufan.carrentalsystem.dao.VehicleDao;
import com.emintufan.carrentalsystem.dto.request.CreateReservationRequest;
import com.emintufan.carrentalsystem.dto.response.CreateReservationResponse;
import com.emintufan.carrentalsystem.entities.Reservation;
import com.emintufan.carrentalsystem.entities.User;
import com.emintufan.carrentalsystem.entities.Vehicle;
import com.emintufan.carrentalsystem.mapper.ModelMapperManager;
import com.emintufan.carrentalsystem.services.abstracts.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private VehicleDao vehicleDao;
    private ReservationDao reservationDao;
    private UserDao userDao;
    private ModelMapperManager modelMapperManager;
    private JwtService jwtService;

    @Autowired
    public ReservationServiceImpl(VehicleDao vehicleDao, ReservationDao reservationDao,
                                  UserDao userDao, ModelMapperManager modelMapperManager,
                                  JwtService jwtService) {
        this.vehicleDao = vehicleDao;
        this.reservationDao = reservationDao;
        this.userDao = userDao;
        this.modelMapperManager = modelMapperManager;
        this.jwtService = jwtService;
    }

    @Override
    public List<CreateReservationResponse> getReservations() {
        List<Reservation> reservation = reservationDao.findAll();
        if(!reservation.isEmpty()) {
            List<CreateReservationResponse> response = reservation.stream()
                    .map(x -> new CreateReservationResponse(x, x.getStartDate(), x.getEndDate()))
                    .collect(Collectors.toList());
            return (response);
        }
        else
            return (null);
    }

    @Override
    public String updateReservation(Reservation reservation) {


        return null;
    }


    @Override
    public boolean deleteReservation(int id) {

        Optional<Reservation> reservation = reservationDao.findById(id);
        if (reservation.isPresent()) {
            reservationDao.delete(reservation.get());
            return true;
        }
        return false;
    }

    @Override
    public Reservation findReservationById(int id) {
        Optional<Reservation> reservation = reservationDao.findById(id);
        if (reservation.isPresent())
            return reservation.get();
        return null;
    }

    @Override
    public boolean addReservation(int vehicleId, int userId, CreateReservationRequest request) {

            Optional<Vehicle> vehicle = vehicleDao.findById(vehicleId);
            Optional<User> user = userDao.findById(userId);
            if (vehicle.isPresent() && user.isPresent()) {
                Reservation reservation = new Reservation();

                reservation.setUser(user.get());
                reservation.setVehicle(vehicle.get());
                reservation.setStartDate(request.getStartDate());
                reservation.setEndDate(request.getEndDate());
                reservationDao.save(reservation);
                return (true);
            }
            else
                return (false);
    }
}
