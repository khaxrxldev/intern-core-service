package com.intern.core.service.dto;

public class StudentResultResponse {

	private StudentResponse student;
	
	private String studentEvaluationsStatus;
	
	private Float subject1TotalMark;
	
	private String subject1Grade;
	
	private String subject1Pointer;
	
	private Float subject2TotalMark;
	
	private String subject2Grade;
	
	private String subject2Pointer;

	public StudentResponse getStudent() {
		return student;
	}

	public void setStudent(StudentResponse student) {
		this.student = student;
	}

	public String getStudentEvaluationsStatus() {
		return studentEvaluationsStatus;
	}

	public void setStudentEvaluationsStatus(String studentEvaluationsStatus) {
		this.studentEvaluationsStatus = studentEvaluationsStatus;
	}

	public Float getSubject1TotalMark() {
		return subject1TotalMark;
	}

	public void setSubject1TotalMark(Float subject1TotalMark) {
		this.subject1TotalMark = subject1TotalMark;
	}

	public String getSubject1Grade() {
		return subject1Grade;
	}

	public void setSubject1Grade(String subject1Grade) {
		this.subject1Grade = subject1Grade;
	}

	public String getSubject1Pointer() {
		return subject1Pointer;
	}

	public void setSubject1Pointer(String subject1Pointer) {
		this.subject1Pointer = subject1Pointer;
	}

	public Float getSubject2TotalMark() {
		return subject2TotalMark;
	}

	public void setSubject2TotalMark(Float subject2TotalMark) {
		this.subject2TotalMark = subject2TotalMark;
	}

	public String getSubject2Grade() {
		return subject2Grade;
	}

	public void setSubject2Grade(String subject2Grade) {
		this.subject2Grade = subject2Grade;
	}

	public String getSubject2Pointer() {
		return subject2Pointer;
	}

	public void setSubject2Pointer(String subject2Pointer) {
		this.subject2Pointer = subject2Pointer;
	}
}
