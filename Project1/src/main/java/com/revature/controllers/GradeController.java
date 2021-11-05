package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Grade;
import com.revature.services.GradeService;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;

public class GradeController {

    GradeService gs;
    Gson gson = new Gson();

    //Constructor
    public GradeController(GradeService gs) {
        this.gs = gs;
    }

    // Controller responsible for managing the ApP logic.
    // I.e. What isit that our application needs to be able to to do
    // in this case manage route/endpoints

    public Handler getGradeById = (context) -> {

        int id = Integer.parseInt(context.pathParam("id"));
        Grade d = gs.getGrade(id);
        populateResult(context, d);
    };

    public Handler getAllGrades = (context) -> {
        List<Grade> GradeList = gs.getAllGrades();

        if(GradeList != null) {
            context.result(gson.toJson(GradeList));
        } else {
            context.result("{}");
        }
    };

    public Handler addGrade = (context) -> {
        Grade d = gson.fromJson(context.body(), Grade.class);

        d = gs.addGrade(d);
        populateResult(context, d);
    };

    public Handler updateGrade = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        Grade d = gson.fromJson(context.body(), Grade.class);
        d.setId(id);
        d = gs.updateGrade(d);
        populateResult(context, d);

    };

    public Handler deleteGrade = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        Grade d = gs.deleteGrade(id);
        populateResult(context, d);
    };

    //Helper function
    private void populateResult (Context context, Grade d) {
        if (d != null) {
            context.result(gson.toJson(d));
        } else {
            context.result("{}");
        }
    }
}