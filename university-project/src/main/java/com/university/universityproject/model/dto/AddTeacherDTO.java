package com.university.universityproject.model.dto;

import lombok.Getter;

@Getter
public class AddTeacherDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String faculty;
    private String room;

    public AddTeacherDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AddTeacherDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AddTeacherDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public AddTeacherDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public AddTeacherDTO setFaculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    public AddTeacherDTO setRoom(String room) {
        this.room = room;
        return this;
    }
}
