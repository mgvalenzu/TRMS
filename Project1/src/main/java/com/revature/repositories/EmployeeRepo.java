package com.revature.repositories;

import com.revature.models.Employee;

import java.util.List;

public interface EmployeeRepo {

    public Employee addEmployee(Employee e);
    public List<Employee> getAllEmployees();
    public Employee getEmployee(int id);
    // We can maybe do a get Employee by username/password we shall se - to login
//    public Employee getEmployee(String username, String password);
    public Employee updateEmployee(Employee change);
    public Employee deleteEmployee(int id);

}
