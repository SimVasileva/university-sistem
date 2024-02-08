package com.university.universityproject.repository;

import com.university.universityproject.model.StudentEntity;
import com.university.universityproject.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
   StudentEntity findByFacultyNumber(String facultyNumber);
   List<StudentEntity> findByFirstName(String firstName);
  StudentEntity findByUserId(Long id);
}
