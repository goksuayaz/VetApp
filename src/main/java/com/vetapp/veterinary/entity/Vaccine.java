package com.vetapp.veterinary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "vaccine")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Temporal(TemporalType.DATE)
    @Column(name = "protectionStartDate", nullable = false)
    private LocalDate protectionStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "protectionFinishDate", nullable = false)
    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id",referencedColumnName = "id")
    private Animal animal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vaccine_id",referencedColumnName = "id")
    private Vaccine vaccine;

}
