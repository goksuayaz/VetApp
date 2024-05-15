package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.VaccinesService;
import com.vetapp.veterinary.entity.Vacciness;
import com.vetapp.veterinary.repository.VaccinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class VaccinessManager implements VaccinesService {

    @Autowired
    private VaccinessRepository vaccinessRepository;
    @Override
    public Vacciness getByID(int id) {
        if (this.vaccinessRepository.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return this.vaccinessRepository.findById(id);
        }
    }

    @Override
    public Vacciness save(Vacciness vacciness) {
        return this.vaccinessRepository.save(vacciness);
    }

    @Override
    public String delete(int id) {
        if (this.vaccinessRepository.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            this.vaccinessRepository.delete(this.getByID(id));
            return "deleted the record with id: " + id;
        }
    }

    @Override
    public Vacciness update(Vacciness vacciness) {
        Vacciness existingVacciness = vaccinessRepository.findById((long) vacciness.getId());
        if (existingVacciness==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            existingVacciness.setVacciness_name(vacciness.getVacciness_name());
            existingVacciness.setVacciness_code(vacciness.getVacciness_code());
            return this.vaccinessRepository.save(vacciness);
        }
    }

    @Override
    public List<Vacciness> findAll() {
        return this.vaccinessRepository.findAll();
    }
}




}
