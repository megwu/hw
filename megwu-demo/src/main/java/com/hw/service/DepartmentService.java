package com.hw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hw.entity.Department;
import com.hw.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Resource
	private DepartmentRepository departmentRepository;
	
	public List<Department> findAll(){
		return departmentRepository.findAll();
	}
	
	public Department findOne(Integer id){
		return departmentRepository.findById(id).get();
	}
	
	public Department save(Department department) {
		
		if (department.getId() != null) {
			Department tmpDepartment = departmentRepository.findById(department.getId()).get();
			if (tmpDepartment!=null) {
				return null;
			}
		}
		
		return departmentRepository.save(department);
	}
	
	public Department update(Department department) {
		Department tmpDepartment = departmentRepository.findById(department.getId()).get();
		if (tmpDepartment==null) {
			return null;
		}
		tmpDepartment.setName(department.getName());
		
		return departmentRepository.save(tmpDepartment);
	}
	
	public Integer delete(Integer id) {
		Department tmpDepartment = departmentRepository.findById(id).get();
		if (tmpDepartment==null) {
			return -1;
		} else {
			departmentRepository.deleteById(id);
			return 0;
		}
	}
}
