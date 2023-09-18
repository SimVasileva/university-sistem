package com.university.universityproject.service;

import com.university.universityproject.model.TeacherEntity;
import com.university.universityproject.model.dto.AddTeacherDTO;

import java.util.List;

public interface TeacherService {
    void createTeacher(AddTeacherDTO addTeacherDTO);
    List<TeacherEntity> viewTeachers (String firstName, String lastName);
}
