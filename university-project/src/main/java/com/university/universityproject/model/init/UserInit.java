package com.university.universityproject.model.init;

import com.university.universityproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserInit implements CommandLineRunner {

    private final UserService userService;

    public UserInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUserRoles();
        userService.initUsers();
    }
}