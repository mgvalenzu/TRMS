package com.revature.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;

public class EmployeeController {

    EmployeeService es;
    Gson gson = new Gson();

    //Constructor
    public EmployeeController(EmployeeService es) {
        this.es = es;
    }

    // Controller responsible for managing the ApP logic.
    // I.e. What isit that our application needs to be able to to do
    // in this case manage route/endpoints

    public Handler getEmployeeById = (context) -> {

        int id = Integer.parseInt(context.pathParam("id"));
        Employee d = es.getEmployee(id);
        populateResult(context, d);
    };

    public Handler getAllEmployees = (context) -> {
        List<Employee> EmployeeList = es.getAllEmployees();

        if(EmployeeList != null) {
            context.result(gson.toJson(EmployeeList));
        } else {
            context.result("{}");
        }
    };

    public Handler addEmployee = (context) -> {
        Employee d = null;
        try {
            d = gson.fromJson(context.body(), Employee.class); // something on here
            d = es.addEmployee(d);
            populateResult(context, d);
        } catch (IllegalStateException | JsonSyntaxException e) {
            e.printStackTrace();
            System.out.println(d);
        }

    };

    public Handler updateEmployee = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        Employee d = gson.fromJson(context.body(), Employee.class);
        d.setId(id);
        d = es.updateEmployee(d);
        populateResult(context, d);

    };

    public Handler deleteEmployee = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        Employee d = es.deleteEmployee(id);
        populateResult(context, d);
    };

    // Others
    public Handler employeeLogin = (context) -> { // grabs the employee object for the given login credentials

        String username = context.pathParam("username");
        String password = context.pathParam("password");

        Employee e = es.getEmployeeByLogin(username, password); // this service method isnt done yet

        //Validate
        if(e != null) {
            populateResult(context, e);
        } else {
            context.result("Not Found");
        }
    };

    public Handler getSupervisorsSubordinates = (context) -> {
      int supervisorID = Integer.parseInt(context.pathParam(":supervisorID"));

        List<Employee> subordinates = es.getSupervisorsSubordinates(supervisorID);
          //validate
            if(subordinates != null) {
                context.result(gson.toJson(subordinates));
            } else {
                context.result("{}");
            }

    };

//    public Handler getDepartmentHead = (context) -> {
//        int id = Integer.parseInt(context.pathParam(":id"));
//
//    }

    //Helper function
    private void populateResult (Context context, Employee d) {
        if (d != null) {
            context.result(gson.toJson(d));
        } else {
            context.result("{}");
        }
    }
}