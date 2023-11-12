package com.intern.core.service.dto;

public class ReportResponse {
	
	private String questionId;

	private String questionNumber;

	private String questionDesc;

	private String questionCategoryCode;

	private String questionCategoryDesc;

	private Integer questionWeightage;

	private Integer resultScore;

	private Float resultTotalMark;
	
	private String criteriaId;

	private String criteriaName;

	private String criteriaDesc;

	private Integer criteriaPercentage;

	private Float criteriaTotalMark;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public String getQuestionCategoryCode() {
		return questionCategoryCode;
	}

	public void setQuestionCategoryCode(String questionCategoryCode) {
		this.questionCategoryCode = questionCategoryCode;
	}

	public String getQuestionCategoryDesc() {
		return questionCategoryDesc;
	}

	public void setQuestionCategoryDesc(String questionCategoryDesc) {
		this.questionCategoryDesc = questionCategoryDesc;
	}

	public Integer getQuestionWeightage() {
		return questionWeightage;
	}

	public void setQuestionWeightage(Integer questionWeightage) {
		this.questionWeightage = questionWeightage;
	}

	public Integer getResultScore() {
		return resultScore;
	}

	public void setResultScore(Integer resultScore) {
		this.resultScore = resultScore;
	}

	public Float getResultTotalMark() {
		return resultTotalMark;
	}

	public void setResultTotalMark(Float resultTotalMark) {
		this.resultTotalMark = resultTotalMark;
	}

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

	public Float getCriteriaTotalMark() {
		return criteriaTotalMark;
	}

	public void setCriteriaTotalMark(Float criteriaTotalMark) {
		this.criteriaTotalMark = criteriaTotalMark;
	}
}
