package com.revature.services;

import com.revature.models.Grade;

import java.util.List;

public interface GradeService {

    // Repository Methods
    public Grade addGrade(Grade g);
    public Grade getGrade(int id);
//    public Grade getGrade(String name); // get Grade by name
    public List<Grade> getAllGrades();
    public Grade updateGrade(Grade change);
    public Grade deleteGrade(int id);

    // Other business logic methods here

}
