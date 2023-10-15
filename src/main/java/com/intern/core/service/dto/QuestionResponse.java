package com.intern.core.service.dto;

import java.util.List;

public class QuestionResponse {
	
	private String questionId;

	private String questionNumber;

	private String questionDesc;

	private String questionCategoryCode;

	private String questionCategoryDesc;

	private Integer questionWeightage;

	private Integer questionTotalMark;

	private List<Integer> questionTotalMarks;

	private String criteriaId;
	
	private ResultResponse questionResult;

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

	public Integer getQuestionTotalMark() {
		return questionTotalMark;
	}

	public void setQuestionTotalMark(Integer questionTotalMark) {
		this.questionTotalMark = questionTotalMark;
	}

	public List<Integer> getQuestionTotalMarks() {
		return questionTotalMarks;
	}

	public void setQuestionTotalMarks(List<Integer> questionTotalMarks) {
		this.questionTotalMarks = questionTotalMarks;
	}

	public String getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(String criteriaId) {
		this.criteriaId = criteriaId;
	}

	public ResultResponse getQuestionResult() {
		return questionResult;
	}

	public void setQuestionResult(ResultResponse questionResult) {
		this.questionResult = questionResult;
	}
}
