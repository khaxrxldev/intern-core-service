package com.intern.core.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.core.service.entity.SemesterEntity;

public interface SemesterRepository extends JpaRepository<SemesterEntity, String> {
	
	List<SemesterEntity> findBySemesterPartAndSemesterStatus(String semesterPart, String semesterStatus);

	SemesterEntity findBySemesterId(String semesterId);
	
	Integer deleteBySemesterId(String semesterId);
}
