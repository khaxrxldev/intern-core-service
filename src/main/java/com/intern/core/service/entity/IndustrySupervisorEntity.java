package com.intern.core.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "intern_industry_sv")
public class IndustrySupervisorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "industry_sv_id")
	private String industrySvId;

	@Column(name = "industry_sv_name")
	private String industrySvName;

	@Column(name = "industry_sv_phone")
	private String industrySvPhone;

	@Column(name = "industry_sv_email")
	private String industrySvEmail;

	@Column(name = "industry_sv_password")
	private String industrySvPassword;

	@Column(name = "industry_sv_gender")
	private String industrySvGender;

	@Column(name = "industry_sv_position")
	private String industrySvPosition;
	
	@Column(name = "company_id")
	private String companyId;

	public String getIndustrySvId() {
		return industrySvId;
	}

	public void setIndustrySvId(String industrySvId) {
		this.industrySvId = industrySvId;
	}

	public String getIndustrySvName() {
		return industrySvName;
	}

	public void setIndustrySvName(String industrySvName) {
		this.industrySvName = industrySvName;
	}

	public String getIndustrySvPhone() {
		return industrySvPhone;
	}

	public void setIndustrySvPhone(String industrySvPhone) {
		this.industrySvPhone = industrySvPhone;
	}

	public String getIndustrySvEmail() {
		return industrySvEmail;
	}

	public void setIndustrySvEmail(String industrySvEmail) {
		this.industrySvEmail = industrySvEmail;
	}

	public String getIndustrySvPassword() {
		return industrySvPassword;
	}

	public void setIndustrySvPassword(String industrySvPassword) {
		this.industrySvPassword = industrySvPassword;
	}

	public String getIndustrySvGender() {
		return industrySvGender;
	}

	public void setIndustrySvGender(String industrySvGender) {
		this.industrySvGender = industrySvGender;
	}

	public String getIndustrySvPosition() {
		return industrySvPosition;
	}

	public void setIndustrySvPosition(String industrySvPosition) {
		this.industrySvPosition = industrySvPosition;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
