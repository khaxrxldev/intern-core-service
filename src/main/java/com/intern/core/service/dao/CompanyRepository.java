package com.intern.core.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.core.service.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

	CompanyEntity findByCompanyId(String companyId);
	
	Integer deleteByCompanyId(String companyId);
}
