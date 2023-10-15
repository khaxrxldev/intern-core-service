package com.intern.core.service.dto;

public class CriteriaResponse {
	
	private String criteriaId;

	private String criteriaName;

	private String criteriaDesc;

	private Integer criteriaPercentage;

	private String evaluationId;

	public String getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(String criteriaId) {
		this.criteriaId = criteriaId;
	}

	public String getCriteriaName() {
		return criteriaName;
	}

	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

	public String getCriteriaDesc() {
		return criteriaDesc;
	}

	public void setCriteriaDesc(String criteriaDesc) {
		this.criteriaDesc = criteriaDesc;
	}

	public Integer getCriteriaPercentage() {
		return criteriaPercentage;
	}

	public void setCriteriaPercentage(Integer criteriaPercentage) {
		this.criteriaPercentage = criteriaPercentage;
	}

	public String getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(String evaluationId) {
		this.evaluationId = evaluationId;
	}
}
