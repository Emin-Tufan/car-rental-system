package com.emintufan.carrentalsystem.dao;

import com.emintufan.carrentalsystem.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDao extends JpaRepository<Reservation,Integer> {
}
