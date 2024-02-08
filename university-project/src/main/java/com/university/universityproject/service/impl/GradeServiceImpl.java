package com.university.universityproject.service.impl;

import com.university.universityproject.model.GradeEntity;
import com.university.universityproject.model.StudentEntity;
import com.university.universityproject.model.SubjectEntity;
import com.university.universityproject.repository.GradeRepository;
import com.university.universityproject.repository.StudentRepository;
import com.university.universityproject.repository.SubjectRepository;
import com.university.universityproject.service.GradeService;
import org.springframework.stereotype.Service;


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
        StudentEntity student = studentRepository.findByFacultyNumber(facultyNumber);

        SubjectEntity subjectEntity = subjectRepository.findByName(subject);

        if (subjectEntity == null) {
            subjectEntity = new SubjectEntity()
                    .setStudent(student)
                    .setName(subject)
                    .setPassed(true);
            student.setSubject(subjectEntity);
        }
        GradeEntity gradeEntity = new GradeEntity()
                .setGrade(grade)
                .setStudent(student)
                .setSubject(subjectEntity);

        gradeRepository.save(gradeEntity);
        subjectRepository.save(subjectEntity);
    }

}
