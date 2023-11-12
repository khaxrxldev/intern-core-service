package com.intern.core.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.core.service.dao.AcademicSupervisorRepository;
import com.intern.core.service.dao.CompanyRepository;
import com.intern.core.service.dao.IndustrySupervisorRepository;
import com.intern.core.service.dao.StudentRepository;
import com.intern.core.service.dto.AcademicSupervisorResponse;
import com.intern.core.service.dto.CompanyResponse;
import com.intern.core.service.dto.IndustrySupervisorResponse;
import com.intern.core.service.dto.StudentResponse;
import com.intern.core.service.entity.StudentEntity;
import com.intern.core.service.utility.BaseUtility;
import com.intern.core.service.entity.CompanyEntity;
import com.intern.core.service.entity.IndustrySupervisorEntity;
import com.intern.core.service.entity.AcademicSupervisorEntity;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	AcademicSupervisorRepository academicSupervisorRepository;
	
	@Autowired
	IndustrySupervisorRepository industrySupervisorRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public AcademicSupervisorResponse getAcademicSupervisorByAcademicSvId(String academicSvId) {
		AcademicSupervisorResponse academicSupervisorResponse = new AcademicSupervisorResponse();
		AcademicSupervisorEntity existedAcademicSupervisorEntity = academicSupervisorRepository.findByAcademicSvId(academicSvId);
		
		if (BaseUtility.isObjectNotNull(existedAcademicSupervisorEntity)) {
			academicSupervisorResponse = new AcademicSupervisorResponse();
			
			academicSupervisorResponse.setAcademicSvId(existedAcademicSupervisorEntity.getAcademicSvId());
			academicSupervisorResponse.setAcademicSvName(existedAcademicSupervisorEntity.getAcademicSvName());
			academicSupervisorResponse.setAcademicSvPhone(existedAcademicSupervisorEntity.getAcademicSvPhone());
			academicSupervisorResponse.setAcademicSvEmail(existedAcademicSupervisorEntity.getAcademicSvEmail());
			academicSupervisorResponse.setAcademicSvPassword(existedAcademicSupervisorEntity.getAcademicSvPassword());
			academicSupervisorResponse.setAcademicSvGender(existedAcademicSupervisorEntity.getAcademicSvGender());
			academicSupervisorResponse.setAcademicSvPosition(existedAcademicSupervisorEntity.getAcademicSvPosition());
			academicSupervisorResponse.setAcademicSvCoordinator(existedAcademicSupervisorEntity.getAcademicSvCoordinator());
			
			return academicSupervisorResponse;
		}
		
		return null;
	}

	@Override
	public IndustrySupervisorResponse getIndustrySupervisorByIndustrySvId(String industrySvId) {
		IndustrySupervisorResponse industrySupervisorResponse = null;
		IndustrySupervisorEntity existedIndustrySupervisorEntity = industrySupervisorRepository.findByIndustrySvId(industrySvId);
		
		if (BaseUtility.isObjectNotNull(existedIndustrySupervisorEntity)) {
			industrySupervisorResponse = new IndustrySupervisorResponse();
			
			industrySupervisorResponse.setIndustrySvId(existedIndustrySupervisorEntity.getIndustrySvId());
			industrySupervisorResponse.setIndustrySvName(existedIndustrySupervisorEntity.getIndustrySvName());
			industrySupervisorResponse.setIndustrySvPhone(existedIndustrySupervisorEntity.getIndustrySvPhone());
			industrySupervisorResponse.setIndustrySvEmail(existedIndustrySupervisorEntity.getIndustrySvEmail());
			industrySupervisorResponse.setIndustrySvPassword(existedIndustrySupervisorEntity.getIndustrySvPassword());
			industrySupervisorResponse.setIndustrySvGender(existedIndustrySupervisorEntity.getIndustrySvGender());
			industrySupervisorResponse.setIndustrySvPosition(existedIndustrySupervisorEntity.getIndustrySvPosition());
			industrySupervisorResponse.setCompanyId(existedIndustrySupervisorEntity.getCompanyId());
			
			return industrySupervisorResponse;
		}
		
		return null;
	}

	@Override
	public StudentResponse getStudentByStudentMatricNum(String studentMatricNum) throws Exception {
		StudentResponse studentResponse = new StudentResponse();
		StudentEntity existedStudentEntity = studentRepository.findByStudentMatricNum(studentMatricNum);

		if (BaseUtility.isObjectNotNull(existedStudentEntity)) {
			studentResponse.setStudentMatricNum(existedStudentEntity.getStudentMatricNum());
			studentResponse.setStudentName(existedStudentEntity.getStudentName());
			studentResponse.setStudentAddress(existedStudentEntity.getStudentAddress());
			studentResponse.setStudentEmail(existedStudentEntity.getStudentEmail());
			studentResponse.setStudentPhone(existedStudentEntity.getStudentPhone());
			studentResponse.setStudentPassword(existedStudentEntity.getStudentPassword());
			studentResponse.setStudentCampus(existedStudentEntity.getStudentCampus());
			studentResponse.setStudentCourse(existedStudentEntity.getStudentCourse());
			studentResponse.setStudentClass(existedStudentEntity.getStudentClass());
			studentResponse.setStudentProject(existedStudentEntity.getStudentProject());
			studentResponse.setIndustrySvId(existedStudentEntity.getIndustrySvId());
			studentResponse.setAcademicSvId(existedStudentEntity.getAcademicSvId());
			studentResponse.setCoordinatorId(existedStudentEntity.getCoordinatorId());
			
			return studentResponse;
		}
		
		return null;
	}

	@Override
	public CompanyResponse getCompanyByCompanyId(String companyId) throws Exception {
		CompanyResponse companyResponse = new CompanyResponse();
		CompanyEntity existedCompanyEntity = companyRepository.findByCompanyId(companyId);
		
		if (BaseUtility.isObjectNotNull(existedCompanyEntity)) {
			companyResponse.setCompanyName(existedCompanyEntity.getCompanyName());
			companyResponse.setCompanyAddress(existedCompanyEntity.getCompanyAddress());
			companyResponse.setCompanyPhone(existedCompanyEntity.getCompanyPhone());
			companyResponse.setCompanyEmail(existedCompanyEntity.getCompanyEmail());
			companyResponse.setCompanyWebsite(existedCompanyEntity.getCompanyWebsite());
			companyResponse.setCompanyBrochure(existedCompanyEntity.getCompanyBrochure());
			companyResponse.setCompanyBrochureFileName(existedCompanyEntity.getCompanyBrochureFileName());
			companyResponse.setCompanyHrName(existedCompanyEntity.getCompanyHrName());
			companyResponse.setCompanyHrPhone(existedCompanyEntity.getCompanyHrPhone());
			companyResponse.setCompanyHrEmail(existedCompanyEntity.getCompanyHrEmail());
			companyResponse.setCompanyHrGender(existedCompanyEntity.getCompanyHrGender());

			return companyResponse;
		}
		
		return null;
	}
}
