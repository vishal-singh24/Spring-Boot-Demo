package com.ibm.demo.services;

import java.util.List;

import com.ibm.demo.model.Department;

public interface DepartmentService {

    public abstract List<Department> getAllDepartments();

    public abstract Department getDepartmentById(String id);

    public abstract Department getDepartmentByName(String name);

    public abstract List<Department> getDepartmentsByLocation(String location);

    public abstract Department createDepartment(Department department);

    public abstract Department updateDepartment(String id, Department department);

    public abstract void deleteDepartment(String id);
}