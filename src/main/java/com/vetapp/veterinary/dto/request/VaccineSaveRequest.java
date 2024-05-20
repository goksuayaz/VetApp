package com.vetapp.veterinary.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineSaveRequest {


    @NotNull(message = "Vaccine name can not be empty")
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;

    @NotNull(message = "Vaccine id can not be empty")
    private Long animalId;
}

