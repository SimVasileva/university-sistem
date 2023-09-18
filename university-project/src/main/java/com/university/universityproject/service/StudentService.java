package com.university.universityproject.service;

import com.university.universityproject.model.StudentEntity;
import com.university.universityproject.model.dto.AddStudentDTO;

import java.util.List;

public interface StudentService {
    void createStudent(AddStudentDTO addStudentDTO);
    List<StudentEntity> viewAllStudents(String facultyNumber);
}
