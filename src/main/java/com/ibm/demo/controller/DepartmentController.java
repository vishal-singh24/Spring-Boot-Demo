package com.ibm.demo.controller;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.model.Department;
import com.ibm.demo.services.DepartmentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final Logger LOG =
            org.slf4j.LoggerFactory.getLogger(this.getClass());

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(
            @PathVariable String name) {

        return ResponseEntity.ok(
                departmentService.getDepartmentByName(name));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Department>> getDepartmentsByLocation(
            @RequestParam String location) {

        return ResponseEntity.ok(
                departmentService.getDepartmentsByLocation(location));
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(
            @Valid @RequestBody Department department) {

        Department savedDepartment =
                departmentService.createDepartment(department);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable String id,
            @Valid @RequestBody Department department) {

        LOG.info("Department Id: {}", id);

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable String id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }
}