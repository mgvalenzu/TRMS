package com.revature.services;

import com.revature.models.Department;

import java.util.List;

public interface DepartmentService {

    // Repository Methods
    public Department addDepartment(Department d);
    public Department getDepartment(int id);
    public Department getDepartment(String name); // get department by name
    public List<Department> getAllDepartments();
    public Department updateDepartment(Department change);
    public Department deleteDepartment(int id);

    // Other business logic methods here
}
