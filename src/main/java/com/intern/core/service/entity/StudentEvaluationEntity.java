package com.intern.core.service.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "intern_student_evaluation")
public class StudentEvaluationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "student_evaluation_id")
	private String studentEvaluationId;

	@Column(name = "student_evaluation_date")
	private Date studentEvaluationDate;

	@Column(name = "student_evaluation_start_date")
	private Date studentEvaluationStartDate;

	@Column(name = "student_evaluation_end_date")
	private Date studentEvaluationEndDate;

	@Column(name = "student_evaluation_status")
	private String studentEvaluationStatus;

	@Lob
	@Column(name = "student_evaluation_attach")
	private byte[] studentEvaluationAttach;

	@Column(name = "student_evaluation_attach_file_name")
	private String studentEvaluationAttachFileName;

	@Column(name = "student_evaluation_attach_date")
	private Date studentEvaluationAttachDate;

	@Column(name = "student_evaluation_comment")
	private String studentEvaluationComment;

	@Column(name = "student_evaluation_total_score")
	private Float studentEvaluationTotalScore;

	@Column(name = "evaluation_id")
	private String evaluationId;

	@Column(name = "student_matric_num")
	private String studentMatricNum;

	@Column(name = "academic_sv_id")
	private String academicSvId;

	@Column(name = "industry_sv_id")
	private String industrySvId;

	@Column(name = "semester_id")
	private String semesterId;

	public String getStudentEvaluationId() {
		return studentEvaluationId;
	}

	public void setStudentEvaluationId(String studentEvaluationId) {
		this.studentEvaluationId = studentEvaluationId;
	}

	public Date getStudentEvaluationDate() {
		return studentEvaluationDate;
	}

	public void setStudentEvaluationDate(Date studentEvaluationDate) {
		this.studentEvaluationDate = studentEvaluationDate;
	}

	public Date getStudentEvaluationStartDate() {
		return studentEvaluationStartDate;
	}

	public void setStudentEvaluationStartDate(Date studentEvaluationStartDate) {
		this.studentEvaluationStartDate = studentEvaluationStartDate;
	}

	public Date getStudentEvaluationEndDate() {
		return studentEvaluationEndDate;
	}

	public void setStudentEvaluationEndDate(Date studentEvaluationEndDate) {
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

	public Date getStudentEvaluationAttachDate() {
		return studentEvaluationAttachDate;
	}

	public void setStudentEvaluationAttachDate(Date studentEvaluationAttachDate) {
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
}
