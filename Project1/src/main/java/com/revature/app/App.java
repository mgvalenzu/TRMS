package com.revature.app;

import com.revature.controllers.*;
import com.revature.logging.MyLogger;
import com.revature.repositories.*;
import com.revature.services.*;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

public class App {

    public static void main(String[] args) {
        MyLogger.logger.info("Program Started");

        //Javalin Object
        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins); // establish Javalin object

        //Establish the routes/endpoints that Javalin will manage
        establishRoutes(app);

        //run javalin
        app.start();
    }

    private static void establishRoutes(Javalin app) {
        //Tell javalin which routes/endpoints it will manage
//        app.get("/login", (context) -> context.result())
        addDepartmentRoutes(app);
        addEmployeeRoutes(app);
        addEventRoutes(app);
        addGradeRoutes(app);
        addReimbursementRoutes(app);
    }

    private static void addDepartmentRoutes(Javalin app) {

        DepartmentRepo dr = new DepartmentRepoHBImpl();
        DepartmentService ds = new DepartmentServiceImpl(dr);
        DepartmentController dc = new DepartmentController(ds);

        app.get("/departments/:id", dc.getDepartmentById);
        app.get("/departments", dc.getAllDepartments);
        app.post("/departments", dc.addDepartment);
        app.put("/departments/:id", dc.updateDepartment);
        app.delete("/departments/:id", dc.deleteDepartment);

    }

    private static void addEmployeeRoutes(Javalin app) {

        EmployeeRepo er = new EmployeeRepoHBImpl();
        EmployeeService es = new EmployeeServiceImpl(er);
        EmployeeController ec = new EmployeeController(es);

        app.get("/employees/:id", ec.getEmployeeById);
        app.get("/employees", ec.getAllEmployees);
        app.post("/employees", ec.addEmployee);
        app.put("/employees/:id", ec.updateEmployee);
        app.delete("/employees/:id", ec.deleteEmployee);

        app.get("/employees/:username/employees/:password", ec.employeeLogin);
        app.get("/employees/employees/:supervisorID", ec.getSupervisorsSubordinates);
    }

    private static void addEventRoutes(Javalin app) {

        EventRepo er = new EventRepoHBImpl();
        EventService es = new EventServiceImpl(er);
        EventController ec = new EventController(es);

        app.get("/events/:id", ec.getEventById);
        app.get("/events", ec.getAllEvents);
        app.post("/events", ec.addEvent);
        app.put("/events/:id", ec.updateEvent);
        app.delete("/events/:id", ec.deleteEvent);
    }

    private static void addGradeRoutes(Javalin app) {

        GradeRepo gr = new GradeRepoHBImpl();
        GradeService gs = new GradeServiceImpl(gr);
        GradeController gc = new GradeController(gs);

        app.get("/grades/:id", gc.getGradeById);
        app.get("/grades", gc.getAllGrades);
        app.post("/grades", gc.addGrade);
        app.put("/grades/:id", gc.updateGrade);
        app.delete("/grades/:id", gc.deleteGrade);
    }

    private static void addReimbursementRoutes(Javalin app) {

        ReimbursementRepo rr = new ReimbursementRepoHBImpl();
        ReimbursementService rs = new ReimbursementServiceImpl(rr);
        ReimbursementController rc = new ReimbursementController(rs);

        app.get("/reimbursements/:id", rc.getReimbursementById);
        app.get("/reimbursements", rc.getAllReimbursements);
        app.post("/reimbursements", rc.addReimbursement);
        app.put("/reimbursements/:id", rc.updateReimbursement);
        app.delete("/reimbursements/:id", rc.deleteReimbursement);

        app.get("/reimbursements/employees/:id", rc.getReimbursementByEmployeeId);
//        app.get("/reimbursements/employees/employees/:supervisorID", rc.getALlReimbursementsWhereSupervisor);
    }


}
