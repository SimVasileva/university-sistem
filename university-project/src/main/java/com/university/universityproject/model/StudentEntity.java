package com.university.universityproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "students")
@Getter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String facultyNumber;
    @Column
    private String grupa;
    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private SpecialityEntity speciality;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private FacultyEntity faculty;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public StudentEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public StudentEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentEntity setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
        return this;
    }

    public StudentEntity setGrupa(String grupa) {
        this.grupa = grupa;
        return this;
    }

    public StudentEntity setSubject(SubjectEntity subject) {
        this.subject = subject;
        return this;
    }

    public StudentEntity setSpeciality(SpecialityEntity speciality) {
        this.speciality = speciality;
        return this;
    }

    public StudentEntity setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
        return this;
    }

    public StudentEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
