package com.revature.models;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.sql.Time;
import java.util.Date;

/*
 * Entity is used to denote the Class as an entity that Hibernate will manage.
 * By extension will have a Database Representation(i.e. a Table)
 *
 * Table is used to provide additional information about the Table itself.
 * */

@Entity
@Table(name="employees")
public class Employee {

    @Id // Denotes this field as the Primary Key - regardless of what we called it in our database or model
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique Identification

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="login_password", nullable = false)
    private String loginPassword;

    @Column(name="email")
    private String email;

//    @Column(name="date_created", columnDefinition="date default current_date")
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name="is_benco", columnDefinition = "boolean")
    private boolean isBenco;

    @Column(name="is_department_head", columnDefinition = "boolean")
    private boolean isDepartmentHead;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="department_id")
    private Department departmentID;

    @ManyToOne
    @JoinColumn(name="supervisor_id")
    private Employee supervisorID;

    // No arg constructor
    public Employee() {
    }

    // Full arg constructor
    public Employee(int id, String username, String loginPassword, String email, LocalDate dateCreated, boolean isBenco, boolean isDepartmentHead ,Department departmentID, Employee supervisorID) {
        this.id = id;
        this.username = username;
        this.loginPassword = loginPassword;
        this.email = email;
        this.dateCreated = dateCreated;
        this.isBenco = isBenco;
        this.isDepartmentHead = isDepartmentHead;
        this.departmentID = departmentID;
        this.supervisorID = supervisorID;
    }

    // ID-less constructor


    public Employee(String username, String loginPassword, String email, LocalDate dateCreated, boolean isBenco, boolean isDepartmentHead ,Department departmentID, Employee supervisorID) {
        this.username = username;
        this.loginPassword = loginPassword;
        this.email = email;
        this.dateCreated = dateCreated;
        this.isBenco = isBenco;
        this.isDepartmentHead = isDepartmentHead;
        this.departmentID = departmentID;
        this.supervisorID = supervisorID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isBenco() {
        return isBenco;
    }

    public void setBenco(boolean benco) {
        isBenco = benco;
    }

    public boolean isDepartmentHead() {
        return isDepartmentHead;
    }

    public void setDepartmentHead(boolean departmentHead) {
        isDepartmentHead = departmentHead;
    }

    public Department getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Department departmentID) {
        this.departmentID = departmentID;
    }

    public Employee getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(Employee supervisorID) {
        this.supervisorID = supervisorID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", email='" + email + '\'' +
                ", dateCreated=" + dateCreated +
                ", isBenco=" + isBenco +
                ", isDepartmentHead=" + isDepartmentHead +
                ", departmentID=" + departmentID +
                ", supervisorID=" + supervisorID +
                '}';
    }
}
