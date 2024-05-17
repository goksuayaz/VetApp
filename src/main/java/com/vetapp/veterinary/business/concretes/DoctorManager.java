package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.IDoctorService;
import com.vetapp.veterinary.core.config.exception.NotFoundException;
import com.vetapp.veterinary.core.utilies.Msg;
import com.vetapp.veterinary.entity.Doctor;
import com.vetapp.veterinary.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorManager implements IDoctorService {

    private final DoctorRepository doctorRepository;

    // Constructor enjeksiyonu
    public DoctorManager(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepository.save(doctor); // DoctorRepo'nun save metodu kullanılır
    }


    @Override
    public Doctor get(Long id) {

        return this.doctorRepository.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }


    @Override
    public Page<Doctor> cursor(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page,pageSize);
        return this.doctorRepository.findAll(pageable);
    }


    @Override
    public Doctor update(Doctor doctor) {
        this.get(doctor.getId());
        return this.doctorRepository.save(doctor);
    }


    @Override
    public boolean delete(long id) {
        Doctor doctor=this.get(id);
        this.doctorRepository.delete(doctor);
        return true;
    }
}

