package com.emintufan.carrentalsystem.dao;

import com.emintufan.carrentalsystem.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle,Integer> {
}
