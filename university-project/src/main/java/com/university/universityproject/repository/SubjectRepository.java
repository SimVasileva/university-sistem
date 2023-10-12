package com.university.universityproject.repository;

import com.university.universityproject.model.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    Optional<SubjectEntity> findByName(String name);
    List<SubjectEntity> findAllByStudentId(Long studentId);
    Optional<SubjectEntity> findByNameAndStudentId(String name, Long studentId);
}
