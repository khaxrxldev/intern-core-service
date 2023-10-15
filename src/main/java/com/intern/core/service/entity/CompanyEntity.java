package com.intern.core.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "intern_company")
public class CompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "company_id")
	private String companyId;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "company_address")
	private String companyAddress;

	@Column(name = "company_phone")
	private String companyPhone;

	@Column(name = "company_email")
	private String companyEmail;

	@Column(name = "company_website")
	private String companyWebsite;

	@Lob
	@Column(name = "company_brochure")
	private byte[] companyBrochure;

	@Column(name = "company_brochure_file_name")
	private String companyBrochureFileName;

	@Column(name = "company_hr_name")
	private String companyHrName;

	@Column(name = "company_hr_phone")
	private String companyHrPhone;

	@Column(name = "company_hr_email")
	private String companyHrEmail;

	@Column(name = "company_hr_gender")
	private String companyHrGender;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public byte[] getCompanyBrochure() {
		return companyBrochure;
	}

	public void setCompanyBrochure(byte[] companyBrochure) {
		this.companyBrochure = companyBrochure;
	}

	public String getCompanyBrochureFileName() {
		return companyBrochureFileName;
	}

	public void setCompanyBrochureFileName(String companyBrochureFileName) {
		this.companyBrochureFileName = companyBrochureFileName;
	}

	public String getCompanyHrName() {
		return companyHrName;
	}

	public void setCompanyHrName(String companyHrName) {
		this.companyHrName = companyHrName;
	}

	public String getCompanyHrPhone() {
		return companyHrPhone;
	}

	public void setCompanyHrPhone(String companyHrPhone) {
		this.companyHrPhone = companyHrPhone;
	}

	public String getCompanyHrEmail() {
		return companyHrEmail;
	}

	public void setCompanyHrEmail(String companyHrEmail) {
		this.companyHrEmail = companyHrEmail;
	}

	public String getCompanyHrGender() {
		return companyHrGender;
	}

	public void setCompanyHrGender(String companyHrGender) {
		this.companyHrGender = companyHrGender;
	}
}
