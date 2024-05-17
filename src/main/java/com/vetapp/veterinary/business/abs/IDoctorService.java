package com.vetapp.veterinary.business.abs;

import com.vetapp.veterinary.entity.Doctor;
import org.springframework.data.domain.Page;

public interface IDoctorService {

    Doctor save(Doctor doctor);
    Doctor get(Long id);
    Page<Doctor> cursor(int page, int pageSize);
    Doctor update (Doctor doctor);
    boolean delete (long id);
}

