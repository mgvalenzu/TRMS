package com.revature.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.services.EmployeeService;
import com.revature.services.ReimbursementService;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ReimbursementController {

    ReimbursementService rs;
    Gson gson = new Gson();
    EmployeeService es;

    //Constructor
    public ReimbursementController(ReimbursementService rs) {
        this.rs = rs;
    }

    // Controller responsible for managing the ApP logic.
    // I.e. What isit that our application needs to be able to to do
    // in this case manage route/endpoints

    public Handler getReimbursementById = (context) -> {

        int id = Integer.parseInt(context.pathParam("id"));
        Reimbursement d = rs.getReimbursement(id);
        populateResult(context, d);
    };

    public Handler getAllReimbursements = (context) -> {
        List<Reimbursement> ReimbursementList = rs.getAllReimbursements();

        if(ReimbursementList != null) {
            context.result(gson.toJson(ReimbursementList));
        } else {
            context.result("{}");
        }
    };

    public Handler addReimbursement = (context) -> {
        Reimbursement d = null;

        try{
            d = gson.fromJson(context.body(), Reimbursement.class);
            d = rs.addReimbursement(d);
            System.out.println(d);
            populateResult(context, d);
        } catch (IllegalStateException | JsonSyntaxException e) {
            e.printStackTrace();
        }


    };

    public Handler updateReimbursement = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        Reimbursement d = gson.fromJson(context.body(), Reimbursement.class);
        d.setId(id);
        d = rs.updateReimbursement(d);
        populateResult(context, d);

    };

    public Handler deleteReimbursement = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        Reimbursement d = rs.deleteReimbursement(id);
        populateResult(context, d);
    };

    //Additional Handlers
    public Handler getReimbursementByEmployeeId = (context) -> {
        int id = Integer.parseInt(context.pathParam("id"));
        List<Reimbursement> reimbursements = null;

        try{
            reimbursements = rs.getAllReimbursementsByEmployeeId(id);
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }

       reimbursements = rs.getAllReimbursementsByEmployeeId(id);


      if(reimbursements != null) {
          context.result(gson.toJson(reimbursements));
      } else {
          context.result("{}");
      }
    };


    //Helper function
    private void populateResult (Context context, Reimbursement d) {
        if (d != null) {
            context.result(gson.toJson(d));
        } else {
            context.result("{}");
        }
    }
}