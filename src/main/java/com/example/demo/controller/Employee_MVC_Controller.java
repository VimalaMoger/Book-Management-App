package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class Employee_MVC_Controller {
	private EmployeeService service;
	public Employee_MVC_Controller(EmployeeService service) {
		this.service = service;
	}

	@GetMapping("/employees")
	public String listEmployees(Model model) {
		List<Employee> emp =service.getAllEmployees();
		model.addAttribute("employees", emp);
		return "employee";
	}
	
	@GetMapping("/employee/new")
	public String AddEmployee(Model model) {
		//create object to hold data
		Employee emp= new Employee();
		model.addAttribute("employee", emp);
		return "Add_employee";
	}

	@PostMapping("/employee")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee emp, BindingResult res) {
		if(res.hasErrors())
			return "Add_employee";
		else {
			service.saveEmployeeInfo(emp);
			return "redirect:/employees";
		}
	}

	@GetMapping("/employee/edit/{id}")
	public String UpdateEmployee(@PathVariable("id") Long id, Model model) {
		model.addAttribute("employee",service.getEmployeeById(id).get());
		return "update";
	}

	@PostMapping("/employee/{id}")
	public String updatingEmployee(@PathVariable("id") Long id,  @ModelAttribute("employee") Employee emp,Model model) {
		service.updateEmployee(emp, id);
		return  "redirect:/employees";
	}

	@GetMapping("/employee/{id}")
	public String DeleteEmployee(@PathVariable("id") Long id) {
		service.deleteEmployee(id);
		return  "redirect:/employees";
    }
}
