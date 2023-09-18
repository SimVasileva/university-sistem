package com.university.universityproject.service;

import org.springframework.stereotype.Service;

public interface SubjectService {
  void createSubject(String facultyNumber, String subject, boolean passed);
}
