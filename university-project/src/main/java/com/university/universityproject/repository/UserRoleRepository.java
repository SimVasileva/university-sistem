package com.university.universityproject.repository;

import com.university.universityproject.model.UserRoleEntity;
import com.university.universityproject.model.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository
        extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole(UserRoleEnum role);

}
