package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.AvailableDateService;
import com.vetapp.veterinary.entity.AvailableDate;
import com.vetapp.veterinary.repository.AvailableDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AvalaibleDateManager implements AvailableDateService {

    @Autowired
    private AvailableDateRepository availableDateRepository;

    @Override
    public AvailableDate getByID(long id) {

        if (this.availableDateRepository.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return this.availableDateRepository.findById(id);
        }
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return this.availableDateRepository.save(availableDate);
    }

    @Override
    public String delete(long id) {
        if (this.availableDateRepository.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            this.availableDateRepository.delete(this.getByID(id));
            return "deleted the record with id: " + id;
        }
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        AvailableDate existingDoctorAv = availableDateRepository.findById(availableDate.getId());
        if (existingDoctorAv==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            existingDoctorAv.setAvailableDays(availableDate.getAvailableDays());
            return this.availableDateRepository.save(availableDate);
        }

    }

    @Override
    public List<AvailableDate> findAll() {
        return this.availableDateRepository.findAll();
    }
}


}