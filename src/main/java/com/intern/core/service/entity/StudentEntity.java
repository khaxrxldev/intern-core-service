package com.intern.core.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "intern_student")
public class StudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "student_matric_num")
	private String studentMatricNum;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "student_address")
	private String studentAddress;

	@Column(name = "student_email")
	private String studentEmail;

	@Column(name = "student_phone")
	private String studentPhone;

	@Column(name = "student_password")
	private String studentPassword;

	@Column(name = "student_campus")
	private String studentCampus;

	@Column(name = "student_course")
	private String studentCourse;

	@Column(name = "student_class")
	private String studentClass;

	@Column(name = "student_project")
	private String studentProject;

	@Lob
	@Column(name = "student_cv")
	private byte[] studentCV;

	@Column(name = "student_cv_file_name")
	private String studentCVFileName;

	@Lob
	@Column(name = "student_mini_transcript")
	private byte[] studentMiniTranscript;

	@Column(name = "student_mini_transcript_file_name")
	private String studentMiniTranscriptFileName;

	@Lob
	@Column(name = "student_cover_letter")
	private byte[] studentCoverLetter;

	@Column(name = "student_cover_letter_file_name")
	private String studentCoverLetterFileName;

	@Lob
	@Column(name = "student_course_outline")
	private byte[] studentCourseOutline;

	@Column(name = "student_course_outline_file_name")
	private String studentCourseOutlineFileName;

	@Lob
	@Column(name = "student_sl")
	private byte[] studentSL;

	@Column(name = "student_sl_file_name")
	private String studentSLFileName;

	@Column(name = "industry_sv_id")
	private String industrySvId;

	@Column(name = "academic_sv_id")
	private String academicSvId;

	@Column(name = "coordinator_id")
	private String coordinatorId;

	public String getStudentMatricNum() {
		return studentMatricNum;
	}

	public void setStudentMatricNum(String studentMatricNum) {
		this.studentMatricNum = studentMatricNum;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentCampus() {
		return studentCampus;
	}

	public void setStudentCampus(String studentCampus) {
		this.studentCampus = studentCampus;
	}

	public String getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(String studentCourse) {
		this.studentCourse = studentCourse;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	
	public String getStudentProject() {
		return studentProject;
	}

	public void setStudentProject(String studentProject) {
		this.studentProject = studentProject;
	}

	public byte[] getStudentCV() {
		return studentCV;
	}

	public void setStudentCV(byte[] studentCV) {
		this.studentCV = studentCV;
	}

	public String getStudentCVFileName() {
		return studentCVFileName;
	}

	public void setStudentCVFileName(String studentCVFileName) {
		this.studentCVFileName = studentCVFileName;
	}

	public byte[] getStudentMiniTranscript() {
		return studentMiniTranscript;
	}

	public void setStudentMiniTranscript(byte[] studentMiniTranscript) {
		this.studentMiniTranscript = studentMiniTranscript;
	}

	public String getStudentMiniTranscriptFileName() {
		return studentMiniTranscriptFileName;
	}

	public void setStudentMiniTranscriptFileName(String studentMiniTranscriptFileName) {
		this.studentMiniTranscriptFileName = studentMiniTranscriptFileName;
	}

	public byte[] getStudentCoverLetter() {
		return studentCoverLetter;
	}

	public void setStudentCoverLetter(byte[] studentCoverLetter) {
		this.studentCoverLetter = studentCoverLetter;
	}

	public String getStudentCoverLetterFileName() {
		return studentCoverLetterFileName;
	}

	public void setStudentCoverLetterFileName(String studentCoverLetterFileName) {
		this.studentCoverLetterFileName = studentCoverLetterFileName;
	}

	public byte[] getStudentCourseOutline() {
		return studentCourseOutline;
	}

	public void setStudentCourseOutline(byte[] studentCourseOutline) {
		this.studentCourseOutline = studentCourseOutline;
	}

	public String getStudentCourseOutlineFileName() {
		return studentCourseOutlineFileName;
	}

	public void setStudentCourseOutlineFileName(String studentCourseOutlineFileName) {
		this.studentCourseOutlineFileName = studentCourseOutlineFileName;
	}

	public byte[] getStudentSL() {
		return studentSL;
	}

	public void setStudentSL(byte[] studentSL) {
		this.studentSL = studentSL;
	}

	public String getStudentSLFileName() {
		return studentSLFileName;
	}

	public void setStudentSLFileName(String studentSLFileName) {
		this.studentSLFileName = studentSLFileName;
	}

	public String getIndustrySvId() {
		return industrySvId;
	}

	public void setIndustrySvId(String industrySvId) {
		this.industrySvId = industrySvId;
	}

	public String getAcademicSvId() {
		return academicSvId;
	}

	public void setAcademicSvId(String academicSvId) {
		this.academicSvId = academicSvId;
	}

	public String getCoordinatorId() {
		return coordinatorId;
	}

	public void setCoordinatorId(String coordinatorId) {
		this.coordinatorId = coordinatorId;
	}
}
