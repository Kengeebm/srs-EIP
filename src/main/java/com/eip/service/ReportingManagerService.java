package com.eip.service;

import java.util.List;
import java.util.Optional;
import com.eip.domain.ReportingManager;

public interface ReportingManagerService {

    List<ReportingManager> findAll();

    void save(ReportingManager reportingManager);
    
    void update(ReportingManager reportingManager);

    Optional<ReportingManager> findById(String id);
    
    void delete(String id);
	 
	String findByName(String name);
	
	List<ReportingManager> findByReportingManagerName(String name);
}
