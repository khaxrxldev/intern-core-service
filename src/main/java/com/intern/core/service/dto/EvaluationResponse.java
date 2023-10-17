package com.intern.core.service.dto;

public class EvaluationResponse {
	
	private String evaluationId;

	private String evaluationName;

	private String evaluationCategory;

	private String evaluationPart;

	private String evaluationSubject;

	public String getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(String evaluationId) {
		this.evaluationId = evaluationId;
	}

	public String getEvaluationName() {
		return evaluationName;
	}

	public void setEvaluationName(String evaluationName) {
		this.evaluationName = evaluationName;
	}

	public String getEvaluationCategory() {
		return evaluationCategory;
	}

	public void setEvaluationCategory(String evaluationCategory) {
		this.evaluationCategory = evaluationCategory;
	}

	public String getEvaluationPart() {
		return evaluationPart;
	}

	public void setEvaluationPart(String evaluationPart) {
		this.evaluationPart = evaluationPart;
	}

	public String getEvaluationSubject() {
		return evaluationSubject;
	}

	public void setEvaluationSubject(String evaluationSubject) {
		this.evaluationSubject = evaluationSubject;
	}
}
