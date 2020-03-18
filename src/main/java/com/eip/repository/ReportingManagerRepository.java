package com.eip.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eip.domain.ReportingManager;

@Repository
public interface ReportingManagerRepository extends MongoRepository<ReportingManager, String>{

	List<ReportingManager> findByReportingManagerName(String name);
}
