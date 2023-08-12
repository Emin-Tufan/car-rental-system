package com.emintufan.carrentalsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicles")
@NoArgsConstructor
public class Vehicle {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "categories")
    private String categories;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "class")
    private String carClass;

    @Column(name = "min_age")
    private String minAge;

    @Column(name = "min_driving")
    private String minDriving;

    @Column(name = "gear_box_type")
    private String gearBoxType;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "luggage")
    private String luggage;

    @Column(name = "capacity")
    private String capacity;

    @Column(name = "src")
    private String src;

    @Column(name = "engine_capacity")
    private String engineCapacity;

    @Column(name = "fuel_consumption")
    private String fuelConsumption;

    @Column(name = "doors")
    private String doors;

    @Column(name = "airbag")
    private boolean airbag;

    @Column(name = "abs")
    private boolean abs;

    @Column(name = "cruise_control")
    private boolean cruiseControl;

}
