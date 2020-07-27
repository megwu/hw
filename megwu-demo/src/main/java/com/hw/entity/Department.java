package com.hw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="t_department")
public class Department {
	// 部門 ID
	@Id
	@GeneratedValue
	private Integer id;
	// 部門名稱
	@Column(name="name")
	private String name;
	
	/** 部門 ID */
	public Integer getId() {
		return this.id;
	}
	/** 部門 ID */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/** 部門名稱 */
	public String getName() {
		return this.name;
	}
	/** 部門名稱 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	
}
