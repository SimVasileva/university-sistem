package com.university.universityproject.repository;

import com.university.universityproject.model.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<FacultyEntity, Long> {
    @Override
    Optional<FacultyEntity> findById(Long id);

    Optional<FacultyEntity> findByName(String name);
}
