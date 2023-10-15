package com.intern.core.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intern_question")
public class QuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "question_id")
	private String questionId;

	@Column(name = "question_number")
	private String questionNumber;

	@Column(name = "question_desc")
	private String questionDesc;

	@Column(name = "question_category_code")
	private String questionCategoryCode;

	@Column(name = "question_category_desc")
	private String questionCategoryDesc;

	@Column(name = "question_weightage")
	private Integer questionWeightage;

	@Column(name = "question_total_mark")
	private Integer questionTotalMark;

	@Column(name = "criteria_id")
	private String criteriaId;

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

	public String getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(String criteriaId) {
		this.criteriaId = criteriaId;
	}
}
