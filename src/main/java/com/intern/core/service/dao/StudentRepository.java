package com.intern.core.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.core.service.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, String> {

	StudentEntity findByStudentMatricNum(String studentMatricNum);

	StudentEntity findByStudentEmail(String studentEmail);

	Integer deleteByStudentMatricNum(String studentMatricNum);
	
	List<StudentEntity> findByAcademicSvId(String academicSvId);
	
	List<StudentEntity> findByIndustrySvId(String industrySvId);
}
