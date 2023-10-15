package com.intern.core.service.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ApplicationRequest {
	
	private String applicationId;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime applicationDate;

	private String applicationCompStatus;

	private String applicationStudStatus;

	private String studentMatricNum;

	private String companyId;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
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
