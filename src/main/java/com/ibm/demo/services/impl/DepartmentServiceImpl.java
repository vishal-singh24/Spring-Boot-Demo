package com.ibm.demo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.demo.exception.DepartmentAlreadyExistsException;
import com.ibm.demo.exception.DepartmentNotFoundException;
import com.ibm.demo.model.Department;
import com.ibm.demo.repository.DepartmentRepository;
import com.ibm.demo.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepository) {

        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(String id) {

        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id));
    }

    @Override
    public Department getDepartmentByName(String name) {

        return departmentRepository.findByName(name)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with name: " + name));
    }

    @Override
    public List<Department> getDepartmentsByLocation(
            String location) {

        List<Department> departments =
                departmentRepository.findByLocationIgnoreCase(location);

        if (departments.isEmpty()) {
            throw new DepartmentNotFoundException(
                    "No departments found in location: " + location);
        }

        return departments;
    }

    @Override
    public Department createDepartment(
            Department department) {

        if (departmentRepository.existsByName(
                department.getName())) {

            throw new DepartmentAlreadyExistsException(
                    "Department with name "
                            + department.getName()
                            + " already exists");
        }

        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(
            String id,
            Department updatedDepartment) {

        Department existing = getDepartmentById(id);

        if (!existing.getName().equalsIgnoreCase(
                updatedDepartment.getName())
                && departmentRepository.existsByName(
                        updatedDepartment.getName())) {

            throw new DepartmentAlreadyExistsException(
                    "Department with name "
                            + updatedDepartment.getName()
                            + " already exists");
        }

        existing.setName(updatedDepartment.getName());
        existing.setLocation(updatedDepartment.getLocation());

        return departmentRepository.save(existing);
    }

    @Override
    public void deleteDepartment(String id) {

        if (!departmentRepository.existsById(id)) {

            throw new DepartmentNotFoundException(
                    "Department not found with id: " + id);
        }

        departmentRepository.deleteById(id);
    }
}