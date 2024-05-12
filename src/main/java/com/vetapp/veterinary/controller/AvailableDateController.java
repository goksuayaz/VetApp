package com.vetapp.veterinary.controller;


import com.vetapp.veterinary.business.abs.AvailableDateService;
import com.vetapp.veterinary.entity.AvailableDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/availables")
public class AvailableDateController {

    @Autowired
    private AvailableDateService availableDateService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDate> findAll(){
        return this.availableDateService.findAll();
    }

}
