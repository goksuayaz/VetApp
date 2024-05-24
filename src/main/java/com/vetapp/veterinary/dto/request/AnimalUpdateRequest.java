package com.vetapp.veterinary.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {

    @NotNull(message = "Animal id can not be null")
    @Positive(message = "Animal Id must be positive")
    private long id;
    @NotNull(message = "Animal name can not be empty")
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    @NotNull(message = "Customer id can not be empty or null")
    @Positive(message = "Customer id must be positive")
    private Long customerId;


}

