/* This controller created for database interaction via Postman */
/*package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {

	public EmployeeController(EmployeeService service) {
		//super();
		this.service = service;
	}
	
	private EmployeeService service;

	@PostMapping("/employees")
	public ResponseEntity<List<Employee>> addEmployee(@RequestBody Employee emp) {
		return new ResponseEntity<List<Employee>>(service.saveEmployeeInfo(emp), HttpStatus.CREATED);
	}

	//build get all employee
	@GetMapping("/employees")
 	public ResponseEntity<List<Employee>> getAllEmployees(EmployeeNotFoundException ex) {
        if (service.getAllEmployees().isEmpty())
            throw new EmployeeNotFoundException("not found");
        return new ResponseEntity<>(service.getAllEmployees(), HttpStatus.OK);
    }

	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name="id") Long id){
		if (service.getEmployeeById(id).isEmpty())
			throw new EmployeeNotFoundException("not found");
		return new ResponseEntity<Employee>(service.getEmployeeById(id).get(),HttpStatus.OK);
		}

	//update employee
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp, @PathVariable(name="id") Long id){
		return new ResponseEntity<Employee>(service.updateEmployee(emp, id),HttpStatus.OK);
	}
	//delete employee
	@DeleteMapping("employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(name="id") Long id) {
		service.deleteEmployee(id);
		return new ResponseEntity<String>("employee deleted successfully",HttpStatus.OK);
	}
    //exception handling method
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> employeeNotFound(EmployeeNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
*/
