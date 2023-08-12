package com.emintufan.carrentalsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateVehicleResponse {

    private int id;

    private String categories;

    private String name;

    private String model;

    private String carClass;

    private String minAge;

    private String minDriving;

    private String gearBoxType;

    private String fuelType;

    private String luggage;

    private String capacity;

    private String src;

    private String engineCapacity;

    private String fuelConsumption;

    private String doors;

    private boolean airbag;

    private boolean abs;

    private boolean cruiseControl;
}
