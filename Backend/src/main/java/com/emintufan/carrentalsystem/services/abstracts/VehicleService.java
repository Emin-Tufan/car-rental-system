package com.emintufan.carrentalsystem.services.abstracts;

import com.emintufan.carrentalsystem.dto.request.CreateVehicleRequest;
import com.emintufan.carrentalsystem.dto.response.CreateVehicleResponse;
import com.emintufan.carrentalsystem.entities.Vehicle;

import java.util.List;

public interface VehicleService {

    CreateVehicleRequest addVehicle(CreateVehicleRequest createVehicleResponse);
    List<CreateVehicleResponse> getVehicleList();

    Vehicle findById(int id);

    boolean deleteVehicle(int vehicle);

}
