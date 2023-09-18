package com.university.universityproject.model.dto;

import com.university.universityproject.model.FacultyEntity;
import com.university.universityproject.model.SpecialityEntity;

import lombok.Getter;

@Getter
public class AddStudentDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String facultyNumber;
    private String faculty;
    private String speciality;


    public AddStudentDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AddStudentDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AddStudentDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public AddStudentDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public AddStudentDTO setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
        return this;
    }

    public AddStudentDTO setFaculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    public AddStudentDTO setSpeciality(String speciality) {
        this.speciality = speciality;
        return this;
    }
}
