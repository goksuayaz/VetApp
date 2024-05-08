package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.VaccineService;
import com.vetapp.veterinary.entity.Vaccine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineManager implements VaccineService {

    @Override
    public Vaccine getById(Long id) {
        return null;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        return null;
    }

    @Override
    public List<Vaccine> findAll() {
        return null;
    }

    @Override
    public Vaccine findByName(String name) {
        return null;
    }
}
