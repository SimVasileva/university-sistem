package com.university.universityproject.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "subjects")
@Getter
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private boolean passed;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;


    public SubjectEntity setStudent(StudentEntity student) {
        this.student = student;
        return this;
    }

    public SubjectEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public SubjectEntity setName(String name) {
        this.name = name;
        return this;
    }

    public SubjectEntity setPassed(boolean passed) {
        this.passed = passed;
        return this;
    }
}
