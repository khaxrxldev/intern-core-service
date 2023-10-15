package com.intern.core.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.core.service.entity.ApplicationEntity;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, String> {

	ApplicationEntity findByApplicationId(String applicationId);

	List<ApplicationEntity> findByStudentMatricNum(String studentMatricNum);

	List<ApplicationEntity> findByStudentMatricNumNot(String studentMatricNum);
	
	Integer deleteByApplicationId(String applicationId);
}
