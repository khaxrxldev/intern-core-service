package com.intern.core.service.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intern_semester")
public class SemesterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "semester_id")
	private String semesterId;

	@Column(name = "semester_code")
	private String semesterCode;

	@Column(name = "semester_part")
	private String semesterPart;

	@Column(name = "semester_status")
	private String semesterStatus;

	@Column(name = "semester_start_date")
	private Date semesterStartDate;

	@Column(name = "semester_end_date")
	private Date semesterEndDate;

	@Column(name = "semester_start_evaluate_date")
	private Date semesterStartEvaluateDate;

	@Column(name = "semester_end_evaluate_date")
	private Date semesterEndEvaluateDate;

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

	public Date getSemesterStartDate() {
		return semesterStartDate;
	}

	public void setSemesterStartDate(Date semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}

	public Date getSemesterEndDate() {
		return semesterEndDate;
	}

	public void setSemesterEndDate(Date semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}

	public Date getSemesterStartEvaluateDate() {
		return semesterStartEvaluateDate;
	}

	public void setSemesterStartEvaluateDate(Date semesterStartEvaluateDate) {
		this.semesterStartEvaluateDate = semesterStartEvaluateDate;
	}

	public Date getSemesterEndEvaluateDate() {
		return semesterEndEvaluateDate;
	}

	public void setSemesterEndEvaluateDate(Date semesterEndEvaluateDate) {
		this.semesterEndEvaluateDate = semesterEndEvaluateDate;
	}
}
