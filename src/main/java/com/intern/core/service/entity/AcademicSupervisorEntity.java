package com.intern.core.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intern_academic_sv")
public class AcademicSupervisorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "academic_sv_id")
	private String academicSvId;

	@Column(name = "academic_sv_name")
	private String academicSvName;

	@Column(name = "academic_sv_phone")
	private String academicSvPhone;

	@Column(name = "academic_sv_email")
	private String academicSvEmail;

	@Column(name = "academic_sv_password")
	private String academicSvPassword;

	@Column(name = "academic_sv_gender")
	private String academicSvGender;

	@Column(name = "academic_sv_position")
	private String academicSvPosition;

	@Column(name = "academic_sv_coordinator")
	private Integer academicSvCoordinator;

	public String getAcademicSvId() {
		return academicSvId;
	}

	public void setAcademicSvId(String academicSvId) {
		this.academicSvId = academicSvId;
	}

	public String getAcademicSvName() {
		return academicSvName;
	}

	public void setAcademicSvName(String academicSvName) {
		this.academicSvName = academicSvName;
	}

	public String getAcademicSvPhone() {
		return academicSvPhone;
	}

	public void setAcademicSvPhone(String academicSvPhone) {
		this.academicSvPhone = academicSvPhone;
	}

	public String getAcademicSvEmail() {
		return academicSvEmail;
	}

	public void setAcademicSvEmail(String academicSvEmail) {
		this.academicSvEmail = academicSvEmail;
	}

	public String getAcademicSvPassword() {
		return academicSvPassword;
	}

	public void setAcademicSvPassword(String academicSvPassword) {
		this.academicSvPassword = academicSvPassword;
	}

	public String getAcademicSvGender() {
		return academicSvGender;
	}

	public void setAcademicSvGender(String academicSvGender) {
		this.academicSvGender = academicSvGender;
	}

	public String getAcademicSvPosition() {
		return academicSvPosition;
	}

	public void setAcademicSvPosition(String academicSvPosition) {
		this.academicSvPosition = academicSvPosition;
	}

	public Integer getAcademicSvCoordinator() {
		return academicSvCoordinator;
	}

	public void setAcademicSvCoordinator(Integer academicSvCoordinator) {
		this.academicSvCoordinator = academicSvCoordinator;
	}
}
