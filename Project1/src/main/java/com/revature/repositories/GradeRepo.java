package com.revature.repositories;

import com.revature.models.Grade;

import java.util.List;

public interface GradeRepo {

    public Grade addGrade(Grade g);
    public List<Grade> getAllGrades();
    public Grade getGrade(int id);
    public Grade updateGrade(Grade change);
    public Grade deleteGrade(int id);

}
