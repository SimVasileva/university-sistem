package com.university.universityproject.service.impl;

import com.university.universityproject.model.FacultyEntity;
import com.university.universityproject.model.SpecialityEntity;
import com.university.universityproject.model.StudentEntity;
import com.university.universityproject.model.SubjectEntity;
import com.university.universityproject.model.UserEntity;
import com.university.universityproject.model.UserRoleEntity;
import com.university.universityproject.model.dto.AddStudentDTO;
import com.university.universityproject.repository.FacultyRepository;
import com.university.universityproject.repository.SpecialityRepository;
import com.university.universityproject.repository.StudentRepository;
import com.university.universityproject.repository.UserRepository;
import com.university.universityproject.repository.UserRoleRepository;
import com.university.universityproject.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.university.universityproject.model.UserRoleEnum.STUDENT;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserRoleRepository userRoleRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SpecialityRepository specialityRepository;
    private final FacultyRepository facultyRepository;

    public StudentServiceImpl(UserRoleRepository userRoleRepository, StudentRepository studentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, SpecialityRepository specialityRepository, FacultyRepository facultyRepository) {
        this.userRoleRepository = userRoleRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.specialityRepository = specialityRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public void createStudent(AddStudentDTO addStudentDTO) {
        SpecialityEntity speciality;
        FacultyEntity faculty;
        Optional<SpecialityEntity> specialityOpt = specialityRepository.findByName(addStudentDTO.getSpeciality());
        if (specialityOpt.isEmpty()) {
            speciality = new SpecialityEntity().setName(addStudentDTO.getSpeciality());
            specialityRepository.save(speciality);
        } else {
            speciality = specialityOpt.get();
        }

        Optional<FacultyEntity> facultyOpt = facultyRepository.findByName(addStudentDTO.getFaculty());
        if (facultyOpt.isEmpty()) {
            faculty = new FacultyEntity().setName(addStudentDTO.getFaculty());
            facultyRepository.save(faculty);
        } else {
            faculty = facultyOpt.get();
        }
        UserRoleEntity
                studentRole = userRoleRepository.findByRole(STUDENT).orElseThrow(NullPointerException::new);

        UserEntity userEntity = new UserEntity();
        userEntity
                .setFirstName(addStudentDTO.getFirstName())
                .setLastName(addStudentDTO.getLastName())
                .setEmail(addStudentDTO.getEmail())
                .setPassword(passwordEncoder.encode(addStudentDTO.getPassword()))
                .setRoles(List.of(studentRole));

        userRepository.save(userEntity);

        StudentEntity student = new StudentEntity()
                .setFirstName(addStudentDTO.getFirstName())
                .setLastName(addStudentDTO.getLastName())
                .setFacultyNumber(addStudentDTO.getFacultyNumber())
                .setSpeciality(speciality)
                .setFaculty(faculty)
                .setUser(userEntity);

        studentRepository.save(student);

    }

    @Override
    public List<StudentEntity> viewAllStudents(String name) {
        return studentRepository.findByFirstName(name);
    }
}
