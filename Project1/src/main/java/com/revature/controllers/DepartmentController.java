package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Department;
import com.revature.services.DepartmentService;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;

public class DepartmentController {

    DepartmentService ds;
    Gson gson = new Gson();

    //Constructor
    public DepartmentController(DepartmentService ds) {
        this.ds = ds;
    }

    // Controller responsible for managing the ApP logic.
    // I.e. What isit that our application needs to be able to to do
    // in this case manage route/endpoints

    public Handler getDepartmentById = (context) -> {

        int id = Integer.parseInt(context.pathParam("id"));
        Department d = ds.getDepartment(id);
        populateResult(context, d);
    };

    public Handler getAllDepartments = (context) -> {
        List<Department> departmentList = ds.getAllDepartments();

        if(departmentList != null) {
            context.result(gson.toJson(departmentList));
        } else {
            context.result("{}");
        }
    };

    public Handler addDepartment = (context) -> {
      Department d = gson.fromJson(context.body(), Department.class);

      d = ds.addDepartment(d);
      populateResult(context, d);
    };

    public Handler updateDepartment = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        Department d = gson.fromJson(context.body(), Department.class);
        d.setId(id);
        d = ds.updateDepartment(d);
        populateResult(context, d);

    };

    public Handler deleteDepartment = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        Department d = ds.deleteDepartment(id);
        populateResult(context, d);
    };

    // Additional handlers

    //Helper function
    private void populateResult (Context context, Department d) {
        if (d != null) {
            context.result(gson.toJson(d));
        } else {
            context.result("{}");
        }
    }
}
