package com.emintufan.carrentalsystem.services.concretes;

import com.emintufan.carrentalsystem.dao.VehicleDao;
import com.emintufan.carrentalsystem.dto.requests.CreateVehicleRequest;
import com.emintufan.carrentalsystem.entitys.Vehicle;
import com.emintufan.carrentalsystem.mappers.ModelMapperManager;
import com.emintufan.carrentalsystem.services.abstracts.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleImpl implements VehicleService {

    private VehicleDao vehicleDao;
    private ModelMapperManager modelMapperManager;

    @Autowired
    public VehicleImpl(VehicleDao vehicleDao,ModelMapperManager modelMapperManager) {
        this.vehicleDao = vehicleDao;
        this.modelMapperManager = modelMapperManager;
    }

    @Override
    public boolean addVehicle(CreateVehicleRequest createVehicleRequest) {

        if(createVehicleRequest != null) {
            Vehicle vehicle = modelMapperManager.forRequest().map(createVehicleRequest,Vehicle.class);
            vehicleDao.save(vehicle);
            return true;
        }

        return false;
    }

    @Override
    public Vehicle findByid(int id) {
        Optional<Vehicle> vehicle = vehicleDao.findById(id);
        return vehicle.get();
    }

    @Override
    public boolean deleteVehicle(Vehicle vehicle) {
        vehicleDao.delete(vehicle);
        return true;
    }
}
