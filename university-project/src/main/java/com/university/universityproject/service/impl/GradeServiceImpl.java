package com.university.universityproject.service.impl;

import com.university.universityproject.model.GradeEntity;
import com.university.universityproject.model.StudentEntity;
import com.university.universityproject.model.SubjectEntity;
import com.university.universityproject.repository.GradeRepository;
import com.university.universityproject.repository.StudentRepository;
import com.university.universityproject.repository.SubjectRepository;
import com.university.universityproject.service.GradeService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;

    public GradeServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public void createGrade(String facultyNumber, String subject, Integer grade) {
        Optional<StudentEntity> studentOpt = studentRepository.findByFacultyNumber(facultyNumber);
        if (studentOpt.isPresent()) {
            StudentEntity student = studentOpt.get();
            Optional<SubjectEntity> subjectOpt = subjectRepository.findByNameAndStudentId(subject, student.getId());

            SubjectEntity subjectEntity = subjectOpt.get();
            GradeEntity gradeEntity = new GradeEntity().setGrade(grade).setStudent(student).setSubject(subjectEntity);
            subjectEntity.setGrade(gradeEntity);

            subjectRepository.save(subjectEntity);
            gradeRepository.save(gradeEntity);
        } else {
            throw new UsernameNotFoundException("Not found " + facultyNumber);
        }
    }

}
