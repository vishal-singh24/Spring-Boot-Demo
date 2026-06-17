package com.ibm.demo.dto.employee;

import java.time.LocalDateTime;

public class EmployeeResponse {
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Double salary;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public EmployeeResponse() {
		
	}
	
	public EmployeeResponse(String id, String firstName, String lastName, String email, Double salary,LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.salary=salary;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
	}
	
	

}
