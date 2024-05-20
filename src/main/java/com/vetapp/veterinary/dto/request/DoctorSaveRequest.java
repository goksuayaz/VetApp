package com.vetapp.veterinary.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSaveRequest {

    @NotNull(message = "Doctor name can not be empty")
    private String name;
    private String phone;

    @Email(message = "Invalid e-mail format")
    private String mail;
    private String address;
    private String city;
}

