package com.intern.core.service.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.intern.core.service.dao.ApplicationRepository;
import com.intern.core.service.dao.CompanyRepository;
import com.intern.core.service.dao.CriteriaRepository;
import com.intern.core.service.dao.EvaluationRepository;
import com.intern.core.service.dao.QuestionRepository;
import com.intern.core.service.dao.ResultRepository;
import com.intern.core.service.dao.StudentEvaluationRepository;
import com.intern.core.service.dao.StudentRepository;
import com.intern.core.service.dto.ApplicationRequest;
import com.intern.core.service.dto.ApplicationResponse;
import com.intern.core.service.dto.CompanyResponse;
import com.intern.core.service.dto.CriteriaQuestionsResponse;
import com.intern.core.service.dto.CriteriaRequest;
import com.intern.core.service.dto.CriteriaResponse;
import com.intern.core.service.dto.EvaluationCriteriasResponse;
import com.intern.core.service.dto.EvaluationRequest;
import com.intern.core.service.dto.EvaluationResponse;
import com.intern.core.service.dto.QuestionResponse;
import com.intern.core.service.dto.ResultRequest;
import com.intern.core.service.dto.ResultResponse;
import com.intern.core.service.dto.StudentEvaluationRequest;
import com.intern.core.service.dto.StudentEvaluationResponse;
import com.intern.core.service.entity.ApplicationEntity;
import com.intern.core.service.entity.CompanyEntity;
import com.intern.core.service.entity.CriteriaEntity;
import com.intern.core.service.entity.EvaluationEntity;
import com.intern.core.service.entity.QuestionEntity;
import com.intern.core.service.entity.ResultEntity;
import com.intern.core.service.entity.StudentEvaluationEntity;
import com.intern.core.service.utility.BaseUtility;
import com.intern.core.service.utility.DateUtility;
import com.intern.core.service.entity.StudentEntity;
import com.intern.core.service.dto.StudentResponse;

@Service
public class CoreServiceImpl implements CoreService {

	@Autowired
	EvaluationRepository evaluationRepository;
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	StudentEvaluationRepository studentEvaluationRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CriteriaRepository criteriaRepository;
	
	@Autowired
	ResultRepository resultRepository;
	
	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public List<EvaluationResponse> getEvaluations() {
		List<EvaluationResponse> evaluationResponses = new ArrayList<EvaluationResponse>();
		List<EvaluationEntity> evaluationEntities = evaluationRepository.findAll();
		
		for (EvaluationEntity evaluationEntity : evaluationEntities) {
			EvaluationResponse evaluationResponse = new EvaluationResponse();
			
			evaluationResponse.setEvaluationId(evaluationEntity.getEvaluationId());
			evaluationResponse.setEvaluationName(evaluationEntity.getEvaluationName());
			evaluationResponse.setEvaluationCategory(evaluationEntity.getEvaluationCategory());
			evaluationResponse.setEvaluationSubject(evaluationEntity.getEvaluationSubject());
			
			evaluationResponses.add(evaluationResponse);
		}
		
		return evaluationResponses;
	}

	@Override
	public List<EvaluationResponse> filterEvaluations(EvaluationRequest evaluationRequest) {
		List<EvaluationResponse> evaluationResponses = new ArrayList<EvaluationResponse>();
		List<EvaluationEntity> evaluationEntities = evaluationRepository.findAll();
		
		for (EvaluationEntity evaluationEntity : evaluationEntities) {
			EvaluationResponse evaluationResponse = new EvaluationResponse();
			
			evaluationResponse.setEvaluationId(evaluationEntity.getEvaluationId());
			evaluationResponse.setEvaluationName(evaluationEntity.getEvaluationName());
			evaluationResponse.setEvaluationCategory(evaluationEntity.getEvaluationCategory());
			evaluationResponse.setEvaluationSubject(evaluationEntity.getEvaluationSubject());
			
			Boolean addRow = true;
			
			if (BaseUtility.isNotBlank(evaluationRequest.getEvaluationCategory()) && !evaluationRequest.getEvaluationCategory().equals(evaluationEntity.getEvaluationCategory())) {
				addRow = false;
			}
			if (BaseUtility.isNotBlank(evaluationRequest.getEvaluationSubject()) && !evaluationRequest.getEvaluationSubject().equals(evaluationEntity.getEvaluationSubject())) {
				addRow = false;
			}
			if (BaseUtility.isNotBlank(evaluationRequest.getStudentMatricNum())) {
				StudentEvaluationEntity existedStudentEvaluationEntity = studentEvaluationRepository.findByEvaluationIdAndStudentMatricNum(evaluationEntity.getEvaluationId(), evaluationRequest.getStudentMatricNum());
				if (BaseUtility.isObjectNotNull(existedStudentEvaluationEntity)) {
					addRow = false;
				}
			}
			
			if (addRow) {
				evaluationResponses.add(evaluationResponse);
			}
		}
		
		return evaluationResponses;
	}

	@Override
	public EvaluationResponse getEvaluationByEvaluationId(String evaluationId) {
		EvaluationResponse evaluationResponse = new EvaluationResponse();
		EvaluationEntity evaluationEntity = evaluationRepository.findByEvaluationId(evaluationId);
		
		if (BaseUtility.isObjectNotNull(evaluationEntity)) {
			evaluationResponse.setEvaluationId(evaluationEntity.getEvaluationId());
			evaluationResponse.setEvaluationName(evaluationEntity.getEvaluationName());
			evaluationResponse.setEvaluationCategory(evaluationEntity.getEvaluationCategory());
			evaluationResponse.setEvaluationSubject(evaluationEntity.getEvaluationSubject());
		
			return evaluationResponse;
		} else {
			return null;
		}
	}

	@Override
	public EvaluationResponse insertEvaluation(EvaluationRequest evaluationRequest) throws Exception {
		EvaluationResponse evaluationResponse = new EvaluationResponse();
		EvaluationEntity newEvaluationEntity = new EvaluationEntity();
		
		newEvaluationEntity.setEvaluationId(BaseUtility.generateId());
		newEvaluationEntity.setEvaluationName(evaluationRequest.getEvaluationName());
		newEvaluationEntity.setEvaluationCategory(evaluationRequest.getEvaluationCategory());
		newEvaluationEntity.setEvaluationSubject(evaluationRequest.getEvaluationSubject());
		
		EvaluationEntity insertedEvaluationEntity = evaluationRepository.save(newEvaluationEntity);
		
		if (BaseUtility.isObjectNotNull(insertedEvaluationEntity)) {
			evaluationResponse.setEvaluationId(insertedEvaluationEntity.getEvaluationId());
			evaluationResponse.setEvaluationName(insertedEvaluationEntity.getEvaluationName());
			evaluationResponse.setEvaluationCategory(insertedEvaluationEntity.getEvaluationCategory());
			evaluationResponse.setEvaluationSubject(insertedEvaluationEntity.getEvaluationSubject());
		} else {
			throw new Exception();
		}
		
		return evaluationResponse;
	}

	@Override
	public EvaluationResponse updateEvaluation(EvaluationRequest evaluationRequest) throws Exception {
		EvaluationResponse evaluationResponse = new EvaluationResponse();
		EvaluationEntity existedEvaluationEntity = evaluationRepository.findByEvaluationId(evaluationRequest.getEvaluationId());
		
		if (BaseUtility.isObjectNotNull(existedEvaluationEntity)) {
			existedEvaluationEntity.setEvaluationName(evaluationRequest.getEvaluationName());
			existedEvaluationEntity.setEvaluationCategory(evaluationRequest.getEvaluationCategory());
			existedEvaluationEntity.setEvaluationSubject(evaluationRequest.getEvaluationSubject());
			
			EvaluationEntity updatedEvaluationEntity = evaluationRepository.save(existedEvaluationEntity);
			
			if (BaseUtility.isObjectNotNull(updatedEvaluationEntity)) {
				evaluationResponse.setEvaluationId(updatedEvaluationEntity.getEvaluationId());
				evaluationResponse.setEvaluationName(updatedEvaluationEntity.getEvaluationName());
				evaluationResponse.setEvaluationCategory(updatedEvaluationEntity.getEvaluationCategory());
				evaluationResponse.setEvaluationSubject(updatedEvaluationEntity.getEvaluationSubject());
			} else {
				throw new Exception();
			}
			
			return evaluationResponse;
		} else {
			return null;
		}
	}

	@Transactional
	public Boolean deleteEvaluationByEvaluationId(String evaluationId) {
		Integer totalEvaluationDeleted = 0;
		
		try {
			totalEvaluationDeleted = evaluationRepository.deleteByEvaluationId(evaluationId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalEvaluationDeleted > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<ApplicationResponse> getApplications() {
		List<ApplicationResponse> applicationResponses = new ArrayList<ApplicationResponse>();
		List<ApplicationEntity> applicationEntities = applicationRepository.findAll();
		
		for (ApplicationEntity applicationEntity : applicationEntities) {
			ApplicationResponse applicationResponse = new ApplicationResponse();
			
			applicationResponse.setApplicationId(applicationEntity.getApplicationId());
			applicationResponse.setApplicationDate(DateUtility.convertToLocalDateTime(applicationEntity.getApplicationDate()));
			applicationResponse.setApplicationCompStatus(applicationEntity.getApplicationCompStatus());
			applicationResponse.setApplicationStudStatus(applicationEntity.getApplicationStudStatus());
			applicationResponse.setStudentMatricNum(applicationEntity.getStudentMatricNum());
			applicationResponse.setCompanyId(applicationEntity.getCompanyId());
			
			applicationResponses.add(applicationResponse);
		}
		
		return applicationResponses;
	}

	@Override
	public List<ApplicationResponse> filterApplications(ApplicationRequest applicationRequest) {
		List<ApplicationResponse> applicationResponses = new ArrayList<ApplicationResponse>();
		List<ApplicationEntity> applicationEntities = applicationRepository.findAll();
		
		for (ApplicationEntity applicationEntity : applicationEntities) {
			ApplicationResponse applicationResponse = new ApplicationResponse();
			
			applicationResponse.setApplicationId(applicationEntity.getApplicationId());
			applicationResponse.setApplicationDate(DateUtility.convertToLocalDateTime(applicationEntity.getApplicationDate()));
			applicationResponse.setApplicationCompStatus(applicationEntity.getApplicationCompStatus());
			applicationResponse.setApplicationStudStatus(applicationEntity.getApplicationStudStatus());
			applicationResponse.setStudentMatricNum(applicationEntity.getStudentMatricNum());
			applicationResponse.setCompanyId(applicationEntity.getCompanyId());
			
			if (BaseUtility.isNotBlank(applicationEntity.getCompanyId())) {
				CompanyEntity companyEntity = companyRepository.findByCompanyId(applicationEntity.getCompanyId());
				
				if (BaseUtility.isObjectNotNull(companyEntity)) {
					CompanyResponse companyResponse = new CompanyResponse();

					companyResponse.setCompanyId(companyEntity.getCompanyId());
					companyResponse.setCompanyName(companyEntity.getCompanyName());
					companyResponse.setCompanyAddress(companyEntity.getCompanyAddress());
					companyResponse.setCompanyPhone(companyEntity.getCompanyPhone());
					companyResponse.setCompanyEmail(companyEntity.getCompanyEmail());
					companyResponse.setCompanyWebsite(companyEntity.getCompanyWebsite());
					companyResponse.setCompanyBrochure(companyEntity.getCompanyBrochure());
					companyResponse.setCompanyBrochureFileName(companyEntity.getCompanyBrochureFileName());
					companyResponse.setCompanyHrName(companyEntity.getCompanyHrName());
					companyResponse.setCompanyHrPhone(companyEntity.getCompanyHrPhone());
					companyResponse.setCompanyHrEmail(companyEntity.getCompanyHrEmail());
					companyResponse.setCompanyHrGender(companyEntity.getCompanyHrGender());

					applicationResponse.setCompany(companyResponse);
				}
			}
			
			Boolean addRow = true;
			
			if (BaseUtility.isNotBlank(applicationRequest.getApplicationCompStatus()) && !applicationRequest.getApplicationCompStatus().equals(applicationEntity.getApplicationCompStatus())) {
				addRow = false;
			}
			if (BaseUtility.isNotBlank(applicationRequest.getApplicationStudStatus()) && !applicationRequest.getApplicationStudStatus().equals(applicationEntity.getApplicationStudStatus())) {
				addRow = false;
			}
			if (BaseUtility.isNotBlank(applicationRequest.getStudentMatricNum()) && !applicationRequest.getStudentMatricNum().equals(applicationEntity.getStudentMatricNum())) {
				addRow = false;
			}
			
			if (addRow) {
				applicationResponses.add(applicationResponse);
			}
		}
		
		return applicationResponses;
	}


	@Override
	public List<CompanyResponse> getCompaniesByStudentApplication(Boolean applyStatus, String studentMatricNum) {
		List<CompanyResponse> companyResponses = new ArrayList<CompanyResponse>();
		
		List<ApplicationEntity> applicationEntities = applicationRepository.findByStudentMatricNum(studentMatricNum);
		List<CompanyEntity> companyEntities = companyRepository.findAll();

		Boolean applyBtnStatus = true;
		// determine if student still eligible to apply
		if (applicationEntities.size() >= 3) {
			applyBtnStatus = false;
			
			for (ApplicationEntity applicationEntity : applicationEntities) {
				if (applicationEntity.getApplicationCompStatus().equals("REJ")) {
					applyBtnStatus = true;
				}
			}
		}
		
		// list of company ID applied by student
		List<String> appliedCompaniesId = new ArrayList<String>();
		for (ApplicationEntity applicationEntity : applicationEntities) {
			appliedCompaniesId.add(applicationEntity.getCompanyId());
		}
		
		// list of all company ID
		List<String> companiesId = new ArrayList<String>();
		for (CompanyEntity companyEntity : companyEntities) {
			companiesId.add(companyEntity.getCompanyId());
		}
		
		if (applyStatus) {
			// retain all company ID applied by student
			// get list of company ID that had been applied
			companiesId.retainAll(appliedCompaniesId);
		} else {
			// remove all company ID applied by student
			// get list of company ID that had not been applied
			companiesId.removeAll(appliedCompaniesId);
		}
		
		for (String applicationCompanyId : companiesId) {
			CompanyEntity companyEntity = companyRepository.findByCompanyId(applicationCompanyId);
			
			if (BaseUtility.isObjectNotNull(companyEntity)) {
				CompanyResponse companyResponse = new CompanyResponse();

				companyResponse.setCompanyId(companyEntity.getCompanyId());
				companyResponse.setCompanyName(companyEntity.getCompanyName());
				companyResponse.setCompanyAddress(companyEntity.getCompanyAddress());
				companyResponse.setCompanyPhone(companyEntity.getCompanyPhone());
				companyResponse.setCompanyEmail(companyEntity.getCompanyEmail());
				companyResponse.setCompanyWebsite(companyEntity.getCompanyWebsite());
				companyResponse.setCompanyBrochure(companyEntity.getCompanyBrochure());
				companyResponse.setCompanyBrochureFileName(companyEntity.getCompanyBrochureFileName());
				companyResponse.setCompanyHrName(companyEntity.getCompanyHrName());
				companyResponse.setCompanyHrPhone(companyEntity.getCompanyHrPhone());
				companyResponse.setCompanyHrEmail(companyEntity.getCompanyHrEmail());
				companyResponse.setCompanyHrGender(companyEntity.getCompanyHrGender());
				companyResponse.setCompanyApplyBtn(applyBtnStatus);
				
				companyResponses.add(companyResponse);
			}
		}
		
		return companyResponses;
	}
	
	@Override
	public ApplicationResponse getApplicationByApplicationId(String applicationId) {
		ApplicationResponse applicationResponse = new ApplicationResponse();
		ApplicationEntity applicationEntity = applicationRepository.findByApplicationId(applicationId);
		
		if (BaseUtility.isObjectNotNull(applicationEntity)) {
			applicationResponse.setApplicationId(applicationEntity.getApplicationId());
			applicationResponse.setApplicationDate(DateUtility.convertToLocalDateTime(applicationEntity.getApplicationDate()));
			applicationResponse.setApplicationCompStatus(applicationEntity.getApplicationCompStatus());
			applicationResponse.setApplicationStudStatus(applicationEntity.getApplicationStudStatus());
			applicationResponse.setStudentMatricNum(applicationEntity.getStudentMatricNum());
			applicationResponse.setCompanyId(applicationEntity.getCompanyId());
		
			if (BaseUtility.isNotBlank(applicationEntity.getStudentMatricNum())) {
				StudentEntity studentEntity = studentRepository.findByStudentMatricNum(applicationEntity.getStudentMatricNum());
				
				if (BaseUtility.isObjectNotNull(studentEntity)) {
					StudentResponse studentResponse = new StudentResponse();
					
					studentResponse.setStudentMatricNum(studentEntity.getStudentMatricNum());
					studentResponse.setStudentName(studentEntity.getStudentName());
					studentResponse.setStudentAddress(studentEntity.getStudentAddress());
					studentResponse.setStudentEmail(studentEntity.getStudentEmail());
					studentResponse.setStudentPhone(studentEntity.getStudentPhone());
					studentResponse.setStudentPassword(studentEntity.getStudentPassword());
					studentResponse.setStudentCampus(studentEntity.getStudentCampus());
					studentResponse.setStudentCourse(studentEntity.getStudentCourse());
					studentResponse.setStudentClass(studentEntity.getStudentClass());
					studentResponse.setStudentCV(studentEntity.getStudentCV());
					studentResponse.setStudentCVFileName(studentEntity.getStudentCVFileName());
					studentResponse.setStudentMiniTranscript(studentEntity.getStudentMiniTranscript());
					studentResponse.setStudentMiniTranscriptFileName(studentEntity.getStudentMiniTranscriptFileName());
					studentResponse.setStudentCoverLetter(studentEntity.getStudentCoverLetter());
					studentResponse.setStudentCoverLetterFileName(studentEntity.getStudentCoverLetterFileName());
					studentResponse.setStudentCourseOutline(studentEntity.getStudentCourseOutline());
					studentResponse.setStudentCourseOutlineFileName(studentEntity.getStudentCourseOutlineFileName());
					studentResponse.setStudentSL(studentEntity.getStudentSL());
					studentResponse.setStudentSLFileName(studentEntity.getStudentSLFileName());
					studentResponse.setIndustrySvId(studentEntity.getIndustrySvId());
					studentResponse.setAcademicSvId(studentEntity.getAcademicSvId());
					
					applicationResponse.setStudent(studentResponse);
				}
			}
			
			if (BaseUtility.isNotBlank(applicationEntity.getCompanyId())) {
				CompanyEntity companyEntity = companyRepository.findByCompanyId(applicationEntity.getCompanyId());
				
				if (BaseUtility.isObjectNotNull(companyEntity)) {
					CompanyResponse companyResponse = new CompanyResponse();

					companyResponse.setCompanyId(companyEntity.getCompanyId());
					companyResponse.setCompanyName(companyEntity.getCompanyName());
					companyResponse.setCompanyAddress(companyEntity.getCompanyAddress());
					companyResponse.setCompanyPhone(companyEntity.getCompanyPhone());
					companyResponse.setCompanyEmail(companyEntity.getCompanyEmail());
					companyResponse.setCompanyWebsite(companyEntity.getCompanyWebsite());
					companyResponse.setCompanyBrochure(companyEntity.getCompanyBrochure());
					companyResponse.setCompanyBrochureFileName(companyEntity.getCompanyBrochureFileName());
					companyResponse.setCompanyHrName(companyEntity.getCompanyHrName());
					companyResponse.setCompanyHrPhone(companyEntity.getCompanyHrPhone());
					companyResponse.setCompanyHrEmail(companyEntity.getCompanyHrEmail());
					companyResponse.setCompanyHrGender(companyEntity.getCompanyHrGender());

					applicationResponse.setCompany(companyResponse);
				}
			}
			
			return applicationResponse;
		} else {
			return null;
		}
	}

	@Override
	public ApplicationResponse insertApplication(ApplicationRequest applicationRequest) throws Exception {
		ApplicationResponse applicationResponse = new ApplicationResponse();
		ApplicationEntity newApplicationEntity = new ApplicationEntity();
		
		newApplicationEntity.setApplicationId(BaseUtility.generateId());
		newApplicationEntity.setApplicationDate(DateUtility.asTimeStamp(LocalDateTime.now()));
		newApplicationEntity.setApplicationCompStatus("RVW");
		newApplicationEntity.setApplicationStudStatus("CMP");
		newApplicationEntity.setStudentMatricNum(applicationRequest.getStudentMatricNum());
		newApplicationEntity.setCompanyId(applicationRequest.getCompanyId());
		
		ApplicationEntity insertedApplicationEntity = applicationRepository.save(newApplicationEntity);
		
		if (BaseUtility.isObjectNotNull(insertedApplicationEntity)) {
			applicationResponse.setApplicationId(insertedApplicationEntity.getApplicationId());
			applicationResponse.setApplicationDate(DateUtility.convertToLocalDateTime(insertedApplicationEntity.getApplicationDate()));
			applicationResponse.setApplicationCompStatus(insertedApplicationEntity.getApplicationCompStatus());
			applicationResponse.setApplicationStudStatus(insertedApplicationEntity.getApplicationStudStatus());
			applicationResponse.setStudentMatricNum(insertedApplicationEntity.getStudentMatricNum());
			applicationResponse.setCompanyId(insertedApplicationEntity.getCompanyId());
			
			CompanyEntity existedCompanyEntity = companyRepository.findByCompanyId(insertedApplicationEntity.getCompanyId());
			
			sendEmail(existedCompanyEntity.getCompanyHrEmail(), insertedApplicationEntity.getApplicationId());
		} else {
			throw new Exception();
		}
		
		return applicationResponse;
	}

	@Override
	public ApplicationResponse updateApplication(ApplicationRequest applicationRequest) throws Exception {
		ApplicationResponse applicationResponse = new ApplicationResponse();
		ApplicationEntity existedApplicationEntity = applicationRepository.findByApplicationId(applicationRequest.getApplicationId());
		
		if (BaseUtility.isObjectNotNull(existedApplicationEntity)) {
			if (BaseUtility.isNotBlank(applicationRequest.getApplicationCompStatus())) {
				existedApplicationEntity.setApplicationCompStatus(applicationRequest.getApplicationCompStatus());
			}
			if (BaseUtility.isNotBlank(applicationRequest.getApplicationStudStatus())) {
				existedApplicationEntity.setApplicationStudStatus(applicationRequest.getApplicationStudStatus());
			}
			
			ApplicationEntity updatedApplicationEntity = applicationRepository.save(existedApplicationEntity);
			
			if (BaseUtility.isObjectNotNull(updatedApplicationEntity)) {
				applicationResponse.setApplicationId(updatedApplicationEntity.getApplicationId());
				applicationResponse.setApplicationDate(DateUtility.convertToLocalDateTime(updatedApplicationEntity.getApplicationDate()));
				applicationResponse.setApplicationCompStatus(updatedApplicationEntity.getApplicationCompStatus());
				applicationResponse.setApplicationStudStatus(updatedApplicationEntity.getApplicationStudStatus());
				applicationResponse.setStudentMatricNum(updatedApplicationEntity.getStudentMatricNum());
				applicationResponse.setCompanyId(updatedApplicationEntity.getCompanyId());
			} else {
				throw new Exception();
			}
			
			return applicationResponse;
		} else {
			return null;
		}
	}

	@Transactional
	public Boolean deleteApplicationByApplicationId(String applicationId) {
		Integer totalApplicationDeleted = 0;
		
		try {
			totalApplicationDeleted = applicationRepository.deleteByApplicationId(applicationId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalApplicationDeleted > 0) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<StudentEvaluationResponse> filterStudentEvaluations(StudentEvaluationRequest studentEvaluationRequest) {
		List<StudentEvaluationResponse> studentEvaluationResponses = new ArrayList<StudentEvaluationResponse>();
		List<StudentEvaluationEntity> existedStudentEvaluationEntities = studentEvaluationRepository.findAll();
		
		for (StudentEvaluationEntity existedStudentEvaluationEntity : existedStudentEvaluationEntities) {
			StudentEvaluationResponse studentEvaluationResponse = new StudentEvaluationResponse();
			
			studentEvaluationResponse.setStudentEvaluationId(existedStudentEvaluationEntity.getStudentEvaluationId());
			studentEvaluationResponse.setStudentEvaluationDate(DateUtility.convertToLocalDateTime(existedStudentEvaluationEntity.getStudentEvaluationDate()));
			studentEvaluationResponse.setStudentEvaluationStartDate(DateUtility.convertToLocalDateTime(existedStudentEvaluationEntity.getStudentEvaluationStartDate()));
			studentEvaluationResponse.setStudentEvaluationEndDate(DateUtility.convertToLocalDateTime(existedStudentEvaluationEntity.getStudentEvaluationEndDate()));
			studentEvaluationResponse.setStudentEvaluationStatus(existedStudentEvaluationEntity.getStudentEvaluationStatus());
			
			studentEvaluationResponse.setStudentEvaluationAttachFileName(existedStudentEvaluationEntity.getStudentEvaluationAttachFileName());
			studentEvaluationResponse.setStudentEvaluationAttach(existedStudentEvaluationEntity.getStudentEvaluationAttach());
			studentEvaluationResponse.setStudentEvaluationAttachDate(DateUtility.convertToLocalDateTime(existedStudentEvaluationEntity.getStudentEvaluationAttachDate()));

			studentEvaluationResponse.setStudentEvaluationComment(existedStudentEvaluationEntity.getStudentEvaluationComment());
			studentEvaluationResponse.setStudentEvaluationTotalScore(existedStudentEvaluationEntity.getStudentEvaluationTotalScore());
			studentEvaluationResponse.setEvaluationId(existedStudentEvaluationEntity.getEvaluationId());
			studentEvaluationResponse.setStudentMatricNum(existedStudentEvaluationEntity.getStudentMatricNum());
			studentEvaluationResponse.setAcademicSvId(existedStudentEvaluationEntity.getAcademicSvId());
			studentEvaluationResponse.setIndustrySvId(existedStudentEvaluationEntity.getIndustrySvId());
			
			if (BaseUtility.isNotBlank(existedStudentEvaluationEntity.getEvaluationId())) {
				EvaluationEntity existedEvaluationEntity = evaluationRepository.findByEvaluationId(existedStudentEvaluationEntity.getEvaluationId());
				
				if (BaseUtility.isObjectNotNull(existedEvaluationEntity)) {
					EvaluationResponse evaluationResponse = new EvaluationResponse();
					
					evaluationResponse.setEvaluationId(existedEvaluationEntity.getEvaluationId());
					evaluationResponse.setEvaluationName(existedEvaluationEntity.getEvaluationName());
					evaluationResponse.setEvaluationCategory(existedEvaluationEntity.getEvaluationCategory());
					evaluationResponse.setEvaluationSubject(existedEvaluationEntity.getEvaluationSubject());
					
					studentEvaluationResponse.setEvaluation(evaluationResponse);
				}
			}
			
			Boolean addRow = true;
			
			if (BaseUtility.isNotBlank(studentEvaluationRequest.getStudentEvaluationStatus()) && !studentEvaluationRequest.getStudentEvaluationStatus().equals(existedStudentEvaluationEntity.getStudentEvaluationStatus())) {
				addRow = false;
			}
			if (BaseUtility.isNotBlank(studentEvaluationRequest.getStudentMatricNum()) && !studentEvaluationRequest.getStudentMatricNum().equals(existedStudentEvaluationEntity.getStudentMatricNum())) {
				addRow = false;
			}
			if (BaseUtility.isNotBlank(studentEvaluationRequest.getAcademicSvId()) && !studentEvaluationRequest.getAcademicSvId().equals(existedStudentEvaluationEntity.getAcademicSvId())) {
				addRow = false;
			}
			if (BaseUtility.isNotBlank(studentEvaluationRequest.getIndustrySvId()) && !studentEvaluationRequest.getIndustrySvId().equals(existedStudentEvaluationEntity.getIndustrySvId())) {
				addRow = false;
			}
			if (BaseUtility.isNotBlank(studentEvaluationRequest.getUserLoginType()) && !studentEvaluationRequest.getUserLoginType().equals(studentEvaluationResponse.getEvaluation().getEvaluationCategory())) {
				addRow = false;
			}
			
			if (addRow) {
				studentEvaluationResponses.add(studentEvaluationResponse);
			}
		}
		
		return studentEvaluationResponses;
	}

	@Override
	public StudentEvaluationResponse insertStudentEvaluation(StudentEvaluationRequest studentEvaluationRequest, MultipartFile attachFile) throws Exception {
		StudentEvaluationResponse studentEvaluationResponse = new StudentEvaluationResponse();
		StudentEvaluationEntity newStudentEvaluationEntity = new StudentEvaluationEntity();
		
		newStudentEvaluationEntity.setStudentEvaluationId(BaseUtility.generateId());
		newStudentEvaluationEntity.setStudentEvaluationDate(DateUtility.asTimeStamp(studentEvaluationRequest.getStudentEvaluationDate()));
		newStudentEvaluationEntity.setStudentEvaluationStartDate(DateUtility.asTimeStamp(studentEvaluationRequest.getStudentEvaluationStartDate()));
		newStudentEvaluationEntity.setStudentEvaluationEndDate(DateUtility.asTimeStamp(studentEvaluationRequest.getStudentEvaluationEndDate()));
		newStudentEvaluationEntity.setStudentEvaluationStatus(studentEvaluationRequest.getStudentEvaluationStatus());
		if (attachFile != null) {
			newStudentEvaluationEntity.setStudentEvaluationAttachFileName(attachFile.getOriginalFilename());
			newStudentEvaluationEntity.setStudentEvaluationAttach(attachFile.getBytes());
			newStudentEvaluationEntity.setStudentEvaluationAttachDate(DateUtility.asTimeStamp(studentEvaluationRequest.getStudentEvaluationAttachDate()));
		}
		newStudentEvaluationEntity.setStudentEvaluationComment(studentEvaluationRequest.getStudentEvaluationComment());
		newStudentEvaluationEntity.setStudentEvaluationTotalScore(studentEvaluationRequest.getStudentEvaluationTotalScore());
		newStudentEvaluationEntity.setEvaluationId(studentEvaluationRequest.getEvaluationId());
		newStudentEvaluationEntity.setStudentMatricNum(studentEvaluationRequest.getStudentMatricNum());
		newStudentEvaluationEntity.setAcademicSvId(studentEvaluationRequest.getAcademicSvId());
		newStudentEvaluationEntity.setIndustrySvId(studentEvaluationRequest.getIndustrySvId());
		
		StudentEvaluationEntity insertedEvaluationEntity = studentEvaluationRepository.save(newStudentEvaluationEntity);
		
		if (BaseUtility.isObjectNotNull(insertedEvaluationEntity)) {
			studentEvaluationResponse.setStudentEvaluationId(insertedEvaluationEntity.getStudentEvaluationId());
			studentEvaluationResponse.setStudentEvaluationDate(DateUtility.convertToLocalDateTime(insertedEvaluationEntity.getStudentEvaluationDate()));
			studentEvaluationResponse.setStudentEvaluationStartDate(DateUtility.convertToLocalDateTime(insertedEvaluationEntity.getStudentEvaluationStartDate()));
			studentEvaluationResponse.setStudentEvaluationEndDate(DateUtility.convertToLocalDateTime(insertedEvaluationEntity.getStudentEvaluationEndDate()));
			studentEvaluationResponse.setStudentEvaluationStatus(insertedEvaluationEntity.getStudentEvaluationStatus());
			
			studentEvaluationResponse.setStudentEvaluationAttachFileName(insertedEvaluationEntity.getStudentEvaluationAttachFileName());
			studentEvaluationResponse.setStudentEvaluationAttach(insertedEvaluationEntity.getStudentEvaluationAttach());
			studentEvaluationResponse.setStudentEvaluationAttachDate(DateUtility.convertToLocalDateTime(insertedEvaluationEntity.getStudentEvaluationAttachDate()));

			studentEvaluationResponse.setStudentEvaluationComment(insertedEvaluationEntity.getStudentEvaluationComment());
			studentEvaluationResponse.setStudentEvaluationTotalScore(insertedEvaluationEntity.getStudentEvaluationTotalScore());
			studentEvaluationResponse.setEvaluationId(insertedEvaluationEntity.getEvaluationId());
			studentEvaluationResponse.setStudentMatricNum(insertedEvaluationEntity.getStudentMatricNum());
			studentEvaluationResponse.setAcademicSvId(insertedEvaluationEntity.getAcademicSvId());
			studentEvaluationResponse.setIndustrySvId(insertedEvaluationEntity.getIndustrySvId());
			
			if (BaseUtility.isNotBlank(insertedEvaluationEntity.getEvaluationId())) {
				EvaluationEntity existedEvaluationEntity = evaluationRepository.findByEvaluationId(insertedEvaluationEntity.getEvaluationId());
				
				if (BaseUtility.isObjectNotNull(existedEvaluationEntity)) {
					EvaluationResponse evaluationResponse = new EvaluationResponse();
					
					evaluationResponse.setEvaluationId(existedEvaluationEntity.getEvaluationId());
					evaluationResponse.setEvaluationName(existedEvaluationEntity.getEvaluationName());
					evaluationResponse.setEvaluationCategory(existedEvaluationEntity.getEvaluationCategory());
					evaluationResponse.setEvaluationSubject(existedEvaluationEntity.getEvaluationSubject());
					
					studentEvaluationResponse.setEvaluation(evaluationResponse);
				}
			}
		} else {
			throw new Exception();
		}
		
		return studentEvaluationResponse;
	}

	@Override
	public StudentEvaluationResponse updateStudentEvaluation(StudentEvaluationRequest studentEvaluationRequest, MultipartFile attachFile) throws Exception {
		StudentEvaluationResponse studentEvaluationResponse = new StudentEvaluationResponse();
		StudentEvaluationEntity existedStudentEvaluationEntity = studentEvaluationRepository.findByStudentEvaluationId(studentEvaluationRequest.getStudentEvaluationId());
		
		if (BaseUtility.isObjectNotNull(existedStudentEvaluationEntity)) {
			if (studentEvaluationRequest.getStudentEvaluationDate() != null) {
				existedStudentEvaluationEntity.setStudentEvaluationDate(DateUtility.asTimeStamp(studentEvaluationRequest.getStudentEvaluationDate()));
			}
			if (BaseUtility.isNotBlank(studentEvaluationRequest.getStudentEvaluationStatus())) {
				existedStudentEvaluationEntity.setStudentEvaluationStatus(studentEvaluationRequest.getStudentEvaluationStatus());
			}
			if (BaseUtility.isNotBlank(studentEvaluationRequest.getStudentEvaluationComment())) {
				existedStudentEvaluationEntity.setStudentEvaluationComment(studentEvaluationRequest.getStudentEvaluationComment());
			}
			if (BaseUtility.isNotBlank(studentEvaluationRequest.getStudentEvaluationTotalScore().toString())) {
				existedStudentEvaluationEntity.setStudentEvaluationTotalScore(studentEvaluationRequest.getStudentEvaluationTotalScore());
			}
			if (BaseUtility.isNotBlank(studentEvaluationRequest.getAcademicSvId())) {
				existedStudentEvaluationEntity.setAcademicSvId(studentEvaluationRequest.getAcademicSvId());
			}
			if (BaseUtility.isNotBlank(studentEvaluationRequest.getIndustrySvId())) {
				existedStudentEvaluationEntity.setIndustrySvId(studentEvaluationRequest.getIndustrySvId());
			}
			if (!attachFile.isEmpty()) {
				existedStudentEvaluationEntity.setStudentEvaluationAttach(attachFile.getBytes());
				existedStudentEvaluationEntity.setStudentEvaluationAttachFileName(attachFile.getOriginalFilename());
				existedStudentEvaluationEntity.setStudentEvaluationAttachDate(DateUtility.asTimeStamp(LocalDateTime.now()));
			}
			
			StudentEvaluationEntity updatedStudentEvaluationEntity = studentEvaluationRepository.save(existedStudentEvaluationEntity);
			
			if (BaseUtility.isObjectNotNull(updatedStudentEvaluationEntity)) {
				studentEvaluationResponse.setStudentEvaluationId(updatedStudentEvaluationEntity.getStudentEvaluationId());
				studentEvaluationResponse.setStudentEvaluationDate(DateUtility.convertToLocalDateTime(updatedStudentEvaluationEntity.getStudentEvaluationDate()));
				studentEvaluationResponse.setStudentEvaluationStartDate(DateUtility.convertToLocalDateTime(updatedStudentEvaluationEntity.getStudentEvaluationStartDate()));
				studentEvaluationResponse.setStudentEvaluationEndDate(DateUtility.convertToLocalDateTime(updatedStudentEvaluationEntity.getStudentEvaluationEndDate()));
				studentEvaluationResponse.setStudentEvaluationStatus(updatedStudentEvaluationEntity.getStudentEvaluationStatus());
				
				studentEvaluationResponse.setStudentEvaluationAttachFileName(updatedStudentEvaluationEntity.getStudentEvaluationAttachFileName());
				studentEvaluationResponse.setStudentEvaluationAttach(updatedStudentEvaluationEntity.getStudentEvaluationAttach());
				studentEvaluationResponse.setStudentEvaluationAttachDate(DateUtility.convertToLocalDateTime(updatedStudentEvaluationEntity.getStudentEvaluationAttachDate()));

				studentEvaluationResponse.setStudentEvaluationComment(updatedStudentEvaluationEntity.getStudentEvaluationComment());
				studentEvaluationResponse.setStudentEvaluationTotalScore(updatedStudentEvaluationEntity.getStudentEvaluationTotalScore());
				studentEvaluationResponse.setEvaluationId(updatedStudentEvaluationEntity.getEvaluationId());
				studentEvaluationResponse.setStudentMatricNum(updatedStudentEvaluationEntity.getStudentMatricNum());
				studentEvaluationResponse.setAcademicSvId(updatedStudentEvaluationEntity.getAcademicSvId());
				studentEvaluationResponse.setIndustrySvId(updatedStudentEvaluationEntity.getIndustrySvId());
				
				if (BaseUtility.isNotBlank(updatedStudentEvaluationEntity.getEvaluationId())) {
					EvaluationEntity existedEvaluationEntity = evaluationRepository.findByEvaluationId(updatedStudentEvaluationEntity.getEvaluationId());
					
					if (BaseUtility.isObjectNotNull(existedEvaluationEntity)) {
						EvaluationResponse evaluationResponse = new EvaluationResponse();
						
						evaluationResponse.setEvaluationId(existedEvaluationEntity.getEvaluationId());
						evaluationResponse.setEvaluationName(existedEvaluationEntity.getEvaluationName());
						evaluationResponse.setEvaluationCategory(existedEvaluationEntity.getEvaluationCategory());
						evaluationResponse.setEvaluationSubject(existedEvaluationEntity.getEvaluationSubject());
						
						studentEvaluationResponse.setEvaluation(evaluationResponse);
					}
				}
			} else {
				throw new Exception();
			}
			
			return studentEvaluationResponse;
		} else {
			return null;
		}
	}

	@Transactional
	public Boolean deleteStudentEvaluation(String studentEvaluationId) {
		Integer totalStudentEvaluationDeleted = 0;
		
		try {
			totalStudentEvaluationDeleted = studentEvaluationRepository.deleteByStudentEvaluationId(studentEvaluationId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalStudentEvaluationDeleted > 0) {
			return true;
		}
		
		return false;
	}

	@Transactional
	public Boolean updateStudentEvaluations(String studentMatricNum, List<String> evaluationIds) {
		Boolean updateStatus = true;
		
		try {
			List<StudentEvaluationEntity> existedStudentEvaluationEntities = studentEvaluationRepository.findByStudentMatricNum(studentMatricNum);
			List<String> existedEvaluationIds = new ArrayList<String>();
			
			if (existedStudentEvaluationEntities.size() > 0) {
				for (StudentEvaluationEntity existedStudentEvaluationEntity : existedStudentEvaluationEntities) {
					existedEvaluationIds.add(existedStudentEvaluationEntity.getEvaluationId());
				}
			}
			
			Set<String> evaluationIdSet = evaluationIds.stream().collect(Collectors.toSet());
			Set<String> existedEvaluationIdSet = existedEvaluationIds.stream().collect(Collectors.toSet());
			
//			SetView<String> intersectEvaluationIdSet = Sets.intersection(evaluationIdSet, existedEvaluationIdSet);
//			List<String> intersectEvaluationIds = new ArrayList<>(intersectEvaluationIdSet);
			
			SetView<String> differenceSendEvaluationIdSet = Sets.difference(evaluationIdSet, existedEvaluationIdSet);
			List<String> differenceSendEvaluationIds = new ArrayList<>(differenceSendEvaluationIdSet);
			// insert
			if (differenceSendEvaluationIds.size() > 0) {
				for (String differenceSendEvaluationId : differenceSendEvaluationIds) {
					StudentEvaluationEntity newStudentEvaluationEntity = new StudentEvaluationEntity();
					
					newStudentEvaluationEntity.setStudentEvaluationId(BaseUtility.generateId());
					newStudentEvaluationEntity.setStudentEvaluationStatus("INC");
					newStudentEvaluationEntity.setEvaluationId(differenceSendEvaluationId);
					newStudentEvaluationEntity.setStudentMatricNum(studentMatricNum);
					
					studentEvaluationRepository.save(newStudentEvaluationEntity);
				}
			}
			
			SetView<String> differenceSaveEvaluationIdSet = Sets.difference(existedEvaluationIdSet, evaluationIdSet);
			List<String> differenceSaveEvaluationIds = new ArrayList<>(differenceSaveEvaluationIdSet);
			// delete
			if (differenceSaveEvaluationIds.size() > 0) {
				for (String differenceSaveEvaluationId : differenceSaveEvaluationIds) {
					studentEvaluationRepository.deleteByEvaluationIdAndStudentMatricNum(differenceSaveEvaluationId, studentMatricNum);
				}
			}
		} catch (Exception e) {
			updateStatus = false;
		}

		return updateStatus;
	}
	
	public Boolean sendEmail(String to, String applicationId) {
		Boolean emailSendStatus = false;
		String htmlString = null;
		
		htmlString = "<!DOCTYPE html> <html> <body>  <a href=\"http://localhost:4200/company-approval/" + applicationId + "\">Visit</a> </body> </html> ";
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setFrom("noreply@khaxrxldev.com");
			mimeMessageHelper.setSubject("Internship Application: ");
			mimeMessageHelper.setText(htmlString, true);
			
			javaMailSender.send(mimeMessage);
			emailSendStatus = true;
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return emailSendStatus;
	}

	@Override
	public List<CriteriaResponse> getCriterias() {
		List<CriteriaResponse> criteriaResponses = new ArrayList<CriteriaResponse>();
		List<CriteriaEntity> criteriaEntities = criteriaRepository.findAll();
		
		for (CriteriaEntity criteriaEntity : criteriaEntities) {
			CriteriaResponse criteriaResponse = new CriteriaResponse();
			
			criteriaResponse.setCriteriaId(criteriaEntity.getCriteriaId());
			criteriaResponse.setCriteriaName(criteriaEntity.getCriteriaName());
			criteriaResponse.setCriteriaDesc(criteriaEntity.getCriteriaDesc());
			criteriaResponse.setCriteriaPercentage(criteriaEntity.getCriteriaPercentage());
			criteriaResponse.setEvaluationId(criteriaEntity.getEvaluationId());
			
			criteriaResponses.add(criteriaResponse);
		}
		
		return criteriaResponses;
	}

	@Override
	public List<CriteriaResponse> filterCriterias(CriteriaRequest criteriaRequest) {
		List<CriteriaResponse> criteriaResponses = new ArrayList<CriteriaResponse>();
		List<CriteriaEntity> criteriaEntities = criteriaRepository.findAll();

		for (CriteriaEntity criteriaEntity : criteriaEntities) {
			CriteriaResponse criteriaResponse = new CriteriaResponse();

			criteriaResponse.setCriteriaId(criteriaEntity.getCriteriaId());
			criteriaResponse.setCriteriaName(criteriaEntity.getCriteriaName());
			criteriaResponse.setCriteriaDesc(criteriaEntity.getCriteriaDesc());
			criteriaResponse.setCriteriaPercentage(criteriaEntity.getCriteriaPercentage());
			criteriaResponse.setEvaluationId(criteriaEntity.getEvaluationId());
			
			Boolean addRow = true;
			
			if (BaseUtility.isNotBlank(criteriaRequest.getEvaluationId()) && !criteriaRequest.getEvaluationId().equals(criteriaEntity.getEvaluationId())) {
				addRow = false;
			}
			
			if (addRow) {
				criteriaResponses.add(criteriaResponse);
			}
		}
		
		return criteriaResponses;
	}

	@Override
	public CriteriaResponse getCriteriaByCriteriaId(String criteriaId) {
		CriteriaResponse criteriaResponse = new CriteriaResponse();
		CriteriaEntity criteriaEntity = criteriaRepository.findByCriteriaId(criteriaId);
		
		if (BaseUtility.isObjectNotNull(criteriaEntity)) {
			criteriaResponse.setCriteriaId(criteriaEntity.getCriteriaId());
			criteriaResponse.setCriteriaName(criteriaEntity.getCriteriaName());
			criteriaResponse.setCriteriaDesc(criteriaEntity.getCriteriaDesc());
			criteriaResponse.setCriteriaPercentage(criteriaEntity.getCriteriaPercentage());
			criteriaResponse.setEvaluationId(criteriaEntity.getEvaluationId());
		
			return criteriaResponse;
		} else {
			return null;
		}
	}

	@Override
	public CriteriaResponse insertCriteria(CriteriaRequest criteriaRequest) throws Exception {
		CriteriaResponse criteriaResponse = new CriteriaResponse();
		CriteriaEntity newCriteriaEntity = new CriteriaEntity();
		
		newCriteriaEntity.setCriteriaId(BaseUtility.generateId());
		newCriteriaEntity.setCriteriaName(criteriaRequest.getCriteriaName());
		newCriteriaEntity.setCriteriaDesc(criteriaRequest.getCriteriaDesc());
		newCriteriaEntity.setCriteriaPercentage(criteriaRequest.getCriteriaPercentage());
		newCriteriaEntity.setEvaluationId(criteriaRequest.getEvaluationId());
		
		CriteriaEntity insertedCriteriaEntity = criteriaRepository.save(newCriteriaEntity);
		
		if (BaseUtility.isObjectNotNull(insertedCriteriaEntity)) {
			criteriaResponse.setCriteriaId(insertedCriteriaEntity.getCriteriaId());
			criteriaResponse.setCriteriaName(insertedCriteriaEntity.getCriteriaName());
			criteriaResponse.setCriteriaDesc(insertedCriteriaEntity.getCriteriaDesc());
			criteriaResponse.setCriteriaPercentage(insertedCriteriaEntity.getCriteriaPercentage());
			criteriaResponse.setEvaluationId(insertedCriteriaEntity.getEvaluationId());
		} else {
			throw new Exception();
		}
		
		return criteriaResponse;
	}

	@Override
	public CriteriaResponse updateCriteria(CriteriaRequest criteriaRequest) throws Exception {
		CriteriaResponse criteriaResponse = new CriteriaResponse();
		CriteriaEntity existedCriteriaEntity = criteriaRepository.findByCriteriaId(criteriaRequest.getCriteriaId());
		
		if (BaseUtility.isObjectNotNull(existedCriteriaEntity)) {
			existedCriteriaEntity.setCriteriaName(criteriaRequest.getCriteriaName());
			existedCriteriaEntity.setCriteriaDesc(criteriaRequest.getCriteriaDesc());
			existedCriteriaEntity.setCriteriaPercentage(criteriaRequest.getCriteriaPercentage());
			existedCriteriaEntity.setEvaluationId(criteriaRequest.getEvaluationId());
			
			CriteriaEntity updatedCriteriaEntity = criteriaRepository.save(existedCriteriaEntity);
			
			if (BaseUtility.isObjectNotNull(updatedCriteriaEntity)) {
				criteriaResponse.setCriteriaId(updatedCriteriaEntity.getCriteriaId());
				criteriaResponse.setCriteriaName(updatedCriteriaEntity.getCriteriaName());
				criteriaResponse.setCriteriaDesc(updatedCriteriaEntity.getCriteriaDesc());
				criteriaResponse.setCriteriaPercentage(updatedCriteriaEntity.getCriteriaPercentage());
				criteriaResponse.setEvaluationId(updatedCriteriaEntity.getEvaluationId());
			} else {
				throw new Exception();
			}
			
			return criteriaResponse;
		} else {
			return null;
		}
	}

	@Transactional
	public Boolean deleteCriteriaByCriteriaId(String criteriaId) {
		Integer totalCriteriaDeleted = 0;
		
		try {
			totalCriteriaDeleted = criteriaRepository.deleteByCriteriaId(criteriaId);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		if (totalCriteriaDeleted > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public EvaluationCriteriasResponse getCompleteEvaluation(String evaluationId, String studentEvaluationId) {
		EvaluationCriteriasResponse evaluationCriteriasResponse = new EvaluationCriteriasResponse();
		
		EvaluationEntity existedEvaluationEntity = evaluationRepository.findByEvaluationId(evaluationId);
		if (BaseUtility.isObjectNotNull(existedEvaluationEntity)) {
			EvaluationResponse evaluationResponse = new EvaluationResponse();
			
			evaluationResponse.setEvaluationId(existedEvaluationEntity.getEvaluationId());
			evaluationResponse.setEvaluationName(existedEvaluationEntity.getEvaluationName());
			evaluationResponse.setEvaluationCategory(existedEvaluationEntity.getEvaluationCategory());
			evaluationResponse.setEvaluationSubject(existedEvaluationEntity.getEvaluationSubject());
			
			evaluationCriteriasResponse.setEvaluation(evaluationResponse);
			
			List<CriteriaEntity> existedCriteriaEntities = criteriaRepository.findByEvaluationId(existedEvaluationEntity.getEvaluationId());
			if (existedCriteriaEntities.size() > 0) {
				List<CriteriaQuestionsResponse> criteriaQuestionsResponses = new ArrayList<CriteriaQuestionsResponse>();
				
				for (CriteriaEntity existedCriteriaEntity : existedCriteriaEntities) {
					CriteriaQuestionsResponse criteriaQuestionsResponse = new CriteriaQuestionsResponse();
					
					CriteriaEntity criteriaEntity = criteriaRepository.findByCriteriaId(existedCriteriaEntity.getCriteriaId());
					if (BaseUtility.isObjectNotNull(criteriaEntity)) {
						CriteriaResponse criteriaResponse = new CriteriaResponse();
						
						criteriaResponse.setCriteriaId(criteriaEntity.getCriteriaId());
						criteriaResponse.setCriteriaName(criteriaEntity.getCriteriaName());
						criteriaResponse.setCriteriaDesc(criteriaEntity.getCriteriaDesc());
						criteriaResponse.setCriteriaPercentage(criteriaEntity.getCriteriaPercentage());
						criteriaResponse.setEvaluationId(criteriaEntity.getEvaluationId());
						
						criteriaQuestionsResponse.setCriteria(criteriaResponse);
						
						List<QuestionEntity> questionEntities = questionRepository.findByCriteriaId(existedCriteriaEntity.getCriteriaId());
						if (questionEntities.size() > 0) {
							List<QuestionResponse> questionResponses = new ArrayList<QuestionResponse>();
							
							for (QuestionEntity questionEntity : questionEntities) {
								QuestionResponse questionResponse = new QuestionResponse();
								
								questionResponse.setQuestionId(questionEntity.getQuestionId());
								questionResponse.setQuestionNumber(questionEntity.getQuestionNumber());
								questionResponse.setQuestionDesc(questionEntity.getQuestionDesc());
								questionResponse.setQuestionCategoryCode(questionEntity.getQuestionCategoryCode());
								questionResponse.setQuestionCategoryDesc(questionEntity.getQuestionCategoryDesc());
								questionResponse.setQuestionWeightage(questionEntity.getQuestionWeightage());
								questionResponse.setQuestionTotalMark(questionEntity.getQuestionTotalMark());
								questionResponse.setCriteriaId(questionEntity.getCriteriaId());
								
								List<Integer> questionTotalMarks = new ArrayList<Integer>();
								if (questionEntity.getQuestionTotalMark() > 0) {
									for (int i = 1; i <= questionEntity.getQuestionTotalMark(); i++) {
										questionTotalMarks.add(i);
									}
									
									questionResponse.setQuestionTotalMarks(questionTotalMarks);
								}
								
								// get question result by studentEvaluationId
								ResultEntity existedResultEntity = resultRepository.findByQuestionIdAndStudentEvaluationId(questionEntity.getQuestionId(), studentEvaluationId);
								
								if (BaseUtility.isObjectNotNull(existedResultEntity)) {
									ResultResponse resultResponse = new ResultResponse();
									
									resultResponse.setResultId(existedResultEntity.getResultId());
									resultResponse.setResultScore(existedResultEntity.getResultScore());
									resultResponse.setResultTotalMark(existedResultEntity.getResultTotalMark());
									resultResponse.setQuestionId(existedResultEntity.getQuestionId());
									resultResponse.setStudentEvaluationId(existedResultEntity.getStudentEvaluationId());
									
									questionResponse.setQuestionResult(resultResponse);
								}
								
								questionResponses.add(questionResponse);
							}
							criteriaQuestionsResponse.setQuestions(questionResponses);
						}
					}
					criteriaQuestionsResponses.add(criteriaQuestionsResponse);
				}
				evaluationCriteriasResponse.setCriterias(criteriaQuestionsResponses);
			}
		}		
		
		return evaluationCriteriasResponse;
	}

	@Override
	public List<ResultResponse> filterResults(ResultRequest resultRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultResponse getResultByResultId(String resultId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResultResponse> insertResults(List<ResultRequest> resultRequests) throws Exception {
		List<ResultResponse> resultResponses = new ArrayList<ResultResponse>();
		
		for (ResultRequest resultRequest : resultRequests) {
			ResultEntity existedResultEntity = resultRepository.findByQuestionIdAndStudentEvaluationId(resultRequest.getQuestionId(), resultRequest.getStudentEvaluationId());
			
			if (BaseUtility.isObjectNotNull(existedResultEntity)) {
				resultRequest.setResultId(existedResultEntity.getResultId());
				ResultResponse updatedResultResponse = updateResult(resultRequest);
				
				if (BaseUtility.isObjectNotNull(updatedResultResponse)) {
					resultResponses.add(updatedResultResponse);
				}
			} else {
				ResultEntity newResultEntity = new ResultEntity();
				
				newResultEntity.setResultId(BaseUtility.generateId());
				newResultEntity.setResultScore(resultRequest.getResultScore());
				newResultEntity.setResultTotalMark(resultRequest.getResultTotalMark());
				newResultEntity.setQuestionId(resultRequest.getQuestionId());
				newResultEntity.setStudentEvaluationId(resultRequest.getStudentEvaluationId());
				
				ResultEntity insertedResultEntity = resultRepository.save(newResultEntity);
				
				if (BaseUtility.isObjectNotNull(insertedResultEntity)) {
					ResultResponse resultResponse = new ResultResponse();
					
					resultResponse.setResultId(insertedResultEntity.getResultId());
					resultResponse.setResultScore(insertedResultEntity.getResultScore());
					resultResponse.setResultTotalMark(insertedResultEntity.getResultTotalMark());
					resultResponse.setQuestionId(insertedResultEntity.getQuestionId());
					resultResponse.setStudentEvaluationId(insertedResultEntity.getStudentEvaluationId());
					
					resultResponses.add(resultResponse);
				} else {
					throw new Exception();
				}
			}
		}
		
		return resultResponses;
	}

	@Override
	public ResultResponse updateResult(ResultRequest resultRequest) throws Exception {
		ResultResponse resultResponse = new ResultResponse();
		ResultEntity existedResultEntity = resultRepository.findByResultId(resultRequest.getResultId());
		
		if (BaseUtility.isObjectNotNull(existedResultEntity)) {
			existedResultEntity.setResultScore(resultRequest.getResultScore());
			existedResultEntity.setResultTotalMark(resultRequest.getResultTotalMark());
			existedResultEntity.setQuestionId(resultRequest.getQuestionId());
			existedResultEntity.setStudentEvaluationId(resultRequest.getStudentEvaluationId());
			
			ResultEntity updatedResultEntity = resultRepository.save(existedResultEntity);
			
			if (BaseUtility.isObjectNotNull(updatedResultEntity)) {
				resultResponse.setResultId(updatedResultEntity.getResultId());
				resultResponse.setResultScore(updatedResultEntity.getResultScore());
				resultResponse.setResultTotalMark(updatedResultEntity.getResultTotalMark());
				resultResponse.setQuestionId(updatedResultEntity.getQuestionId());
				resultResponse.setStudentEvaluationId(updatedResultEntity.getStudentEvaluationId());
			} else {
				throw new Exception();
			}
			
			return resultResponse;
		} else {
			return null;
		}
	}

	@Override
	public Boolean deleteResultByResultId(String resultId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentResponse> getEvaluationsStatus(String userType, String userId) {
		List<StudentResponse> studentEvaluationStarted = new ArrayList<StudentResponse>();
		List<StudentEntity> existedStudentEntities = new ArrayList<StudentEntity>();
		
		switch (userType) {
		case "ACD":
			existedStudentEntities = studentRepository.findByAcademicSvId(userId);
			break;
		case "IND":
			existedStudentEntities = studentRepository.findByIndustrySvId(userId);
			break;
		}
		
		if (existedStudentEntities.size() > 0) {
			for (StudentEntity studentEntity : existedStudentEntities) {
				List<StudentEvaluationEntity> existedStudentEvaluationEntities = studentEvaluationRepository.findByStudentMatricNumAndStudentEvaluationStatus(studentEntity.getStudentMatricNum(), "INC");
				
				if (existedStudentEvaluationEntities.size() > 0) {
					for (StudentEvaluationEntity studentEvaluationEntity : existedStudentEvaluationEntities) {
						
						EvaluationEntity existedEvaluationEntity = evaluationRepository.findByEvaluationId(studentEvaluationEntity.getEvaluationId());
						if (BaseUtility.isObjectNotNull(existedEvaluationEntity)) {
							Date currentDate = new Date();
							if (currentDate.after(studentEvaluationEntity.getStudentEvaluationStartDate()) && currentDate.before(studentEvaluationEntity.getStudentEvaluationEndDate())) {
								StudentResponse studentResponse = new StudentResponse();
								
								studentResponse.setStudentMatricNum(studentEntity.getStudentMatricNum());
								studentResponse.setStudentName(studentEntity.getStudentName());
								studentResponse.setStudentAddress(studentEntity.getStudentAddress());
								studentResponse.setStudentEmail(studentEntity.getStudentEmail());
								studentResponse.setStudentPhone(studentEntity.getStudentPhone());
								studentResponse.setStudentPassword(studentEntity.getStudentPassword());
								studentResponse.setStudentCampus(studentEntity.getStudentCampus());
								studentResponse.setStudentCourse(studentEntity.getStudentCourse());
								studentResponse.setStudentClass(studentEntity.getStudentClass());
								studentResponse.setStudentProject(studentEntity.getStudentProject());
								studentResponse.setStudentCV(studentEntity.getStudentCV());
								studentResponse.setStudentCVFileName(studentEntity.getStudentCVFileName());
								studentResponse.setStudentMiniTranscript(studentEntity.getStudentMiniTranscript());
								studentResponse.setStudentMiniTranscriptFileName(studentEntity.getStudentMiniTranscriptFileName());
								studentResponse.setStudentCoverLetter(studentEntity.getStudentCoverLetter());
								studentResponse.setStudentCoverLetterFileName(studentEntity.getStudentCoverLetterFileName());
								studentResponse.setStudentCourseOutline(studentEntity.getStudentCourseOutline());
								studentResponse.setStudentCourseOutlineFileName(studentEntity.getStudentCourseOutlineFileName());
								studentResponse.setStudentSL(studentEntity.getStudentSL());
								studentResponse.setStudentSLFileName(studentEntity.getStudentSLFileName());
								studentResponse.setIndustrySvId(studentEntity.getIndustrySvId());
								studentResponse.setAcademicSvId(studentEntity.getAcademicSvId());
								studentResponse.setCoordinatorId(studentEntity.getCoordinatorId());
								
								if (userType.equals(existedEvaluationEntity.getEvaluationCategory())) {
									studentEvaluationStarted.add(studentResponse);
								}
							}
						}
					}
				}
			}
		}
		return studentEvaluationStarted;
	}
}
