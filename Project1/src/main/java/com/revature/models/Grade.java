package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name="grades")
public class Grade {

    @Id
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // postgres serial
    private int id;

    @Column(name="grade_format")
    private String gradeFormat;

    @Column(name="grade")
    private String grade;

    public Grade() {
    }

    public Grade(int id, String gradeFormat, String grade) {
        this.id = id;
        this.gradeFormat = gradeFormat;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradeFormat() {
        return gradeFormat;
    }

    public void setGradeFormat(String gradeFormat) {
        this.gradeFormat = gradeFormat;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", gradeFormat='" + gradeFormat + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
