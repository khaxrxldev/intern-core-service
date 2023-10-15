package com.intern.core.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.core.service.entity.ResultEntity;

public interface ResultRepository extends JpaRepository<ResultEntity, String> {

	ResultEntity findByResultId(String resultId);

	ResultEntity findByQuestionIdAndStudentEvaluationId(String questionId, String studentEvaluationId);
}
