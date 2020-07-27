package com.hw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hw.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
