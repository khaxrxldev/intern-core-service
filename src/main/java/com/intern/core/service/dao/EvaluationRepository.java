package com.intern.core.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.core.service.entity.EvaluationEntity;

@Repository
public interface EvaluationRepository extends JpaRepository<EvaluationEntity, String> {

	EvaluationEntity findByEvaluationId(String evaluationId);

	Integer deleteByEvaluationId(String evaluationId);
}
