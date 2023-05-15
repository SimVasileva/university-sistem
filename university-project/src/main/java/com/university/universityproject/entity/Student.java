package com.university.universityproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student extends BaseUser {

    @Column
    private String facultyNumber;
    @ManyToOne
    private Speciality speciality;
    @ManyToOne
    private Subject subject;
    @Column
    private Double grade;

}
