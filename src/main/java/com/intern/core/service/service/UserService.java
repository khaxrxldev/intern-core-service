package com.intern.core.service.service;

import com.intern.core.service.dto.AcademicSupervisorResponse;
import com.intern.core.service.dto.CompanyResponse;
import com.intern.core.service.dto.IndustrySupervisorResponse;
import com.intern.core.service.dto.StudentResponse;

public interface UserService {

	AcademicSupervisorResponse getAcademicSupervisorByAcademicSvId(String academicSvId);

	IndustrySupervisorResponse getIndustrySupervisorByIndustrySvId(String industrySvId);

	StudentResponse getStudentByStudentMatricNum(String studentMatricNum) throws Exception;
	
	CompanyResponse getCompanyByCompanyId(String companyId) throws Exception;
}
