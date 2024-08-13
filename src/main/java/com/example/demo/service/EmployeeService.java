package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Employee;
//to provide loose coupling, we use interface 
public interface EmployeeService {
	List<Employee> getAllEmployees();
	Optional<Employee> getEmployeeById(Long id);
	Employee updateEmployee(Employee emp,Long id);
	void deleteEmployee(Long id);
	List<Employee> saveEmployeeInfo(Employee emp);
}
