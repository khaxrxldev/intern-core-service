package com.intern.core.service.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.intern.core.service.dto.ApplicationRequest;
import com.intern.core.service.dto.ApplicationResponse;
import com.intern.core.service.dto.CompanyResponse;
import com.intern.core.service.dto.CriteriaRequest;
import com.intern.core.service.dto.CriteriaResponse;
import com.intern.core.service.dto.EvaluationCriteriasResponse;
import com.intern.core.service.dto.EvaluationRequest;
import com.intern.core.service.dto.EvaluationResponse;
import com.intern.core.service.dto.ResultRequest;
import com.intern.core.service.dto.ResultResponse;
import com.intern.core.service.dto.SemesterRequest;
import com.intern.core.service.dto.StudentEvaluationRequest;
import com.intern.core.service.dto.StudentEvaluationResponse;
import com.intern.core.service.dto.StudentResponse;
import com.intern.core.service.dto.StudentResultResponse;

public interface CoreService {
	
	List<EvaluationResponse> getEvaluations();
	
	List<EvaluationResponse> filterEvaluations(EvaluationRequest evaluationRequest);
	
	EvaluationResponse getEvaluationByEvaluationId(String evaluationId);
	
	EvaluationResponse insertEvaluation(EvaluationRequest evaluationRequest) throws Exception;
	
	EvaluationResponse updateEvaluation(EvaluationRequest evaluationRequest) throws Exception;
	
	Boolean deleteEvaluationByEvaluationId(String evaluationId);
	
	List<ApplicationResponse> getApplications();
	
	List<ApplicationResponse> filterApplications(ApplicationRequest applicationRequest);
	
	List<CompanyResponse> getCompaniesByStudentApplication(Boolean applyStatus, String studentMatricNum);
	
	ApplicationResponse getApplicationByApplicationId(String applicationId);
	
	ApplicationResponse insertApplication(ApplicationRequest applicationRequest) throws Exception;
	
	ApplicationResponse updateApplication(ApplicationRequest applicationRequest) throws Exception;
	
	Boolean deleteApplicationByApplicationId(String applicationId);
	
	List<StudentResultResponse> getStudentsResults();
	
	List<StudentEvaluationResponse> filterStudentEvaluations(StudentEvaluationRequest studentEvaluationRequest);
	
	StudentEvaluationResponse getStudentEvaluationByStudentEvaluationId(String studentEvaluationId);
	
	List<StudentEvaluationResponse> insertStudentEvaluations(List<SemesterRequest> semesterRequests, String studentMatricNum) throws Exception;
	
	StudentEvaluationResponse insertStudentEvaluation(StudentEvaluationRequest studentEvaluationRequest, MultipartFile attachFile) throws Exception;
	
	StudentEvaluationResponse updateStudentEvaluation(StudentEvaluationRequest studentEvaluationRequest, MultipartFile attachFile) throws Exception;
	
	Boolean deleteStudentEvaluation(String studentEvaluationId);
	
	Boolean updateStudentEvaluations(String studentMatricNum, List<String> evaluationIds);
	
	List<CriteriaResponse> getCriterias();
	
	List<CriteriaResponse> filterCriterias(CriteriaRequest criteriaRequest);
	
	CriteriaResponse getCriteriaByCriteriaId(String criteriaId);
	
	CriteriaResponse insertCriteria(CriteriaRequest criteriaRequest) throws Exception;
	
	CriteriaResponse updateCriteria(CriteriaRequest criteriaRequest) throws Exception;
	
	Boolean deleteCriteriaByCriteriaId(String criteriaId);
	
	EvaluationCriteriasResponse getCompleteEvaluation(String evaluationId, String studentEvaluationId);
	
	List<ResultResponse> filterResults(ResultRequest resultRequest);
	
	ResultResponse getResultByResultId(String resultId);
	
	List<ResultResponse> insertResults(List<ResultRequest> resultRequests) throws Exception;
	
	ResultResponse updateResult(ResultRequest resultRequest) throws Exception;
	
	Boolean deleteResultByResultId(String resultId);
	
	List<StudentResponse> getEvaluationsStatus(String userType, String userId);
}
