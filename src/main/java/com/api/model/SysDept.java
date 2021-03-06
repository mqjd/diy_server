package com.api.model;
// Generated 2019-2-23 17:44:39 by Hibernate Tools 5.1.10.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.base.framework.model.BaseModel;

/**
 * SysDept generated by hbm2java
 */
@Entity
@Table(name = "sys_dept")
public class SysDept extends BaseModel {

	private static final long serialVersionUID = 8195190538848189379L;
	private Integer deptId;
	private Integer pDeptId;
	private String deptCode;
	private String deptName;
	private String deptHead;
	
	private List<SysDept> children;

	@Transient
	public List<SysDept> getChildren() {
		return children;
	}

	public void setChildren(List<SysDept> children) {
		this.children = children;
	}

	public SysDept() {
	}

	public SysDept(String deptName) {
		this.deptName = deptName;
	}

	public SysDept(Integer PDeptId, String deptCode, String deptName, String deptHead) {
		this.pDeptId = PDeptId;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.deptHead = deptHead;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "DEPT_ID", unique = true, nullable = false)
	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Column(name = "P_DEPT_ID")
	public Integer getPDeptId() {
		return this.pDeptId;
	}

	public void setPDeptId(Integer PDeptId) {
		this.pDeptId = PDeptId;
	}

	@Column(name = "DEPT_CODE", length = 20)
	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	@Column(name = "DEPT_NAME", nullable = false, length = 100)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "DEPT_HEAD", length = 20)
	public String getDeptHead() {
		return this.deptHead;
	}

	public void setDeptHead(String deptHead) {
		this.deptHead = deptHead;
	}

}
