package com.university.universityproject.web;

import com.university.universityproject.model.StudentEntity;
import com.university.universityproject.model.TeacherEntity;
import com.university.universityproject.model.UserEntity;
import com.university.universityproject.repository.StudentRepository;
import com.university.universityproject.repository.TeacherRepository;
import com.university.universityproject.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public HomeController(UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Get the username
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
        StudentEntity student = studentRepository.findByUserId(user.getId());
        TeacherEntity teacher = teacherRepository.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("teacher", teacher);
        model.addAttribute("student", student);
        return "index";
    }
}
