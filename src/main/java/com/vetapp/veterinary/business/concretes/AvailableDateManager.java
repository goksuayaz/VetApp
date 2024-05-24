package com.vetapp.veterinary.business.concretes;

import com.vetapp.veterinary.business.abs.IAvailableDateService;
import com.vetapp.veterinary.core.config.exception.NotFoundException;
import com.vetapp.veterinary.core.utilies.Msg;
import com.vetapp.veterinary.entity.Animal;
import com.vetapp.veterinary.entity.Appointment;
import com.vetapp.veterinary.entity.AvailableDate;
import com.vetapp.veterinary.repository.AppointmentRepository;
import com.vetapp.veterinary.repository.AvailableDateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepository availableDateRepository;

    public AvailableDateManager(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return availableDateRepository.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepository.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.availableDateRepository.findAll(pageable);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        this.get(availableDate.getId());
        return this.availableDateRepository.save(availableDate);
    }

    @Override
    public boolean delete(long id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepository.delete(availableDate);
        return true;
    }

    public void availableDoctor(Long doctorId, LocalDate availableDate) {
        boolean doctorAvailable = this.availableDateRepository
                .findByDoctorIdAndAvailableDate(doctorId, availableDate).isPresent();
        if (!doctorAvailable) {
            throw new IllegalArgumentException("Doctor is not available on the selected date.");
        }
    }




}
