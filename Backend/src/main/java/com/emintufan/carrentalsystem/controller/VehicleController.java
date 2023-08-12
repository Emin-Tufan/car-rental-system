package com.emintufan.carrentalsystem.controller;

import com.emintufan.carrentalsystem.dto.request.CreateVehicleRequest;
import com.emintufan.carrentalsystem.dto.response.CreateVehicleResponse;
import com.emintufan.carrentalsystem.entities.Vehicle;
import com.emintufan.carrentalsystem.exceptions.EntityExceptions;
import com.emintufan.carrentalsystem.services.abstracts.VehicleService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
@NoArgsConstructor
@CrossOrigin(origins = "*")
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CreateVehicleResponse>> getVehicleList() {
        try {
            List<CreateVehicleResponse> vehicleList = vehicleService.getVehicleList();
            if(vehicleList!=null)
                return (ResponseEntity.ok(vehicleList));
            else
                return (ResponseEntity.badRequest().body(null));
        }
        catch (Exception e)
        {
            throw new EntityExceptions("Vehicles Not Found!");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addVehicle(@RequestBody CreateVehicleRequest createVehicleRequest) {
        try{
            CreateVehicleRequest response = vehicleService.addVehicle(createVehicleRequest);
            URI uri = URI.create("/vehicle/add");
            if (response != null)
                return (ResponseEntity.created(uri).body("Vehicle Added !"));
            else
                return (ResponseEntity.badRequest().body("Bad Request!"));
        }
        catch (Exception e) {
            throw new EntityExceptions("Vehicle Was Not Added!");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteVehicle(@PathVariable int id)
    {
        try {
            boolean isDeleted = vehicleService.deleteVehicle(id);
            if (isDeleted)
                return (ResponseEntity.ok(true));
            else
                return (ResponseEntity.notFound().build());
        }
        catch (Exception e)
        {
            throw new EntityExceptions("Vehicle Was Not Deleted!");
        }
    }
}
