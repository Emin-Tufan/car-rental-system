package com.emintufan.carrentalsystem.services.abstracts;

import com.emintufan.carrentalsystem.dto.requests.CreateVehicleRequest;
import com.emintufan.carrentalsystem.entitys.Vehicle;
import org.springframework.stereotype.Repository;

public interface VehicleService {

    boolean addVehicle(CreateVehicleRequest createVehicleRequest);

    Vehicle findByid(int id);

    boolean deleteVehicle(Vehicle vehicle);
}
