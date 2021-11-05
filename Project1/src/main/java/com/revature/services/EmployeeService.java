package com.revature.services;

import com.revature.models.Employee;

import java.util.List;

public interface EmployeeService {

    // Repository Methods
    public Employee addEmployee(Employee e);
    public Employee getEmployee(int id);
//    public Employee getEmployee(String name, String password); // get Employee by name and password
    public List<Employee> getAllEmployees();
    public Employee updateEmployee(Employee change);
    public Employee deleteEmployee(int id);

    // Other business logic methods here
    public Employee getEmployeeByLogin(String username, String password);
    public List<Employee> getSupervisorsSubordinates(int supervisorID);
}
