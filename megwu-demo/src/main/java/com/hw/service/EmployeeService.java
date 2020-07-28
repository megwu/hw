package com.hw.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	
	/**
	 * Search Employee's Data
	 * @param employee
	 * @return
	 */
	public List<Employee> search(final Employee employee) {
		return employeeRepository.fetchEmpDeptDataInnerJoin(new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate stuNameLike = null;
				if(null != employee && !StringUtils.isEmpty(employee.getName())) {
					stuNameLike = cb.like(root.<String> get("name"), "%" + employee.getName() + "%");
				}
				
				Predicate clazzNameLike = null;
				if(null != employee && !StringUtils.isEmpty(employee.getName())) {
					clazzNameLike = cb.like(root.<String> get("name"), "%" + employee.getName() + "%");
				}
				
				if(null != stuNameLike) query.where(stuNameLike);
					if(null != clazzNameLike) query.where(clazzNameLike);
						return null;
				}
			//}, new PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "id")));
		});
	}
	
//	private EntityManager em ;
//    @SuppressWarnings("unchecked")
//	
//    public List<Employee> search(Employee user) {
//        //String dataSql = "select t from User t where 1 = 1";
//    	String dataSql = "SELECT new com.hw.entity.Employee(e.id, e.name, e.deptId, d.name as deptName, e.sex, e.tel"
//			+ ", e.addr, e.age, e.gftm, e.txtm) "
//			+ "FROM t_employee e, t_department d where d.id = e.deptId";
//        String countSql = "select count(t) from t_employee t where 1 = 1";
//        
//        if(null != user && !StringUtils.isEmpty(user.getName())) {
//            dataSql += " and t.name = ?1";
//            countSql += " and t.name = ?1";
//        }
//        
//        Query dataQuery = em.createQuery(dataSql);
//        Query countQuery = em.createQuery(countSql);
//        
//        if(null != user && !StringUtils.isEmpty(user.getName())) {
//            dataQuery.setParameter(1, user.getName());
//            countQuery.setParameter(1, user.getName());
//        }long totalSize = (long) countQuery.getSingleResult();
//        System.err.println("totalSize = " + totalSize);
//        return dataQuery.getResultList();
//    }
	
//	public List<Employee> search() {
//		return employeeRepository.fetchEmpDeptDataInnerJoin();
//	}
	
	/**
	 * Query Employee by page
	 * @param page
	 * @return
	 */
	public Page<Employee> findAll(int page) {
	    return employeeRepository.findAll(PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "id")));
	}
}
