package com.university.universityproject.web;

import com.university.universityproject.model.TeacherEntity;
import com.university.universityproject.model.dto.AddTeacherDTO;
import com.university.universityproject.model.dto.SearchForm;
import com.university.universityproject.service.GradeService;
import com.university.universityproject.service.SubjectService;
import com.university.universityproject.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private static final String TEACHERS = "teachers";

    public TeacherController(TeacherService teacherService, GradeService gradeService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.gradeService = gradeService;
        this.subjectService = subjectService;
    }

    @GetMapping("/add")
    public String getAddTeacher() {
        return "add-teacher";
    }

    @PostMapping("/add")
    public String addTeacher(AddTeacherDTO teacherDTO) {
        teacherService.createTeacher(teacherDTO);
        return "redirect:/home";
    }

    @GetMapping
    public String getTeachers(@ModelAttribute("searchIdForm") SearchForm searchForm) {
        return TEACHERS;
    }

    @PostMapping
    public String viewTeacher(Model model, @ModelAttribute("searchIdForm") SearchForm searchForm) {
        String[] fullName = (searchForm.getSearchString()).split(" ");
        String firstName = fullName[0];
        String lastName = fullName[1];
        List<TeacherEntity> teachers = teacherService.viewTeachers(firstName, lastName);
        model.addAttribute(TEACHERS, teachers);
        return TEACHERS;
    }

    @GetMapping("/grade")
    public String showGradeForm() {
        return "grade-student";
    }

    @PostMapping("/grade")
    public String gradeStudent(
            @RequestParam(name = "facultyNumber") String facultyNumber,
            @RequestParam(name = "subject") String subject,
            @RequestParam(name = "grade") Integer grade) {

        gradeService.createGrade(facultyNumber, subject, grade);

        return "redirect:/home";
    }

    @GetMapping("/subject")
    public String showSubjectForm() {
        return "subject-student";
    }

    @PostMapping("/subject")
    public String subjectStudent(
            @RequestParam(name = "facultyNumber") String facultyNumber,
            @RequestParam(name = "subject") String subject,
            @RequestParam(name = "passed") boolean passed) {

        subjectService.createSubject(facultyNumber, subject, passed);
        return "redirect:/home";
    }

}
