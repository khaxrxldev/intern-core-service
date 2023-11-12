package com.intern.core.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.core.service.entity.AcademicSupervisorEntity;

@Repository
public interface AcademicSupervisorRepository extends JpaRepository<AcademicSupervisorEntity, String> {

	AcademicSupervisorEntity findByAcademicSvId(String academicSvId);
	
	AcademicSupervisorEntity findByAcademicSvEmail(String academicSvEmail);
	
	AcademicSupervisorEntity findByAcademicSvEmailAndAcademicSvCoordinator(String academicSvEmail, Integer academicSvCoordinator);
	
	Integer deleteByAcademicSvId(String academicSvId);
}
