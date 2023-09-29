package com.university.universityproject.repository;

import com.university.universityproject.model.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    List<TeacherEntity> findAllByFirstName(String firstName);

    List<TeacherEntity> findAllByLastName(String lastName);

    List<TeacherEntity> findAllByFirstNameAndAndLastName(String firstName, String lastName);

    Optional<TeacherEntity> findByEmail(String email);
}
