package com.intern.core.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.core.service.entity.CriteriaEntity;

public interface CriteriaRepository extends JpaRepository<CriteriaEntity, String> {

	CriteriaEntity findByCriteriaId(String criteriaId);
	
	List<CriteriaEntity> findByEvaluationId(String evaluationId);

	Integer deleteByCriteriaId(String criteriaId);
}
