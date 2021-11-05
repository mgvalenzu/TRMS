package com.revature.models;


import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="events")
public class Event {

    @Id // Denotes this field as the Primary Key - regardless of what we called it in our database or model
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "event_type")
    private String eventType;

    @Column(name="event_name")
    private String eventName;

    @Column(name="event_cost", columnDefinition = "numeric(6,2)")
    private double eventCost;

    @Column(name="coverage", columnDefinition = "numeric(6,2)")
    private double coverage;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name="event_location")
    private String eventLocation;

    @Column(name="description")
    private String description;

    @Column(name="cutoff_grade")
    private String cutoffGrade;

//    @ManyToOne
    @Column(name="grade_type")
    private String gradeType;

    public Event() {
    }

    public Event(int id, String eventType, String eventName, double eventCost, double coverage, LocalDate eventDate, String eventLocation, String description, String cutoffGrade, String gradeType) {
        this.id = id;
        this.eventType = eventType;
        this.eventName = eventName;
        this.eventCost = eventCost;
        this.coverage = coverage;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.description = description;
        this.cutoffGrade = cutoffGrade;
        this.gradeType = gradeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public double getEventCost() {
        return eventCost;
    }

    public void setEventCost(double eventCost) {
        this.eventCost = eventCost;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCutoffGrade() {
        return cutoffGrade;
    }

    public void setCutoffGrade(String cutoffGrade) {
        this.cutoffGrade = cutoffGrade;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventCost=" + eventCost +
                ", coverage=" + coverage +
                ", eventDate=" + eventDate +
                ", eventLocation='" + eventLocation + '\'' +
                ", description='" + description + '\'' +
                ", cutoffGrade=" + cutoffGrade +
                ", gradeType=" + gradeType +
                '}';
    }
}
