package com.university.universityproject.repository;

import com.university.universityproject.model.GradeEntity;
import com.university.universityproject.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
    GradeEntity getByGrade(Integer grade);
    List<GradeEntity> findByStudent(StudentEntity student);
}
