package com.vetapp.veterinary.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    //@Temporal(TemporalType.TIME)
    @Column(name = "appointment_date")
    private LocalDateTime appointmentDateTime;


    @ManyToOne
    @JoinColumn(name = "appointment_doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctor;

    @ManyToOne()
    @JoinColumn(name ="appointment_animal_id",referencedColumnName = "animal_id")
    private Animal animal;


}