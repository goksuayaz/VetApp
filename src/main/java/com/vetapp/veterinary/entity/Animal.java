package com.vetapp.veterinary.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(name = "animal_name", nullable = false)
    private String name;

    @Column(name = "animal_species", nullable = false)
    private String species;

    @Column(name = "breed", length = 255)
    private String breed;

    @Column(name = "animal_colour", length = 55)
    private String colour;

    //@Temporal(TemporalType.DATE)
    @Column(name = "animal_date_of_birth")
    private LocalDate dateOfBirth;


    @Column(name = "animal_gender")
    private String gender;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<Vaccine> vaccines;

    @OneToMany(mappedBy = "animal",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;





}




