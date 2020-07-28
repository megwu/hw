package com.hw.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hw.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query("SELECT new com.hw.entity.Employee(e.id, e.name, e.deptId, d.name as deptName, e.sex, e.tel"
			+ ", e.addr, e.age, e.gftm, e.txtm) "
			+ "FROM t_employee e, t_department d where d.id = e.deptId")
	List<Employee> fetchEmpDeptDataInnerJoin(Specification<Employee> specification);
	
	
	
}
