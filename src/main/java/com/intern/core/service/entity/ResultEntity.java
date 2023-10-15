package com.intern.core.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intern_result")
public class ResultEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "result_id")
	private String resultId;

	@Column(name = "result_score")
	private Float resultScore;

	@Column(name = "result_total_mark")
	private Float resultTotalMark;

	@Column(name = "question_id")
	private String questionId;

	@Column(name = "student_evaluation_id")
	private String studentEvaluationId;

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public Float getResultScore() {
		return resultScore;
	}

	public void setResultScore(Float resultScore) {
		this.resultScore = resultScore;
	}

	public Float getResultTotalMark() {
		return resultTotalMark;
	}

	public void setResultTotalMark(Float resultTotalMark) {
		this.resultTotalMark = resultTotalMark;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getStudentEvaluationId() {
		return studentEvaluationId;
	}

	public void setStudentEvaluationId(String studentEvaluationId) {
		this.studentEvaluationId = studentEvaluationId;
	}
}
