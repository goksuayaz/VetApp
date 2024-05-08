package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.DoctorService;
import com.vetapp.veterinary.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorManager implements DoctorService {
    @Override
    public Doctor getById(Long id) {
        return null;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public Doctor update(Doctor doctor) {
        return null;
    }

    @Override
    public List<Doctor> findAll() {
        return null;
    }

    @Override
    public Doctor findByName(String name) {
        return null;
    }
}
