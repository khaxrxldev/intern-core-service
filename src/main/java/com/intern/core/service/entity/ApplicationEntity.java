package com.intern.core.service.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intern_application")
public class ApplicationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "application_id")
	private String applicationId;

	@Column(name = "application_date")
	private Date applicationDate;

	@Column(name = "application_comp_status")
	private String applicationCompStatus;

	@Column(name = "application_stud_status")
	private String applicationStudStatus;

	@Column(name = "student_matric_num")
	private String studentMatricNum;

	@Column(name = "company_id")
	private String companyId;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getApplicationCompStatus() {
		return applicationCompStatus;
	}

	public void setApplicationCompStatus(String applicationCompStatus) {
		this.applicationCompStatus = applicationCompStatus;
	}

	public String getApplicationStudStatus() {
		return applicationStudStatus;
	}

	public void setApplicationStudStatus(String applicationStudStatus) {
		this.applicationStudStatus = applicationStudStatus;
	}

	public String getStudentMatricNum() {
		return studentMatricNum;
	}

	public void setStudentMatricNum(String studentMatricNum) {
		this.studentMatricNum = studentMatricNum;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
