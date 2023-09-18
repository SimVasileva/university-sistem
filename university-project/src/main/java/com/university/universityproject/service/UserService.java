package com.university.universityproject.service;

import com.university.universityproject.model.dto.UserRegistrationDTO;

public interface UserService {

    void initUserRoles();

    void initUsers();

    void createAccount(UserRegistrationDTO userRegistrationDTO);

}

