package com.intern.core.service.controller;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.intern.core.service.service.CoreService;
import com.intern.core.service.service.UserService;
import com.intern.core.service.dto.AcademicSupervisorResponse;
import com.intern.core.service.dto.ApplicationRequest;
import com.intern.core.service.dto.ApplicationResponse;
import com.intern.core.service.dto.CompanyResponse;
import com.intern.core.service.dto.CriteriaQuestionsResponse;
import com.intern.core.service.dto.CriteriaRequest;
import com.intern.core.service.dto.CriteriaResponse;
import com.intern.core.service.dto.EvaluationCriteriasResponse;
import com.intern.core.service.dto.EvaluationRequest;
import com.intern.core.service.dto.EvaluationResponse;
import com.intern.core.service.dto.IndustrySupervisorResponse;
import com.intern.core.service.dto.QuestionResponse;
import com.intern.core.service.dto.ReportResponse;
import com.intern.core.service.dto.Response;
import com.intern.core.service.dto.ResultRequest;
import com.intern.core.service.dto.ResultResponse;
import com.intern.core.service.dto.SemesterRequest;
import com.intern.core.service.dto.StudentEvaluationRequest;
import com.intern.core.service.dto.StudentEvaluationResponse;
import com.intern.core.service.dto.StudentResponse;
import com.intern.core.service.dto.StudentResultRequest;
import com.intern.core.service.dto.StudentResultResponse;
import com.intern.core.service.utility.BaseUtility;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/core")
public class CoreController {

	@Value("${spring.datasource.driver-class-name}")
    public String driverName; 
	
	@Value("${spring.datasource.url}")
    public String dbUrl; 
	
	@Value("${spring.datasource.username}")
    public String dbUsername; 
	
	@Value("${spring.datasource.password}")
    public String dbPassword; 
	
	@Autowired
	CoreService coreService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to intern core service";
	}
	
	@GetMapping("/evaluations")
	public ResponseEntity<Response> getEvaluations() {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<EvaluationResponse> evaluationResponses = coreService.getEvaluations();

		if (!BaseUtility.isListNull(evaluationResponses)) {
			message_status = true;
			
			object_map.put("evaluations", evaluationResponses);
		} else {
			message_status = false;
			message_desc = "NULL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/evaluations/filter")
	public ResponseEntity<Response> filterEvaluations(@RequestPart("evaluationRequest") EvaluationRequest evaluationRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<EvaluationResponse> evaluationResponses = coreService.filterEvaluations(evaluationRequest);
		object_map.put("evaluations", evaluationResponses);
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/evaluation/{evaluationId}")
	public ResponseEntity<Response> getEvaluationByEvaluationId(@PathVariable("evaluationId") String evaluationId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(evaluationId)) {
			EvaluationResponse evaluationResponse = coreService.getEvaluationByEvaluationId(evaluationId);
			
			if (BaseUtility.isObjectNotNull(evaluationResponse)) {
				message_status = true;
				message_desc = "SUCCESS";

				object_map.put("evaluation", evaluationResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/evaluation")
	public ResponseEntity<Response> insertEvaluation(@RequestPart("evaluationRequest") EvaluationRequest evaluationRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isObjectNotNull(evaluationRequest)) {
			EvaluationResponse evaluationResponse = coreService.insertEvaluation(evaluationRequest);
			
			if (BaseUtility.isObjectNotNull(evaluationResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("evaluation", evaluationResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PutMapping("/evaluation")
	public ResponseEntity<Response> updateEvaluation(@RequestPart("evaluationRequest") EvaluationRequest evaluationRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isObjectNotNull(evaluationRequest)) {
			EvaluationResponse evaluationResponse = coreService.updateEvaluation(evaluationRequest);
			
			if (BaseUtility.isObjectNotNull(evaluationResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("evaluation", evaluationResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@DeleteMapping("/evaluation/{evaluationId}")
	public ResponseEntity<Response> deleteEvaluationByEvaluationId(@PathVariable("evaluationId") String evaluationId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(evaluationId)) {
			Boolean deleteEvaluationStatus = coreService.deleteEvaluationByEvaluationId(evaluationId);
			
			if (deleteEvaluationStatus) {
				message_status = true;
				message_desc = "SUCCESS";
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/applications")
	public ResponseEntity<Response> getApplications() {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<ApplicationResponse> applicationResponses = coreService.getApplications();

		if (!BaseUtility.isListNull(applicationResponses)) {
			message_status = true;
			
			object_map.put("applications", applicationResponses);
		} else {
			message_status = false;
			message_desc = "NULL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/applications/filter")
	public ResponseEntity<Response> filterApplications(@RequestPart("applicationRequest") ApplicationRequest applicationRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<ApplicationResponse> applicationResponses = coreService.filterApplications(applicationRequest);
		object_map.put("applications", applicationResponses);
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/application/{applyStatus}/{studentMatricNum}")
	public ResponseEntity<Response> getApplicationsByApplyStatus(@PathVariable("applyStatus") Boolean applyStatus, @PathVariable("studentMatricNum") String studentMatricNum) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(studentMatricNum)) {
			List<CompanyResponse> companyResponses = coreService.getCompaniesByStudentApplication(applyStatus, studentMatricNum);
			
			if (BaseUtility.isObjectNotNull(companyResponses)) {
				message_status = true;
				message_desc = "SUCCESS";

				object_map.put("companies", companyResponses);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/application/{applicationId}")
	public ResponseEntity<Response> getApplicationByApplicationId(@PathVariable("applicationId") String applicationId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(applicationId)) {
			ApplicationResponse applicationResponse = coreService.getApplicationByApplicationId(applicationId);
			
			if (BaseUtility.isObjectNotNull(applicationResponse)) {
				message_status = true;
				message_desc = "SUCCESS";

				object_map.put("application", applicationResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/application")
	public ResponseEntity<Response> insertApplication(@RequestPart("applicationRequest") ApplicationRequest applicationRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isObjectNotNull(applicationRequest)) {
			ApplicationResponse applicationResponse = coreService.insertApplication(applicationRequest);
			
			if (BaseUtility.isObjectNotNull(applicationResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("application", applicationResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PutMapping("/application")
	public ResponseEntity<Response> updateApplication(@RequestPart("applicationRequest") ApplicationRequest applicationRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isObjectNotNull(applicationRequest)) {
			ApplicationResponse applicationResponse = coreService.updateApplication(applicationRequest);
			
			if (BaseUtility.isObjectNotNull(applicationResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("application", applicationResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@DeleteMapping("/application/{applicationId}")
	public ResponseEntity<Response> deleteApplicationByApplicationId(@PathVariable("applicationId") String applicationId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(applicationId)) {
			Boolean deleteApplicationStatus = coreService.deleteApplicationByApplicationId(applicationId);
			
			if (deleteApplicationStatus) {
				message_status = true;
				message_desc = "SUCCESS";
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/students/evaluations/results")
	public ResponseEntity<Response> getStudentsResults() throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<StudentResultResponse> studentResultResponses = coreService.getStudentsResults();
		object_map.put("studentResults", studentResultResponses);
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/students/evaluations/filter")
	public ResponseEntity<Response> filterStudentEvaluations(@RequestPart("studentEvaluationRequest") StudentEvaluationRequest studentEvaluationRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<StudentEvaluationResponse> studentEvaluationResponses = coreService.filterStudentEvaluations(studentEvaluationRequest);
		object_map.put("studentEvaluations", studentEvaluationResponses);
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/students/evaluations")
	public ResponseEntity<Response> insertStudentEvaluations(@RequestPart("semesters") List<SemesterRequest> semesterRequests, @RequestPart("studentMatricNum") String studentMatricNum) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isNotBlank(studentMatricNum)) {
			List<StudentEvaluationResponse> studentEvaluationResponses = coreService.insertStudentEvaluations(semesterRequests, studentMatricNum);
			
			if (!BaseUtility.isListNull(studentEvaluationResponses)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("studentEvaluations", studentEvaluationResponses);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/student/evaluation")
	public ResponseEntity<Response> insertStudentEvaluation(@RequestPart("studentEvaluationRequest") StudentEvaluationRequest studentEvaluationRequest, @RequestParam(name = "attachFile", required = false) MultipartFile attachFile) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isObjectNotNull(studentEvaluationRequest)) {
			StudentEvaluationResponse studentEvaluationResponse = coreService.insertStudentEvaluation(studentEvaluationRequest, attachFile);
			
			if (BaseUtility.isObjectNotNull(studentEvaluationResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("studentEvaluation", studentEvaluationResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PutMapping("/student/evaluation")
	public ResponseEntity<Response> updateStudentEvaluation(@RequestPart("studentEvaluationRequest") StudentEvaluationRequest studentEvaluationRequest, @RequestParam(name = "attachFile", required = false) MultipartFile attachFile) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isObjectNotNull(studentEvaluationRequest)) {
			StudentEvaluationResponse studentEvaluationResponse = coreService.updateStudentEvaluation(studentEvaluationRequest, attachFile);
			
			if (BaseUtility.isObjectNotNull(studentEvaluationResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("studentEvaluation", studentEvaluationResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@DeleteMapping("/student/evaluation/{studentEvaluationId}")
	public ResponseEntity<Response> deleteStudentEvaluation(@PathVariable("studentEvaluationId") String studentEvaluationId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(studentEvaluationId)) {
			Boolean deleteStudentEvaluationStatus = coreService.deleteStudentEvaluation(studentEvaluationId);
			
			if (deleteStudentEvaluationStatus) {
				message_status = true;
				message_desc = "SUCCESS";
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PutMapping("/students/evaluations")
	public ResponseEntity<Response> updateStudentEvaluations(@RequestPart("studentMatricNum") String studentMatricNum, @RequestPart("evaluationIds") List<String> evaluationIds) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		Boolean updateStatus = coreService.updateStudentEvaluations(studentMatricNum, evaluationIds);
		if (updateStatus) {
			message_status = true;
			message_desc = "SUCCESS";
		} else {
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/criterias")
	public ResponseEntity<Response> getCriterias() {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<CriteriaResponse> criteriaResponses = coreService.getCriterias();

		if (!BaseUtility.isListNull(criteriaResponses)) {
			message_status = true;
			
			object_map.put("criterias", criteriaResponses);
		} else {
			message_status = false;
			message_desc = "NULL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/criterias/filter")
	public ResponseEntity<Response> filterCriterias(@RequestPart("criteriaRequest") CriteriaRequest criteriaRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<CriteriaResponse> criteriaResponses = coreService.filterCriterias(criteriaRequest);
		object_map.put("criterias", criteriaResponses);
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/criteria/{evaluationId}")
	public ResponseEntity<Response> getCriteriaByCriteriaId(@PathVariable("evaluationId") String evaluationId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(evaluationId)) {
			CriteriaResponse criteriaResponse = coreService.getCriteriaByCriteriaId(evaluationId);
			
			if (BaseUtility.isObjectNotNull(criteriaResponse)) {
				message_status = true;
				message_desc = "SUCCESS";

				object_map.put("criteria", criteriaResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/criteria")
	public ResponseEntity<Response> insertCriteria(@RequestPart("criteriaRequest") CriteriaRequest criteriaRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isObjectNotNull(criteriaRequest)) {
			CriteriaResponse criteriaResponse = coreService.insertCriteria(criteriaRequest);
			
			if (BaseUtility.isObjectNotNull(criteriaResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("criteria", criteriaResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PutMapping("/criteria")
	public ResponseEntity<Response> updateCriteria(@RequestPart("criteriaRequest") CriteriaRequest criteriaRequest) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		if (BaseUtility.isObjectNotNull(criteriaRequest)) {
			CriteriaResponse criteriaResponse = coreService.updateCriteria(criteriaRequest);
			
			if (BaseUtility.isObjectNotNull(criteriaResponse)) {
				message_status = true;
				message_desc = "SUCCESS";
				
				object_map.put("criteria", criteriaResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@DeleteMapping("/criteria/{criteriaId}")
	public ResponseEntity<Response> deleteCriteriaByCriteriaId(@PathVariable("criteriaId") String criteriaId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(criteriaId)) {
			Boolean deleteCriteriaStatus = coreService.deleteCriteriaByCriteriaId(criteriaId);
			
			if (deleteCriteriaStatus) {
				message_status = true;
				message_desc = "SUCCESS";
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/evaluation/complete/{evaluationId}/{studentEvaluationId}")
	public ResponseEntity<Response> getCompleteEvaluation(@PathVariable("evaluationId") String evaluationId, @PathVariable("studentEvaluationId") String studentEvaluationId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(evaluationId) && BaseUtility.isNotBlank(studentEvaluationId)) {
			EvaluationCriteriasResponse evaluationCriteriasResponse = coreService.getCompleteEvaluation(evaluationId, studentEvaluationId);
			
			if (BaseUtility.isObjectNotNull(evaluationCriteriasResponse)) {
				message_status = true;
				message_desc = "SUCCESS";

				object_map.put("evaluation", evaluationCriteriasResponse);
			} else {
				error_desc = "FAIL";
			}
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/results")
	public ResponseEntity<Response> insertApplication(@RequestPart("resultRequests") List<ResultRequest> resultRequests) throws Exception {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();
		
		List<ResultResponse> resultResponses = coreService.insertResults(resultRequests);
		
		message_status = true;
		message_desc = "SUCCESS";
		object_map.put("results", resultResponses);
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@GetMapping("/evaluations/{userType}/{userId}")
	public ResponseEntity<Response> getEvaluationsStatus(@PathVariable("userType") String userType, @PathVariable("userId") String userId) {
		HttpStatus http_status = HttpStatus.OK;
		String error_desc = null;
		Boolean message_status = false;
		String message_desc = null;
		String message_code = null;
		String message_dev = null;
		Map<Object, Object> object_map = new HashMap<Object, Object>();

		if (BaseUtility.isNotBlank(userType) && BaseUtility.isNotBlank(userId)) {
			List<StudentResponse> studentResponses = coreService.getEvaluationsStatus(userType, userId);
			object_map.put("students", studentResponses);
		} else {
			http_status = HttpStatus.BAD_REQUEST;
			error_desc = "FAIL";
		}
		
		return returnResponse(null, http_status, error_desc, message_status, message_desc, message_code, message_dev, object_map);
	}
	
	@PostMapping("/students/evaluations/results/pdf")
	public ResponseEntity<byte[]> studentsResultsReport(@RequestPart("studentResultRequest") StudentResultRequest studentResultRequest) throws JRException {
		Map<String, Object> jasperParams = new HashMap<String, Object>();
		
		List<StudentResultResponse> studentsResults = coreService.getStudentsResults();
		List<StudentResultResponse> filterStudentsResults = new ArrayList<StudentResultResponse>();
		
		for (StudentResultResponse studentResultResponse : studentsResults) {			
			Boolean addStatus = true;
			
			if (BaseUtility.isNotBlank(studentResultRequest.getFilterClass()) && !(studentResultRequest.getFilterClass().equals(studentResultResponse.getStudentClass()))) {
				addStatus = false;
			}
			if (BaseUtility.isNotBlank(studentResultRequest.getFilterEvaluationStatus()) && !(studentResultRequest.getFilterEvaluationStatus().equals(studentResultResponse.getStudentEvaluationsStatus()))) {
				addStatus = false;
			}
			
			switch (studentResultResponse.getStudentEvaluationsStatus()) {
			case "CMP":
				studentResultResponse.setStudentEvaluationsStatus("COMPLETE");
				break;
			case "PND":
				studentResultResponse.setStudentEvaluationsStatus("PENDING");
				break;
			}
			
			if (addStatus) {
				filterStudentsResults.add(studentResultResponse);
			}
		}
		
		if (BaseUtility.isNotBlank(studentResultRequest.getFilterClass())) {
			jasperParams.put("CLASS_FILTER", studentResultRequest.getFilterClass());
		}
		if (BaseUtility.isNotBlank(studentResultRequest.getFilterEvaluationStatus())) {
			jasperParams.put("STATUS_FILTER", studentResultRequest.getFilterEvaluationStatus() == "CMP" ? "COMPLETE" : "PENDING");
		}
		
		String pictureDirectory = getClass().getResource("/images/UITM_LOGO_FULL.png").toString();
		jasperParams.put("PICTURE_DIRECTORY", pictureDirectory.replace("file:/", ""));
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(filterStudentsResults);
		jasperParams.put("STUDENT_RESULT", dataSource);
		
		InputStream inputStream = getClass().getResourceAsStream("/report/REPORT.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParams, new JREmptyDataSource());
		
		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=Report.pdf");
		headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/student/evaluation/result/pdf/{studentMatricNumber}/{evaluationId}/{studentEvaluationId}")
	public ResponseEntity<byte[]> studentResultReport(@PathVariable("studentMatricNumber") String studentMatricNumber, @PathVariable("evaluationId") String evaluationId, @PathVariable("studentEvaluationId") String studentEvaluationId) throws Exception {
		Map<String, Object> jasperParams = new HashMap<String, Object>();
		List<ReportResponse> reportResponses = new ArrayList<ReportResponse>();
		
		if (BaseUtility.isNotBlank(studentMatricNumber) && BaseUtility.isNotBlank(evaluationId) && BaseUtility.isNotBlank(studentEvaluationId)) {
			StudentResponse studentResponse = userService.getStudentByStudentMatricNum(studentMatricNumber);
			AcademicSupervisorResponse academicSupervisorResponse = new AcademicSupervisorResponse();
			IndustrySupervisorResponse industrySupervisorResponse = new IndustrySupervisorResponse();
			CompanyResponse companyResponse = new CompanyResponse();
			
			if (BaseUtility.isNotBlank(studentResponse.getAcademicSvId())) {
				academicSupervisorResponse = userService.getAcademicSupervisorByAcademicSvId(studentResponse.getAcademicSvId());
			}
			if (BaseUtility.isNotBlank(studentResponse.getIndustrySvId())) {
				industrySupervisorResponse = userService.getIndustrySupervisorByIndustrySvId(studentResponse.getIndustrySvId());
			}
			if (BaseUtility.isObjectNotNull(industrySupervisorResponse)) {
				if (BaseUtility.isNotBlank(industrySupervisorResponse.getCompanyId())) {
					companyResponse = userService.getCompanyByCompanyId(industrySupervisorResponse.getCompanyId());
				}
			}
			
			Integer evaluationTotalPercentage = 0;
			EvaluationCriteriasResponse evaluationCriteriasResponse = coreService.getCompleteEvaluation(evaluationId, studentEvaluationId);
			
			for (CriteriaQuestionsResponse criteriaQuestionsResponse : evaluationCriteriasResponse.getCriterias()) {
				Float criteriaTotalMark = 0.0f;
				for (QuestionResponse questionResponse : criteriaQuestionsResponse.getQuestions()) {
					criteriaTotalMark = criteriaTotalMark + questionResponse.getQuestionResult().getResultTotalMark();
				}
				
				for (QuestionResponse questionResponse : criteriaQuestionsResponse.getQuestions()) {
					ReportResponse reportResponse = new ReportResponse();
					
					reportResponse.setQuestionId(questionResponse.getQuestionId());
					reportResponse.setQuestionNumber(questionResponse.getQuestionNumber());

					String questionDescription = questionResponse.getQuestionDesc();
					questionDescription = questionDescription.replaceAll("<strong>", "<b>");
					questionDescription = questionDescription.replaceAll("</strong>", "</b>");
					reportResponse.setQuestionDesc(questionDescription);
					
					reportResponse.setQuestionCategoryCode(questionResponse.getQuestionCategoryCode());
					reportResponse.setQuestionCategoryDesc(questionResponse.getQuestionCategoryDesc());
					reportResponse.setQuestionWeightage(questionResponse.getQuestionWeightage());
					
					reportResponse.setResultScore(Math.round(questionResponse.getQuestionResult().getResultScore()));
					reportResponse.setResultTotalMark(questionResponse.getQuestionResult().getResultTotalMark());
					
					CriteriaResponse criteriaResponse = criteriaQuestionsResponse.getCriteria();
					reportResponse.setCriteriaId(criteriaResponse.getCriteriaId());
					reportResponse.setCriteriaName(criteriaResponse.getCriteriaName());
					reportResponse.setCriteriaDesc(criteriaResponse.getCriteriaDesc());
					reportResponse.setCriteriaPercentage(criteriaResponse.getCriteriaPercentage());
					
					if (questionResponse.getCriteriaId().equals(criteriaResponse.getCriteriaId())) {
						Float percent = (criteriaQuestionsResponse.getCriteria().getCriteriaPercentage().floatValue() / 100);
						reportResponse.setCriteriaTotalMark(criteriaTotalMark * percent);
					}
					
					reportResponses.add(reportResponse);
				}
				
				evaluationTotalPercentage = evaluationTotalPercentage + criteriaQuestionsResponse.getCriteria().getCriteriaPercentage();
			}
			
			StudentEvaluationResponse studentEvaluationResponse = coreService.getStudentEvaluationByStudentEvaluationId(studentEvaluationId);
			if (BaseUtility.isObjectNotNull(studentEvaluationResponse)) {
				jasperParams.put("EVALUATION_COMMENT", studentEvaluationResponse.getStudentEvaluationComment());
				jasperParams.put("EVALUATION_TOTAL_SCORE", studentEvaluationResponse.getStudentEvaluationTotalScore());
			}
			
			EvaluationResponse evaluationResponse = coreService.getEvaluationByEvaluationId(evaluationId);
			if (BaseUtility.isObjectNotNull(evaluationResponse)) {
				jasperParams.put("EVALUATION_NAME", evaluationResponse.getEvaluationName());
			}
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportResponses);
			jasperParams.put("criteriaId", dataSource);
			jasperParams.put("criteriaName", dataSource);
			jasperParams.put("criteriaDesc", dataSource);
			jasperParams.put("criteriaPercentage", dataSource);
			jasperParams.put("criteriaTotalMark", dataSource);
			jasperParams.put("questionDesc", dataSource);
			jasperParams.put("questionWeightage", dataSource);
			jasperParams.put("resultScore", dataSource);
			jasperParams.put("resultTotalMark", dataSource);
			
			jasperParams.put("STUDENT_NAME", studentResponse.getStudentName());
			jasperParams.put("STUDENT_MATRIC_NUMBER", studentResponse.getStudentMatricNum());
			jasperParams.put("ACADEMIC_COACH", academicSupervisorResponse.getAcademicSvName());
			jasperParams.put("INDUSTRY_COACH", industrySupervisorResponse.getIndustrySvName());
			jasperParams.put("COMPANY_NAME", companyResponse.getCompanyName());
			jasperParams.put("PROJECT_TITLE", studentResponse.getStudentProject());
			jasperParams.put("EVALUATION_TOTAL_PERCENTAGE", evaluationTotalPercentage);

			String pictureDirectory = getClass().getResource("/images/UITM_LOGO_FULL.png").toString();
			jasperParams.put("PICTURE_DIRECTORY", pictureDirectory.replace("file:/", ""));
			
			InputStream inputStream = getClass().getResourceAsStream("/report/STUDENT_RESULT_REPORT.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParams, dataSource);
			
			byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=Report.pdf");
			headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
			
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} else {
			return ResponseEntity.ok().body(null);
		}
	}

	public ResponseEntity<Response> returnResponse(Errors errors, HttpStatus http_status, String error_desc, Boolean msg_status, String msg_desc, String msg_code, String msg_dev, Map<Object, Object> object_map) {
		if (BaseUtility.isObjectNotNull(errors)) {
			if (errors.hasErrors()) {
				for (ObjectError objectError: errors.getAllErrors()) {
					error_desc = error_desc + objectError.getDefaultMessage();
					
					if (errors.getErrorCount() > 1) {
						error_desc = error_desc + ", ";
					}
				}
			}
		}
		
		Response response = new Response();
		response.setTime_stamp(LocalDateTime.now());
		response.setStatus_code(http_status.value());
		response.setStatus_desc(http_status);
		response.setError_desc(error_desc);
		response.setMessage_status(msg_status);
		response.setMessage_desc(msg_desc);
		response.setMessage_code(msg_code);
		response.setMessage_dev(msg_dev);
		response.setData(object_map);
		
		return ResponseEntity.ok(response);
	}
}
