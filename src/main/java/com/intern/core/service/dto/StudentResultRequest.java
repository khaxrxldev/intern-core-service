package com.intern.core.service.dto;

public class StudentResultRequest {

	private String filterClass;
	
	private String filterEvaluationStatus;

	public String getFilterClass() {
		return filterClass;
	}

	public void setFilterClass(String filterClass) {
		this.filterClass = filterClass;
	}

	public String getFilterEvaluationStatus() {
		return filterEvaluationStatus;
	}

	public void setFilterEvaluationStatus(String filterEvaluationStatus) {
		this.filterEvaluationStatus = filterEvaluationStatus;
	}
}
