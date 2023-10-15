package com.intern.core.service.dto;

public class ResultRequest {
	
	private String resultId;

	private Float resultScore;

	private Float resultTotalMark;

	private String questionId;

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
