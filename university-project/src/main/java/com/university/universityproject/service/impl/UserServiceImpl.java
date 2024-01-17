package com.university.universityproject.service.impl;

import java.util.List;

import com.university.universityproject.model.FacultyEntity;
import com.university.universityproject.model.SpecialityEntity;
import com.university.universityproject.model.StudentEntity;
import com.university.universityproject.model.TeacherEntity;
import com.university.universityproject.model.UserEntity;
import com.university.universityproject.model.UserRoleEntity;
import com.university.universityproject.model.dto.UserRegistrationDTO;
import com.university.universityproject.repository.FacultyRepository;
import com.university.universityproject.repository.SpecialityRepository;
import com.university.universityproject.repository.StudentRepository;
import com.university.universityproject.repository.TeacherRepository;
import com.university.universityproject.repository.UserRepository;
import com.university.universityproject.repository.UserRoleRepository;
import com.university.universityproject.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.university.universityproject.model.UserRoleEnum.ADMIN;
import static com.university.universityproject.model.UserRoleEnum.STUDENT;
import static com.university.universityproject.model.UserRoleEnum.TEACHER;

@Service
public class UserServiceImpl implements UserService {

    private final UserDetailsService userDetailsService;
    private final UserRoleRepository userRoleRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;
    private final FacultyRepository facultyRepository;
    private final SpecialityRepository specialityRepository;

    public UserServiceImpl(
            UserDetailsService userDetailsService,
            UserRoleRepository userRoleRepository,
            StudentRepository studentRepository, TeacherRepository teacherRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.default.password}") String defaultPassword,
            FacultyRepository facultyRepository,
            SpecialityRepository specialityRepository) {
        this.userDetailsService = userDetailsService;
        this.userRoleRepository = userRoleRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
        this.facultyRepository = facultyRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public void initUserRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(ADMIN);
            UserRoleEntity studentRole = new UserRoleEntity().setRole(STUDENT);
            UserRoleEntity teacherRole = new UserRoleEntity().setRole(TEACHER);

            userRoleRepository.saveAll(List.of(adminRole, studentRole, teacherRole));
        }
    }

    @Override
    public void initUsers() {
        if (userRepository.count() == 0) {

            UserEntity adminUser = new UserEntity()
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setEmail("admin@example.com")
                    .setPassword(passwordEncoder.encode(defaultPassword))
                    .setRoles(userRoleRepository.findAll());

            UserRoleEntity teacherRole = userRoleRepository.findByRole(TEACHER)
                    .orElseThrow(() -> new IllegalStateException("Roles are not initialized properly."));

            UserEntity teacherUser = new UserEntity()
                    .setFirstName("Teacher")
                    .setLastName("Teacher")
                    .setEmail("teacher@example.com")
                    .setPassword(passwordEncoder.encode(defaultPassword))
                    .setRoles(List.of(teacherRole));

            FacultyEntity faculty = new FacultyEntity().setName("FTK");
            facultyRepository.save(faculty);

            SpecialityEntity speciality = new SpecialityEntity().setName("Example Speciality");
            specialityRepository.save(speciality);


            UserRoleEntity studentRole = userRoleRepository.findByRole(STUDENT)
                    .orElseThrow(() -> new IllegalStateException("Roles are not initialized properly."));

            UserEntity studentUser = new UserEntity()
                    .setFirstName("Student")
                    .setLastName("Student")
                    .setEmail("student@example.com")
                    .setPassword(passwordEncoder.encode(defaultPassword))
                    .setRoles(List.of(studentRole));


            userRepository.saveAll(List.of(adminUser, teacherUser, studentUser));

            StudentEntity student = new StudentEntity()
                    .setFirstName(studentUser.getFirstName())
                    .setLastName(studentUser.getLastName())
                    .setUser(studentUser)
                    .setFacultyNumber("111111111")
                    .setSpeciality(speciality)
                    .setFaculty(faculty)
                    .setGrupa("1");

            TeacherEntity teacher = new TeacherEntity()
                    .setFirstName(teacherUser.getFirstName())
                    .setLastName(teacherUser.getLastName())
                    .setEmail(teacherUser.getEmail())
                    .setFaculty(faculty)
                    .setRoom("1111")
                    .setUser(teacherUser);

            teacherRepository.save(teacher);
            studentRepository.save(student);
        }
    }

    @Override
    public void createAccount(UserRegistrationDTO userRegistrationDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity
                .setFirstName(userRegistrationDTO.getFirstName())
                .setLastName(userRegistrationDTO.getLastName())
                .setEmail(userRegistrationDTO.getEmail())
                .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        userRepository.save(userEntity);

        var userDetails = userDetailsService.loadUserByUsername(userRegistrationDTO.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }

}