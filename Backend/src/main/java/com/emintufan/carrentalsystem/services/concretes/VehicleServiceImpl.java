package com.emintufan.carrentalsystem.services.concretes;

import com.emintufan.carrentalsystem.dao.VehicleDao;
import com.emintufan.carrentalsystem.dto.request.CreateVehicleRequest;
import com.emintufan.carrentalsystem.dto.response.CreateVehicleResponse;
import com.emintufan.carrentalsystem.entities.Vehicle;
import com.emintufan.carrentalsystem.mapper.ModelMapperManager;
import com.emintufan.carrentalsystem.services.abstracts.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private VehicleDao vehicleDao;
    private ModelMapperManager modelMapperManager;
    @Autowired
    public VehicleServiceImpl(VehicleDao vehicleDao, ModelMapperManager modelMapperManager) {
        this.vehicleDao = vehicleDao;
        this.modelMapperManager = modelMapperManager;
    }

    @Override
    public CreateVehicleRequest addVehicle(CreateVehicleRequest createVehicleRequest) {
        Vehicle vehicle = modelMapperManager.forRequest().map(createVehicleRequest, Vehicle.class);
        if(vehicle == null)
            return (null);
        vehicleDao.save(vehicle);
        return (createVehicleRequest);
    }

    @Override
    public List<CreateVehicleResponse> getVehicleList() {
        List<Vehicle> getVehicleList = vehicleDao.findAll();
        if (getVehicleList == null)
            return null;
        else
        {
            List<CreateVehicleResponse> response = getVehicleList.stream()
                    .map(x -> modelMapperManager.forResponse().map(x, CreateVehicleResponse.class))
                    .collect(Collectors.toList());
            return response;
        }
    }

    @Override
    public Vehicle findById(int id) {
        Optional<Vehicle> vehicle = vehicleDao.findById(id);
        if (!vehicle.isPresent())
            return null;
        else return vehicle.get();
    }

    @Override
    public boolean deleteVehicle(int id) {
        Optional<Vehicle> vehicle = vehicleDao.findById(id);
        if (!vehicle.isPresent())
            return (false);
        else {
            vehicleDao.delete(vehicle.get());
            return (true);
        }
    }
}
