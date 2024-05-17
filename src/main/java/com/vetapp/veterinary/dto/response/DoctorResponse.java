package com.vetapp.veterinary.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {

    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}

