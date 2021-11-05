package com.revature.repositories;

import com.revature.models.Department;

import java.util.List;

public interface DepartmentRepo {

    public Department addDepartment(Department d);
    public List<Department> getAllDepartments();
    public Department getDepartment(int id);
    public Department getDepartment(String name); // get department by name
    public Department updateDepartment(Department change);
    public Department deleteDepartment(int id);

}
