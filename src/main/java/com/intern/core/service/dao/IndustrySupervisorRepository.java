package com.intern.core.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.core.service.entity.IndustrySupervisorEntity;

@Repository
public interface IndustrySupervisorRepository extends JpaRepository<IndustrySupervisorEntity, String> {

	IndustrySupervisorEntity findByIndustrySvId(String industrySvId);

	IndustrySupervisorEntity findByIndustrySvEmail(String industrySvEmail);
	
	Integer deleteByIndustrySvId(String industrySvId);
}
