package com.university.universityproject.repository;

import com.university.universityproject.model.GradeEntity;
import com.university.universityproject.model.StudentEntity;
import com.university.universityproject.model.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    Optional<SubjectEntity> findByName(String name);

    @Override
    List<SubjectEntity> findAll();
    List<SubjectEntity> findAllByStudentId(Long studentId);
    Optional<SubjectEntity> findByNameAndStudentId(String name, Long studentId);
}
