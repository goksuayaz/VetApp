package com.vetapp.veterinary.controller;


import com.vetapp.veterinary.business.abs.AppointmenService;
import com.vetapp.veterinary.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmenService appointmenService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findAll(){
        return this.appointmenService.findAll();

    }



}