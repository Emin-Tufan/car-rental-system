package com.emintufan.carrentalsystem.controller;

import com.emintufan.carrentalsystem.dto.request.CreateOfficeRequest;
import com.emintufan.carrentalsystem.dto.response.CreateOfficeResponse;
import com.emintufan.carrentalsystem.entities.Office;
import com.emintufan.carrentalsystem.exceptions.EntityExceptions;
import com.emintufan.carrentalsystem.services.abstracts.OfficeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/office")
@NoArgsConstructor
@CrossOrigin(origins = "*")
public class OfficeController {

    private OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CreateOfficeResponse>> getOfficeList() {
        try {
            List<CreateOfficeResponse> vehicleList = officeService.getOfficeList();
            if (vehicleList != null)
                return (ResponseEntity.ok(vehicleList));
            else
                return (ResponseEntity.badRequest().build());
        }
        catch (Exception e)
        {
            throw new EntityExceptions("Office Not Found!");
        }
    }
    @PostMapping("/add")
    public ResponseEntity<String> addVehicle(@RequestBody CreateOfficeRequest createOfficeRequest) {
        try
        {
            CreateOfficeRequest response = officeService.addOffice(createOfficeRequest);
            URI uri = URI.create("/office/add");
            if (response != null)
                return  (ResponseEntity.created(uri).body("Office Added !"));
            else
                return (ResponseEntity.badRequest().build());
        }
        catch (Exception e)
        {
            throw new EntityExceptions("Office Was Not Added !");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOffice(@PathVariable int id)
    {
        try {
            boolean isDeleted = officeService.deleteOffice(id);
            if (isDeleted)
                return (ResponseEntity.ok("Office Deleted!"));
            else
                return (ResponseEntity.badRequest().build());
        }
        catch (Exception e)
        {
            throw new EntityExceptions("Office Was Not Deleted!");
        }

    }
}
