package com.eip.service;

import java.util.List;
import java.util.Optional;

import com.eip.domain.TimeSheetApproval;

public interface TimeSheetApprovalService {
	
	List<TimeSheetApproval>  getTimeSheetApprovalDetails();
	
	List<TimeSheetApproval> getTimeSheetApprovalDetailsByUser(String userLogin);
	
	TimeSheetApproval saveTimeSheetApprovalDetails(TimeSheetApproval timeSheetApproval);
	
	List<TimeSheetApproval> saveAllTimeSheetApprovalDetails(List<TimeSheetApproval> timeSheetApproval);
	
	TimeSheetApproval updateTimeSheetApproval(TimeSheetApproval timeSheetApproval);
	
	void deleteTimeSheetApproval(String id);
	
	Long countByManagerEmail(String managerEmail);
	
	List<TimeSheetApproval> findByManagerEmail(String managerEmail);

	Optional<TimeSheetApproval> findById(String id);
}
