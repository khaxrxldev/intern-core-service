package com.intern.core.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.core.service.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, String> {

	QuestionEntity findByQuestionId(String questionId);

	List<QuestionEntity> findByCriteriaId(String criteriaId);
	
	Integer deleteByQuestionId(String questionId);
}
