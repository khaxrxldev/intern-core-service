package com.intern.core.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intern_criteria")
public class CriteriaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "criteria_id")
	private String criteriaId;

	@Column(name = "criteria_name")
	private String criteriaName;

	@Column(name = "criteria_desc")
	private String criteriaDesc;

	@Column(name = "criteria_percentage")
	private Integer criteriaPercentage;

	@Column(name = "evaluation_id")
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
