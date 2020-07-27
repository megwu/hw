package com.hw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hw.entity.Department;
import com.hw.service.DepartmentService;

@Controller
@RequestMapping("/dept")
public class DepartmentController {
	
	@Resource
	private DepartmentService departmentService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public List<Department> getAll(){
		return departmentService.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Department getOne(@PathVariable Integer id){
		System.out.println("DepartmentController got parameter id = " + id);
		return departmentService.findOne(id);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public String create(Department department){
		System.out.println("DepartmentController got parameter department = " + department.toString());
		
		if (department.getId() != null) {
			System.err.println("id is not null");
			return "failed: id is not null";
		}
		
		if (department.getName() == null || department.getName().trim().length() == 0) {
			System.err.println("name is null");
			return "failed: name is null";
		}
		
		department = departmentService.save(department);
		
		if (department == null) {
			return "failed: department id already exist";
		} else {
			System.out.println("after save to DB: " + department.toString());
			return "success add department: " + department.getId();
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public String update(@PathVariable Integer id, Department department){
		
		System.out.println("DepartmentController got parameter department = " + department.toString());
		if (id.intValue() != department.getId().intValue()) {
			System.err.println("Error: id is not equal");
			return "Error: id is not equal";
		}
		
		department = departmentService.update(department);
		if (department == null) {
			System.err.println("failed: not found department id:" + id);
			return "failed: not found department";
		} else {
			return "success update department id:" + department.getId();
		}
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable Integer id){
		System.out.println("DepartmentController got parameter id = " + id);
		if (id <= 0) {
			return "Error: id cannot be <= 0";
		}
		
		Integer result = departmentService.delete(id);
		
		if (result.intValue() == 0) {
			return "success delete department id:" + id;
			
		} else {
			return "failed: " + result;
		}
		
	}
}
