package com.revature.models;

import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="reimbursements")
public class Reimbursement {

    @Id
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // postgres serial
    private int id;

    @Column(name="justification")
    private String justification;

//    @Column(name="submission_date" , columnDefinition="date default current_date")
    @Column(name="submission_date")
    private LocalDate submissionDate;

    @Column(name="approval_status", columnDefinition="varchar(100) default 'Submitted'")
    private String approvalStatus;

    @Column(name="final_grade", columnDefinition = "varchar(25) default 'In-Progress'")
    private String finalGrade;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employeeID;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event eventID;

    public Reimbursement() {
    }

    public Reimbursement(int id, String justification, LocalDate submissionDate, String approvalStatus, String finalGrade, Employee employeeID, Event eventID) {
        this.id = id;
        this.justification = justification;
        this.submissionDate = submissionDate;
        this.approvalStatus = approvalStatus;
        this.finalGrade = finalGrade;
        this.employeeID = employeeID;
        this.eventID = eventID;
    }

    public Reimbursement(String justification, LocalDate submissionDate, String approvalStatus, String finalGrade, Employee employeeID, Event eventID) {
        this.justification = justification;
        this.submissionDate = submissionDate;
        this.approvalStatus = approvalStatus;
        this.finalGrade = finalGrade;
        this.employeeID = employeeID;
        this.eventID = eventID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    public Event getEventID() {
        return eventID;
    }

    public void setEventID(Event eventID) {
        this.eventID = eventID;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", justification='" + justification + '\'' +
                ", submissionDate=" + submissionDate +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", finalGrade='" + finalGrade + '\'' +
                ", employeeID=" + employeeID +
                ", eventID=" + eventID +
                '}';
    }
}
