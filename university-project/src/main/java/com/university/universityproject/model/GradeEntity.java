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
@Table(name = "grades")
@Getter
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer grade;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;


    public GradeEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public GradeEntity setGrade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public GradeEntity setStudent(StudentEntity student) {
        this.student = student;
        return this;
    }

    public GradeEntity setSubject(SubjectEntity subject) {
        this.subject = subject;
        return this;
    }
}
