package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.DoctorService;
import com.vetapp.veterinary.entity.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DoctorManager implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Override
    public Doctor getByID(long id) {
        if (this.doctorRepository.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return this.doctorRepository.findById(id);
        }
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Override
    public String delete(long id) {
        if (this.doctorRepository.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            this.doctorRepository.delete(this.getByID(id));
            return "deleted the record with id: " + id;
        }
    }

    @Override
    public Doctor update(Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(doctor.getId());
        if (existingDoctor==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            existingDoctor.setName(doctor.getName());
            existingDoctor.setCity(doctor.getCity());
            existingDoctor.setAddress(doctor.getAddress());
            existingDoctor.setMail(doctor.getMail());
            existingDoctor.setPhone(doctor.getPhone());
            return this.doctorRepository.save(doctor);
        }

    }

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepository.findAll();
    }
}


}