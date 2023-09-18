package com.university.universityproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "specialities")
@Getter
public class SpecialityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    public SpecialityEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public SpecialityEntity setName(String name) {
        this.name = name;
        return this;
    }
}
