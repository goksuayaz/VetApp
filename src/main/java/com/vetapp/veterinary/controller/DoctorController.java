package com.vetapp.veterinary.controller;

import com.vetapp.veterinary.business.abs.DoctorService;
import com.vetapp.veterinary.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> findAll(){
        return this.doctorService.findAll();

    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor finByID(@PathVariable("id") long id) {
        return this.doctorService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor save(@RequestBody Doctor doctor) {
        return this.doctorService.save(doctor);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Doctor update(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        return this.doctorService.delete(id);
    }
}


