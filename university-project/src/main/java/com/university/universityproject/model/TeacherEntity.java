package com.university.universityproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "teachers")
@Getter
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String room;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private FacultyEntity faculty;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public TeacherEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public TeacherEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TeacherEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TeacherEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public TeacherEntity setRoom(String room) {
        this.room = room;
        return this;
    }

    public TeacherEntity setFaculty(FacultyEntity faculty) {
        this.faculty = faculty;
        return this;
    }

    public TeacherEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
