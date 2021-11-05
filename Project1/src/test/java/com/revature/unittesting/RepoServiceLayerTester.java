package com.revature.unittesting;

import com.revature.models.*;
import com.revature.repositories.*;
import com.revature.services.*;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

public class RepoServiceLayerTester {

    DepartmentRepo dr = new DepartmentRepoHBImpl();
    EmployeeRepo er = new EmployeeRepoHBImpl();
    EventRepo evr = new EventRepoHBImpl();
    GradeRepo gr = new GradeRepoHBImpl();
    ReimbursementRepo rr = new ReimbursementRepoHBImpl();


    //Department repo tests
    @Test
    public void getDepartmentByStringSuccessTest(){
        Department actual = dr.getDepartment("QA Department");
        Department expected = dr.getDepartment(1);
        boolean test;
        if(expected.getId() == actual.getId()){
            test = true;
        }else {
            test = false;
        }
        Assertions.assertTrue(test);
    }



    //////////////////////////////////////////////////////////////////
    //Service Layers?
    ///////////////////////////////////////////////////////////////


    //Department Service Test /////////////
    DepartmentService ds = new DepartmentServiceImpl(dr);

    @Test
    public void getAllDepartmentServiceTest() {
        List<Department> allDepartments =ds.getAllDepartments();
        Assertions.assertNotNull(allDepartments);
        Assertions.assertNotEquals(0, allDepartments.size());
    }

    @Test
    public void getDepartmentByIDServiceTest() {
        Department testDepartment = ds.getDepartment(1);
        Assertions.assertEquals(1, testDepartment.getId());
    }

//    @Test
//    public void addDepartmentServiceTest() {
//        Department actual = new Department();
//        actual.setId(100);
//
//
//        Assertions.assertEquals(100, actual.getId());
//    }
//
    @Test
    public void deleteDepartmentSuccessServiceTest(){ // this will fail the second time you do it cause it actual deletes the thing
        Department expected = ds.getDepartment(2);
        Department actual = ds.deleteDepartment(2);
        Assertions.assertEquals(expected.getId(), actual.getId());
    }


    //Employee Service tests ///////////////
    EmployeeService es = new EmployeeServiceImpl(er);
    @Test
    public void getAllEmployeesServiceTest() {
        List<Employee> allEmployees =es.getAllEmployees();
        Assertions.assertNotNull(allEmployees);
        Assertions.assertNotEquals(0, allEmployees.size());
    }

    @Test
    public void getEmployeeByIDServiceTest() {
        Employee testEmployee = es.getEmployee(1);
        Assertions.assertEquals(1, testEmployee.getId());
    }

    @Test
    public void getEmployeeByLoginServiceTest(){
        String username = "Dkong";
        String password = "password";
        Employee actual = es.getEmployeeByLogin(username, password);
        Employee expected = es.getEmployee(1);

        Assertions.assertEquals(expected.getId(), actual.getId());
    }

//    @Test
//    public void getSupervisorsSubordinatesServiceTest(){
//        List<Employee> actual = es.getSupervisorsSubordinates(2);
//
//        int supervisorID = 2;
//        Employee supervisor = es.getEmployee(2);
//        List<Employee> expected = new ArrayList<Employee>();
//        List<Employee> allEmployees = es.getAllEmployees();
//
//        for(Employee e : allEmployees){
//            if (e.getSupervisorID().getId() == supervisorID){
//                expected.add(e);
//            }
//        }
//        boolean a = true;
//        for(int i = 0;i <= expected.size(); i++){
//            if (expected.get(i) == actual.get(i)){
//                continue;
//            }else {
//                a = false;
//                break;
//            }
//        }
//    }

    @Test
    public void deleteEmployeeSuccessServiceTest(){ // this will fail the second time you do it cause it actual deletes the thing
        Employee expected = es.getEmployee(3);
        Employee actual = es.deleteEmployee(3);
        Assertions.assertEquals(expected.getId(), actual.getId());
    }


    //Event Service Tests //////////////
    EventService evs = new EventServiceImpl(evr);
    @Test
    public void getAllEventsServiceTest() {
        List<Event> allEvents =evs.getAllEvents();
        Assertions.assertNotNull(allEvents);
        Assertions.assertNotEquals(0, allEvents.size());
    }

    @Test
    public void getEventByIDServiceTest() {
        Event testEvent = evs.getEvent(1);
        Assertions.assertEquals(1, testEvent.getId());
    }

        @Test
    public void deleteEventSuccessServiceTest(){ // this will fail the second time you do it cause it actual deletes the thing
        Event expected = evs.getEvent(3);
        Event actual = evs.deleteEvent(3);
        Assertions.assertEquals(expected.getId(), actual.getId());
    }


    //Grade Service Tests /////////////
    GradeService gs = new GradeServiceImpl(gr);
    @Test
    public void getAllGradesServiceTest() {
        List<Grade> allGrades = gs.getAllGrades();
        Assertions.assertNotNull(allGrades);
        Assertions.assertNotEquals(0, allGrades.size());
    }

    @Test
    public void getGradeByIDServiceTest() {
        Grade testGrade = gs.getGrade(1);
        Assertions.assertEquals(1, testGrade.getId());
    }

    @Test
    public void deleteGradeSuccessServiceTest(){ // this will fail the second time you do it cause it actual deletes the thing
        Grade expected = gs.getGrade(3);
        Grade actual = gs.deleteGrade(3);
        Assertions.assertEquals(expected.getId(), actual.getId());
    }


    //Reimbursement Service Tests //////
    ReimbursementService rs = new ReimbursementServiceImpl(rr);
    @Test
    public void getAllReimbursementsServiceTest() {
        List<Reimbursement> allReimbursements = rs.getAllReimbursements();
        Assertions.assertNotNull(allReimbursements);
        Assertions.assertNotEquals(0, allReimbursements.size());
    }

    @Test
    public void getReimbursementByIDServiceTest() {
        Reimbursement testReimbursement = rs.getReimbursement(1);
        Assertions.assertEquals(1, testReimbursement.getId());
    }

    @Test
    public void deleteReimbursementSuccessServiceTest(){ // this will fail the second time you do it cause it actual deletes the thing
        Reimbursement expected = rs.getReimbursement(3);
        Reimbursement actual = rs.deleteReimbursement(3);
        Assertions.assertEquals(expected.getId(), actual.getId());
    }

}
