package com.hw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="t_employee")
public class Employee {
	// 員工編號
	@Id
	@GeneratedValue
	private Integer id;
	// 姓名
	@Column(name="name")
	private String name;
	// 部門 ID
	@Column(name="dept_id")
	private String deptId;
	// 性別
	@Column(name="sex")
	private String sex;
	// 電話
	@Column(name="tel")
	private String tel;
	// 地址
	@Column(name="addr")
	private String addr;
	// 年齡
	@Column(name="age")
	private String age;
	// 建立時間
	@Column(name="gftm")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Temporal(TemporalType.DATE)
	private Date gftm;
	// 最後修改時間
	@Column(name="txtm")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
	@Temporal(TemporalType.DATE)
	private Date txtm;
	
	private String deptName;
	
	public Employee(){}
	
	public Employee(Integer id, String name, String deptId, String deptName, String sex, String tel, String addr, String age, Date gftm, Date txtm) {
		this.id = id;
		this.name = name;
		this.deptId = deptId;
		this.deptName = deptName;
		this.sex = sex;
		this.tel = tel;
		this.addr = addr;
		this.age = age;
		this.gftm = gftm;
		this.txtm = txtm;
	}
	
	/** 員工編號 */
	public Integer getId() {
		return this.id;
	}
	/** 員工編號 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/** 員工姓名 */
	public String getName() {
		return this.name;
	}
	/** 員工姓名 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** 部門 ID */
	public String getDeptId() {
		return this.deptId;
	}
	/** 部門 ID */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	/** 性別 */
	public String getSex() {
		return this.sex;
	}
	/** 性別 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/** 電話 */
	public String getTel() {
		return this.tel;
	}
	/** 電話 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	/** 地址 */
	public String getAddr() {
		return this.addr;
	}
	/** 地址 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	/** 年齡 */
	public String getAge() {
		return this.age;
	}
	/** 年齡 */
	public void setAge(String age) {
		this.age = age;
	}
	
	/** 建立時間 */
	public Date getGftm() {
		return this.gftm;
	}
	/** 建立時間 */
	public void setGftm(Date gftm) {
		this.gftm = gftm;
	}
	
	/** 最後編修時間 */
	public Date getTxtm() {
		return this.txtm;
	}
	/** 最後編修時間 */
	public void setTxtm(Date txtm) {
		this.txtm = txtm;
	}
	
	/** 部門 Name */
	public String getDeptName() {
		return this.deptName;
	}
	/** 部門 Name */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", deptId=" + deptId + ", sex=" + sex + ", tel=" + tel
				+ ", addr=" + addr + ", age=" + age + ", gftm=" + gftm + ", txtm=" + txtm + "]";
	}
	
}
