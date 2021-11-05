package com.revature.services;

import com.revature.models.Grade;
import com.revature.repositories.GradeRepo;

import java.util.List;

public class GradeServiceImpl implements GradeService{

    GradeRepo gr;

    public GradeServiceImpl(GradeRepo gr) {
        this.gr = gr;
    }

    // Basically going to pass the torch down to the repo layer for
    // these basic operations

    @Override
    public Grade addGrade(Grade g) {
        return gr.addGrade(g);
    }

    @Override
    public Grade getGrade(int id) {
        return gr.getGrade(id);
    }
//
//    @Override
//    public Grade getGrade(String name) {
//        return gr.getGrade(name);
//    }

    @Override
    public List<Grade> getAllGrades() {
        return gr.getAllGrades();
    }

    @Override
    public Grade updateGrade(Grade change) {
        return gr.updateGrade(change);
    }

    @Override
    public Grade deleteGrade(int id) {
        return gr.deleteGrade(id);
    }

    // Service layer methods



}