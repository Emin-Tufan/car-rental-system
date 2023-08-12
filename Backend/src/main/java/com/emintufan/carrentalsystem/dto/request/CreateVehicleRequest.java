package com.emintufan.carrentalsystem.dto.request;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateVehicleRequest {

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
