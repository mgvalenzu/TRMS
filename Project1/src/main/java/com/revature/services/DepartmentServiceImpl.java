package com.revature.services;

import com.revature.models.Department;
import com.revature.repositories.DepartmentRepo;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService{

    DepartmentRepo dr;

    public DepartmentServiceImpl(DepartmentRepo dr) {
        this.dr = dr;
    }

    // Basically going to pass the torch down to the repo layer for
    // these basic operations

    @Override
    public Department addDepartment(Department d) {
        return dr.addDepartment(d);
    }

    @Override
    public Department getDepartment(int id) {
        return dr.getDepartment(id);
    }

    @Override
    public Department getDepartment(String name) {
        return dr.getDepartment(name);
    }

    @Override
    public List<Department> getAllDepartments() {
        return dr.getAllDepartments();
    }

    @Override
    public Department updateDepartment(Department change) {
        return dr.updateDepartment(change);
    }

    @Override
    public Department deleteDepartment(int id) {
        return dr.deleteDepartment(id);
    }

    // Service layer methods



}
