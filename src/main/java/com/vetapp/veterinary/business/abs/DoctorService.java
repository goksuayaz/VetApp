package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor getById(Long id);

    Doctor save(Doctor doctor);

    String delete(Long id);

    Doctor update(Doctor doctor);

    List<Doctor> findAll();

    Doctor findByName(String name);




}
