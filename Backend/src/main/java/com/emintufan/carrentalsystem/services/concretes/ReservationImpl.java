package com.emintufan.carrentalsystem.services.concretes;

import com.emintufan.carrentalsystem.dao.ReservationDao;
import com.emintufan.carrentalsystem.dao.VehicleDao;
import com.emintufan.carrentalsystem.entitys.Reservation;
import com.emintufan.carrentalsystem.entitys.Vehicle;
import com.emintufan.carrentalsystem.services.abstracts.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationImpl implements ReservationService {

    private VehicleDao vehicleDao;
    private ReservationDao reservationDao;

    @Autowired
    public ReservationImpl(VehicleDao vehicleDao, ReservationDao reservationDao) {
        this.vehicleDao = vehicleDao;
        this.reservationDao = reservationDao;
    }

    @Override
    public boolean addCar(Vehicle vehicle) {

        int id = vehicle.getId();
        Optional<Vehicle> vehicle1 = vehicleDao.findById(id);

        if (vehicle1.get().getAvailable() > 0) {
            vehicle1.get().setAvailable(vehicle1.get().getAvailable() - 1);
            vehicleDao.save(vehicle1.get());
        } else {
            vehicleDao.deleteById(id);
        }
        return true;
    }

    @Override
    public String updateReservation(Reservation reservation) {


        return null;
    }

    @Override
    public String deleteReservation(int id) {
        return null;
    }

    @Override
    public String addReservation(int userId) {
        return null;
    }

    @Override
    public Reservation findReservationById(int id) {
        Optional<Reservation> reservation = reservationDao.findById(id);
        if(reservation.isPresent())
            return reservation.get();
        return null;
    }

}
