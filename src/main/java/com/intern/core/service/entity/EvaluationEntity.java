package com.intern.core.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intern_evaluation")
public class EvaluationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "evaluation_id")
	private String evaluationId;

	@Column(name = "evaluation_name")
	private String evaluationName;

	@Column(name = "evaluation_category")
	private String evaluationCategory;

	@Column(name = "evaluation_part")
	private String evaluationPart;

	@Column(name = "evaluation_subject")
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
