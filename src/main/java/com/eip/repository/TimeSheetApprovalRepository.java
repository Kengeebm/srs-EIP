package com.eip.repository;

import com.eip.domain.TimeSheetApproval;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeSheetApprovalRepository extends MongoRepository<TimeSheetApproval, String> {

  List<TimeSheetApproval> findByUserLogin(String userLogin);
	
	Long countByManagerEmail(String managerEmail);
	
	List<TimeSheetApproval> findByManagerEmail(String managerEmail);

}
