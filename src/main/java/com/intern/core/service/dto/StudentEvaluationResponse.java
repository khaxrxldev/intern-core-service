package com.intern.core.service.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class StudentEvaluationResponse {
	
	private String studentEvaluationId;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime studentEvaluationDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime studentEvaluationStartDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime studentEvaluationEndDate;

	private String studentEvaluationStatus;

	private byte[] studentEvaluationAttach;

	private String studentEvaluationAttachFileName;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime studentEvaluationAttachDate;

	private String studentEvaluationComment;

	private Float studentEvaluationTotalScore;

	private String evaluationId;
	
	private EvaluationResponse evaluation;

	private String studentMatricNum;

	private String academicSvId;

	private String industrySvId;
	
	private String semesterId;
	
	private SemesterResponse semester;

	public String getStudentEvaluationId() {
		return studentEvaluationId;
	}

	public void setStudentEvaluationId(String studentEvaluationId) {
		this.studentEvaluationId = studentEvaluationId;
	}

	public LocalDateTime getStudentEvaluationDate() {
		return studentEvaluationDate;
	}

	public void setStudentEvaluationDate(LocalDateTime studentEvaluationDate) {
		this.studentEvaluationDate = studentEvaluationDate;
	}

	public LocalDateTime getStudentEvaluationStartDate() {
		return studentEvaluationStartDate;
	}

	public void setStudentEvaluationStartDate(LocalDateTime studentEvaluationStartDate) {
		this.studentEvaluationStartDate = studentEvaluationStartDate;
	}

	public LocalDateTime getStudentEvaluationEndDate() {
		return studentEvaluationEndDate;
	}

	public void setStudentEvaluationEndDate(LocalDateTime studentEvaluationEndDate) {
		this.studentEvaluationEndDate = studentEvaluationEndDate;
	}

	public String getStudentEvaluationStatus() {
		return studentEvaluationStatus;
	}

	public void setStudentEvaluationStatus(String studentEvaluationStatus) {
		this.studentEvaluationStatus = studentEvaluationStatus;
	}

	public byte[] getStudentEvaluationAttach() {
		return studentEvaluationAttach;
	}

	public void setStudentEvaluationAttach(byte[] studentEvaluationAttach) {
		this.studentEvaluationAttach = studentEvaluationAttach;
	}

	public String getStudentEvaluationAttachFileName() {
		return studentEvaluationAttachFileName;
	}

	public void setStudentEvaluationAttachFileName(String studentEvaluationAttachFileName) {
		this.studentEvaluationAttachFileName = studentEvaluationAttachFileName;
	}

	public LocalDateTime getStudentEvaluationAttachDate() {
		return studentEvaluationAttachDate;
	}

	public void setStudentEvaluationAttachDate(LocalDateTime studentEvaluationAttachDate) {
		this.studentEvaluationAttachDate = studentEvaluationAttachDate;
	}

	public String getStudentEvaluationComment() {
		return studentEvaluationComment;
	}

	public void setStudentEvaluationComment(String studentEvaluationComment) {
		this.studentEvaluationComment = studentEvaluationComment;
	}

	public Float getStudentEvaluationTotalScore() {
		return studentEvaluationTotalScore;
	}

	public void setStudentEvaluationTotalScore(Float studentEvaluationTotalScore) {
		this.studentEvaluationTotalScore = studentEvaluationTotalScore;
	}

	public String getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(String evaluationId) {
		this.evaluationId = evaluationId;
	}

	public EvaluationResponse getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(EvaluationResponse evaluation) {
		this.evaluation = evaluation;
	}

	public String getStudentMatricNum() {
		return studentMatricNum;
	}

	public void setStudentMatricNum(String studentMatricNum) {
		this.studentMatricNum = studentMatricNum;
	}

	public String getAcademicSvId() {
		return academicSvId;
	}

	public void setAcademicSvId(String academicSvId) {
		this.academicSvId = academicSvId;
	}

	public String getIndustrySvId() {
		return industrySvId;
	}

	public void setIndustrySvId(String industrySvId) {
		this.industrySvId = industrySvId;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

	public SemesterResponse getSemester() {
		return semester;
	}

	public void setSemester(SemesterResponse semester) {
		this.semester = semester;
	}
}
