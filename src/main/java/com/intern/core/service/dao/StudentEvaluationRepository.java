package com.intern.core.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.core.service.entity.StudentEvaluationEntity;

@Repository
public interface StudentEvaluationRepository extends JpaRepository<StudentEvaluationEntity, String> {

	StudentEvaluationEntity findByStudentEvaluationId(String studentEvaluationId);

	List<StudentEvaluationEntity> findByStudentMatricNum(String studentMatricNum);

	StudentEvaluationEntity findByEvaluationIdAndStudentMatricNum(String evaluationId, String studentMatricNum);

	List<StudentEvaluationEntity> findByStudentMatricNumAndStudentEvaluationStatus(String studentMatricNum, String studentEvaluationStatus);

	Integer deleteByStudentEvaluationId(String studentEvaluationId);
	
	Integer deleteByEvaluationIdAndStudentMatricNum(String evaluationId, String studentMatricNum);
}
