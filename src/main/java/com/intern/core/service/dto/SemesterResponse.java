package com.intern.core.service.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class SemesterResponse {
	
	private String semesterId;

	private String semesterCode;

	private String semesterPart;

	private String semesterStatus;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime semesterStartDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime semesterEndDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime semesterStartEvaluateDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime semesterEndEvaluateDate;

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

	public String getSemesterCode() {
		return semesterCode;
	}

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	public String getSemesterPart() {
		return semesterPart;
	}

	public void setSemesterPart(String semesterPart) {
		this.semesterPart = semesterPart;
	}

	public String getSemesterStatus() {
		return semesterStatus;
	}

	public void setSemesterStatus(String semesterStatus) {
		this.semesterStatus = semesterStatus;
	}

	public LocalDateTime getSemesterStartDate() {
		return semesterStartDate;
	}

	public void setSemesterStartDate(LocalDateTime semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}

	public LocalDateTime getSemesterEndDate() {
		return semesterEndDate;
	}

	public void setSemesterEndDate(LocalDateTime semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}

	public LocalDateTime getSemesterStartEvaluateDate() {
		return semesterStartEvaluateDate;
	}

	public void setSemesterStartEvaluateDate(LocalDateTime semesterStartEvaluateDate) {
		this.semesterStartEvaluateDate = semesterStartEvaluateDate;
	}

	public LocalDateTime getSemesterEndEvaluateDate() {
		return semesterEndEvaluateDate;
	}

	public void setSemesterEndEvaluateDate(LocalDateTime semesterEndEvaluateDate) {
		this.semesterEndEvaluateDate = semesterEndEvaluateDate;
	}
}
