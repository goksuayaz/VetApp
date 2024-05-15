package com.vetapp.veterinary.controller;

import com.vetapp.veterinary.business.abs.VaccineService;
import com.vetapp.veterinary.entity.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;


    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> findAll(){
        return this.vaccineService.findAll();

    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vaccine finById(@PathVariable("id") long id) {
        return this.vaccineService.getById(id);
    }

    @GetMapping("/getVaccine/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> getVaccine(@PathVariable("id") long id) {
        return vaccineService.findVaccineByAnimalId(id);
    }

    @GetMapping("/getBetween")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Vaccine>> getAnimalVaccinesBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Vaccine> vaccines = vaccineService.findAllByPrtStartBetween(startDate, endDate);
        if (vaccines.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(vaccines);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Vaccine save(@RequestBody Vaccine vaccine) {
        return this.vaccineService.save(vaccine);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Vaccine update(@RequestBody Vaccine vaccine) {
        return vaccineService.save(vaccine);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        return this.vaccineService.delete((long) id);
    }


}



