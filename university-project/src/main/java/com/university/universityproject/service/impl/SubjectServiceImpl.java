package com.university.universityproject.service.impl;

import com.university.universityproject.model.StudentEntity;
import com.university.universityproject.model.SubjectEntity;
import com.university.universityproject.repository.StudentRepository;
import com.university.universityproject.repository.SubjectRepository;
import com.university.universityproject.service.SubjectService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void createSubject(String facultyNumber, String subject, boolean passed) {
        Optional<StudentEntity> studentOpt = studentRepository.findByFacultyNumber(facultyNumber);
        if (studentOpt.isPresent()){
            StudentEntity student = studentOpt.get();
            SubjectEntity subjectEntity = new SubjectEntity().setName(subject).setPassed(passed).setStudent(student);
            subjectRepository.save(subjectEntity);
        } else {
            throw new UsernameNotFoundException("Not found " + facultyNumber);
        }
    }
}
