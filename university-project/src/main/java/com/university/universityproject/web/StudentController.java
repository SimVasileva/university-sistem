package com.university.universityproject.web;

import com.university.universityproject.model.GradeEntity;
import com.university.universityproject.model.StudentEntity;
import com.university.universityproject.model.SubjectEntity;
import com.university.universityproject.model.UserEntity;
import com.university.universityproject.model.dto.AddStudentDTO;
import com.university.universityproject.repository.GradeRepository;
import com.university.universityproject.repository.StudentRepository;
import com.university.universityproject.repository.SubjectRepository;
import com.university.universityproject.repository.UserRepository;
import com.university.universityproject.service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/students")
@PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private static final String STUDENT = "student";
    private static final String SUBJECTS = "subjects";
    private final GradeRepository gradeRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository, UserRepository userRepository,
                             SubjectRepository subjectRepository,
                             GradeRepository gradeRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/add")
    public String getAddStudent() {
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(AddStudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
        return "redirect:/students/add";
    }

    @GetMapping("/grades")
    public String getGrades(Model model, Principal principal) {
        try {
            String email = principal.getName(); // Get the username
            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
            model.addAttribute(STUDENT, user);
            StudentEntity student = studentRepository.findByUserId(user.getId());
            if (student != null) {
                List<GradeEntity> gradeList = gradeRepository.findAllByStudentId(student.getId());
                model.addAttribute("grades", gradeList);
            } else {
                // Handle the case where student is null
                model.addAttribute("error", "Student not found for user: " + email);
            }
        } catch (Exception e) {
            // Log and handle exceptions
            model.addAttribute("error", "An error occurred while retrieving grades.");
        }
        return "student-grades";
    }

    @GetMapping("/subjects")
    public String getSubjects(Model model,Principal principal) {
        try {
            String email = principal.getName(); // Get the username
            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
            model.addAttribute(STUDENT, user);
            StudentEntity student = studentRepository.findByUserId(user.getId());
            if (student != null) {
                List<SubjectEntity> subjectList = subjectRepository.findAll();
                model.addAttribute(SUBJECTS, subjectList);
            } else {
                // Handle the case where student is null
                model.addAttribute("error", "Student not found for user: " + email);
            }
        } catch (Exception e) {
            // Log and handle exceptions
            model.addAttribute("error", "An error occurred while retrieving grades.");
        }
        return SUBJECTS;
    }

//    @PostMapping("/subjects")
//    public String viewSubjects(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName(); // Get the username
//        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
//
//        StudentEntity student = studentRepository.findByUserId(user.getId());
//        model.addAttribute(STUDENT, student);
//
//        List<SubjectEntity> subjectList = subjectRepository.findAll();
//
//        model.addAttribute(SUBJECTS, subjectList);
//        return SUBJECTS;
//    }
//    @GetMapping
//    public String getStudent(@ModelAttribute("searchForm") SearchForm searchForm) {
//        return "students";
//    }
//
//    @PostMapping("/view")
//    public String viewStudent(Model model, @ModelAttribute("searchString") String searchString) {
//        List<StudentEntity> students = studentService.viewAllStudents(searchString);
//        model.addAttribute("students", students);
//        return "students";
//    }


}
