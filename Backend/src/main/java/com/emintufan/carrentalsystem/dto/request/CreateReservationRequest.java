package com.emintufan.carrentalsystem.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateReservationRequest {

    private LocalDate startDate;
    private LocalDate endDate;

}
