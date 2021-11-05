package com.revature.services;

import com.revature.models.Employee;
import com.revature.repositories.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepo er;

    public EmployeeServiceImpl(EmployeeRepo er) {
        this.er = er;
    }

    // Basically going to pass the torch down to the repo layer for
    // these basic operations

    @Override
    public Employee addEmployee(Employee e) {
        return er.addEmployee(e);
    }

    @Override
    public Employee getEmployee(int id) {
        return er.getEmployee(id);
    }

//    @Override
//    public Employee getEmployee(String name, String password) {
//        return er.getEmployee(name, password);
//    }

    @Override
    public List<Employee> getAllEmployees() {
        return er.getAllEmployees();
    }

    @Override
    public Employee updateEmployee(Employee change) {
        return er.updateEmployee(change);
    }

    @Override
    public Employee deleteEmployee(int id) {
        return er.deleteEmployee(id);
    }

    // Service layer methods

    // To obtain a user by their username and password
    @Override
    public Employee getEmployeeByLogin(String username, String password) {
        List<Employee> employees = er.getAllEmployees();
        Employee e = null;

        // Iterate through all employee objects till we find the one with matching credentials
        for(Employee employee : employees) {
            if(employee.getUsername().equals(username) && employee.getLoginPassword().equals(password)) {
//                System.out.println(employee.getId());
                e = employee;
                break;
            }
        }
        return e;
    }

    @Override
    public List<Employee> getSupervisorsSubordinates(int supervisorID){
        List<Employee> employees = er.getAllEmployees();
        System.out.println(employees);
        List<Employee> subordinates = new ArrayList<Employee>();

        //Iterate Through all the employees, adding all the employees that have a supervisor id == to supervisorID
        for(Employee employee : employees) {
            try{
                int id = employee.getSupervisorID().getId(); // grab this employees supervisors id
                System.out.println(id);
                if (id == supervisorID){ // if this employees supervisor matches the supervisorID then add to subordinates
//                    System.out.println(id);
                    subordinates.add(employee);
                }
            }catch(NullPointerException e) {
                e.printStackTrace();
            }
        }
        return subordinates;
    }

}