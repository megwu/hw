package com.hw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hw.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
