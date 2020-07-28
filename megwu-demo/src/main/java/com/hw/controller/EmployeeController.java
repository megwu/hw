package com.hw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hw.entity.Employee;
import com.hw.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Resource
	private EmployeeService employeeService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public List<Employee> getAll(){
		return employeeService.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Employee getOne(@PathVariable Integer id){
		System.out.println("got parameter bookId = " + id);
		return employeeService.findOne(id);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public String create(Employee employee){
		System.out.println("got parameter employee = " + employee.toString());
		
		if (employee.getId() != null) {
			System.err.println("id is not null");
			return "failed: id is not null";
		}
		
		if (employee.getName() == null || employee.getName().trim().length() == 0) {
			System.err.println("name is null");
			return "failed: name is null";
		}
		
		employee = employeeService.save(employee);
		
		if (employee == null) {
			System.err.println("failed: employee id already exist");
			return "failed: employee id already exist";
		} else {
			System.out.println("create success:" + employee.toString());
			return "success add employee: " + employee.getId();
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public String update(@PathVariable Integer id, Employee employee){
		System.out.println("got parameter id = " + id + ", employee = " + employee.toString());
		
		if (id.intValue() != employee.getId().intValue()) {
			System.err.println("id is not equal");
			return "Error: id is not equal";
		}
		
		employee = employeeService.update(employee);
		if (employee == null) {
			System.err.println("update failed: not found employee");
			return "failed: not found employee";
		} else {
			System.out.println("update success:" + employee.toString());
			return "success update employee:" + employee.getId();
		}
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable Integer id){
		System.out.println("got parameter id = " + id);
		if (id <= 0) {
			System.err.println("delete Error: id cannot be <= 0");
			return "Error: id cannot be <= 0";
		}
		
		Integer result = employeeService.delete(id);
		
		if (result.intValue() == 0) {
			System.out.println("delete success employee id:" + id);
			return "success delete employee id:" + id;
		} else {
			System.err.println("delete failed: " + result);
			return "failed: " + result;
		}
		
	}
	
	@RequestMapping(value="/page/{page}", method=RequestMethod.GET)
	@ResponseBody
	public Page<Employee> getAll(@PathVariable Integer page){
		System.out.println("page = " + page);
		return employeeService.findAll(page);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public List<Employee> getEmployees(Employee employee){
		System.out.println("got parameter employee = " + employee.toString());
		return employeeService.search(employee);
	}
	
}
