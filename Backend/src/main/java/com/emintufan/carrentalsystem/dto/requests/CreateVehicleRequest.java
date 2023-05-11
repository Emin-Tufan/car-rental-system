package com.emintufan.carrentalsystem.dto.requests;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateVehicleRequest {

    @NotNull(message = "Brand Can not be empty !")
    private String brand;

    @NotNull(message = "Model Can not be empty !")
    private String model;

    @Digits(integer = 4,fraction = 0,message = "Year Can not be empty !")
    private int year;

    @NotNull(message = "Color Can not be empty !")
    private String color;

    @NotNull(message = "Plate Number Can not be empty !")
    private String plateNumber;

    @NotNull(message = "Price Can not be empty !")
    private BigDecimal rentalPrice;
}
