package com.university.universityproject.service.impl;

import com.university.universityproject.model.FacultyEntity;
import com.university.universityproject.model.TeacherEntity;
import com.university.universityproject.model.UserEntity;
import com.university.universityproject.model.UserRoleEntity;
import com.university.universityproject.model.dto.AddTeacherDTO;
import com.university.universityproject.repository.FacultyRepository;
import com.university.universityproject.repository.TeacherRepository;
import com.university.universityproject.repository.UserRepository;
import com.university.universityproject.repository.UserRoleRepository;
import com.university.universityproject.service.TeacherService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.university.universityproject.model.UserRoleEnum.TEACHER;
@Service
public class TeacherServiceImpl implements TeacherService {
    private final UserRoleRepository userRoleRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FacultyRepository facultyRepository;

    public TeacherServiceImpl(UserRoleRepository userRoleRepository, TeacherRepository teacherRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, FacultyRepository facultyRepository) {
        this.userRoleRepository = userRoleRepository;
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public void createTeacher(AddTeacherDTO addTeacherDTO) {
        FacultyEntity faculty = null;
        Optional<FacultyEntity> facultyOpt = facultyRepository.findByName(addTeacherDTO.getFaculty());
        if (facultyOpt.isEmpty()) {
            faculty = new FacultyEntity().setName(addTeacherDTO.getFaculty());

            facultyRepository.save(faculty);
        } else {
            faculty = facultyOpt.get();
        }
        UserRoleEntity teacherRole = userRoleRepository.findByRole(TEACHER).orElseThrow(NullPointerException::new);
        UserEntity userEntity = new UserEntity();
        userEntity
                .setFirstName(addTeacherDTO.getFirstName())
                .setLastName(addTeacherDTO.getLastName())
                .setEmail(addTeacherDTO.getEmail())
                .setPassword(passwordEncoder.encode(addTeacherDTO.getPassword()))
                .setRoles(List.of(teacherRole));

        userRepository.save(userEntity);

        TeacherEntity teacher = new TeacherEntity()
                .setFirstName(addTeacherDTO.getFirstName())
                .setLastName(addTeacherDTO.getFirstName())
                .setFaculty(faculty)
                .setRoom(addTeacherDTO.getRoom());

        teacherRepository.save(teacher);
    }

    @Override
    public List<TeacherEntity> viewTeachers(String firstName,String lastName) {
        return teacherRepository.findAllByFirstNameAndAndLastName(firstName, lastName);
    }
}
