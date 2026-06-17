package com.ibm.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ibm.demo.model.Employee;
import com.ibm.demo.services.EmployeeService;


import jakarta.validation.Valid;


@Validated
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	private final Logger LOG=org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {

		return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
	}

	@GetMapping("/search")
	public ResponseEntity<List<Employee>> getEmployeesByFirstName(@RequestParam String firstName) {

		return ResponseEntity.ok(employeeService.getEmployeesByFirstName(firstName));
	}

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {

		Employee savedEmployee = employeeService.createEmployee(employee);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @Valid @RequestBody Employee employee) {
		//System.out.println("Hello");    not a good practice to use syso
		LOG.info("Id"+id);                 // good practice to use Logger 
		
		return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {

		employeeService.deleteEmployee(id);

		return ResponseEntity.noContent().build();
	}
}
