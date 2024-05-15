package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.VaccineService;
import com.vetapp.veterinary.entity.Vaccine;
import com.vetapp.veterinary.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class VaccineManager implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public Vaccine getById(Long id) {
        if (this.vaccineRepository.findById(id)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            return this.vaccineRepository.findById(id);
        }
    }



    @Override
    public Vaccine save(Vaccine vaccine) {
        Long animalID = vaccine.getAnimal().getId();
        Long vaccineID = vaccine.getVaccine().getId();
        Vaccine oldVaccine = vaccineRepository.findByVaccine(animalID,vaccineID);

        if (oldVaccine != null && oldVaccine.getPrtFinish().isAfter(vaccine.getPrtStart())){
            throw  new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return vaccineRepository.save(vaccine);
    }

    @Override
    public String delete(Long id) {
        if (this.vaccineRepository.findById(id)==null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            this.vaccineRepository.delete(this.getById(id));
            return "deleted the record with id: " + id;
        }
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        Vaccine existingVaccine = vaccineRepository.findById((int) Vaccine.getId());
        if (existingVaccine == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            existingVaccine.setPrtStart(vaccine.getPrtStart());
            existingVaccine.setPrtFinish(vaccine.getPrtFinish());
            return this.vaccineRepository.save(vaccine);
        }

    }

    @Override
    public List<Vaccine> findAll() {
        return this.vaccineRepository.findAll();
    }

    @Override
    public List<Vaccine> findVaccineByAnimalId(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id);
        if (vaccine!=null){
            return vaccine.getAnimal().getVaccineList();
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Vaccine> findAllByPrtStartBetween(LocalDate prt_start, LocalDate prt_finish) {
        return vaccineRepository.findAllByPrtStartBetween(prt_start,prt_finish);
    }


}


}
