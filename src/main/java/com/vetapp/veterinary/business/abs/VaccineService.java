package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface VaccineService {

    Vaccine getById(Long id);

    Vaccine save(Vaccine vaccine);

    String delete(Long id);

    Vaccine update(Vaccine vaccine);

    List<Vaccine> findAll();

    List<Vaccine> findVaccineByAnimalId(long id);

    List<Vaccine> findAllByPrtStartBetween(LocalDate prt_start, LocalDate prt_finish);


}
