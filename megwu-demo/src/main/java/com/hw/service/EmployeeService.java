package com.hw.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hw.entity.Employee;
import com.hw.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Resource
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	public Employee findOne(Integer id){
		
		if (id == null || id.intValue() == 0) {
			System.out.println("id is null");
			return null;
		}
		
		return employeeRepository.findById(id).get();
	}
	
	public Employee save(Employee employee) {
		
		// check id
		if ( employee.getId() != null ) {
			Employee tmpEmployee = employeeRepository.findById(employee.getId()).get();
			if (tmpEmployee != null) {
				System.err.println("id is exist");
				return null;
			}
		}
		
		Date nowTime = new Date();
		employee.setTxtm(nowTime);
		employee.setGftm(nowTime);
		
		return employeeRepository.save(employee);
	}
	
	public Employee update(Employee employee) {
		Employee tmpEmployee = employeeRepository.findById(employee.getId()).get();
		if (tmpEmployee == null) {
			System.err.println("employee is not exist");
			return null;
		}
		
		Date nowTime = new Date();
		tmpEmployee.setTxtm(nowTime);
		
		if (employee.getName() != null && employee.getName().length() > 0) {
			tmpEmployee.setName(employee.getName());
		}
		if (employee.getDeptId() != null && employee.getDeptId().length() > 0) {
			tmpEmployee.setDeptId(employee.getDeptId());
		}
		if (employee.getSex() != null && employee.getSex().length() > 0) {
			tmpEmployee.setSex(employee.getSex());
		}
		if (employee.getTel() != null && employee.getTel().length() > 0) {
			tmpEmployee.setTel(employee.getTel());
		}
		if (employee.getAddr() != null && employee.getAddr().length() > 0) {
			tmpEmployee.setAddr(employee.getAddr());
		}
		if (employee.getAge() != null && employee.getAge().length() > 0) {
			tmpEmployee.setAge(employee.getAge());
		}
		
		return employeeRepository.save(tmpEmployee);
	}
	
	public Integer delete(Integer id) {
		Employee tmpEmployee = employeeRepository.findById(id).get();
		
		if (tmpEmployee == null) {
			System.err.println("employee is not exist");
			return -1;
		} else {
			employeeRepository.deleteById(id);
			return 0;
		}
	}
	
}
