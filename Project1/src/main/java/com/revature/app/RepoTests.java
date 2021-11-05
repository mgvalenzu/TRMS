package com.revature.app;

import com.revature.models.Department;
import com.revature.repositories.DepartmentRepo;
import com.revature.repositories.DepartmentRepoHBImpl;

import java.util.List;

public class RepoTests {
    public static void main(String[] args) {
        DepartmentRepo dr = new DepartmentRepoHBImpl();

        List<Department> allDepartments = dr.getAllDepartments();

        Department deptByID = dr.getDepartment(1);

    }
}

