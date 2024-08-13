package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;
//internally provides @repository, simplerepo implements uses repository and transactional

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
