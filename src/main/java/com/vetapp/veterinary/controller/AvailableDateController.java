package com.vetapp.veterinary.controller;


import com.vetapp.veterinary.business.abs.AvailableDateService;
import com.vetapp.veterinary.entity.AvailableDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDate finByID(@PathVariable("id") long id) {
        return this.availableDateService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDate save(@RequestBody AvailableDate availableDate) {
        return this.availableDateService.save(availableDate);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDate update(@RequestBody AvailableDate doctorAvailability) {
        return availableDateService.save(doctorAvailability);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        return this.availableDateService.delete(id);
    }



}
