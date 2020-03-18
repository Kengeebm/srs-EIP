 package com.eip.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eip.domain.TimeSheetApproval;
import com.eip.repository.TimeSheetApprovalRepository;
import com.eip.security.SecurityUtils;
import com.eip.service.TimeSheetApprovalService;

@Service
public class TimeSheetApprovalServiceImpl implements TimeSheetApprovalService {
	private static final Logger logger = LoggerFactory.getLogger(TimeSheetApprovalServiceImpl.class);


	@Autowired
	TimeSheetApprovalRepository timeSheetApprovalRepository;

	@Override
	public List<TimeSheetApproval> getTimeSheetApprovalDetails() {
		logger.debug("request for fetchAll from TimeSheetApproval");
		return timeSheetApprovalRepository.findAll();
	}
	
	@Override
	public List<TimeSheetApproval> getTimeSheetApprovalDetailsByUser(String userLogin) {
		logger.debug("request for fetchAll from TimeSheetApproval",userLogin);
		return timeSheetApprovalRepository.findByUserLogin(userLogin);
	}

	@Override
	public TimeSheetApproval saveTimeSheetApprovalDetails(TimeSheetApproval timeSheetApproval) {
		logger.debug("request for save from TimeSheetApproval",timeSheetApproval);
		if(timeSheetApproval.getId()==null) {
		timeSheetApproval.setCurrentDate(LocalDate.now());
		timeSheetApproval.setUserLogin(SecurityUtils.getCurrentUserLogin().get());
		}
		return timeSheetApprovalRepository.save(timeSheetApproval);
	}


	@Override
	public List<TimeSheetApproval> saveAllTimeSheetApprovalDetails(List<TimeSheetApproval> timeSheetApproval) {
		logger.debug("request for SaveAll from TimeSheetApproval",timeSheetApproval);
		for(TimeSheetApproval timesheet :timeSheetApproval) {
			timesheet.setCurrentDate(LocalDate.now());
			timesheet.setUserLogin(SecurityUtils.getCurrentUserLogin().get());
		}
		return timeSheetApprovalRepository.saveAll(timeSheetApproval);
	}

	@Override
	public TimeSheetApproval updateTimeSheetApproval(TimeSheetApproval timeSheetApproval) {  
		logger.debug("request for update from TimeSheetApproval",timeSheetApproval);
		return timeSheetApprovalRepository.save(timeSheetApproval);
	}

	@Override
	public void deleteTimeSheetApproval(String id) {
		logger.info("request for delete TimeSheetApproval",id);
		 timeSheetApprovalRepository.deleteById(id);
	}

	@Override
	public Long countByManagerEmail(String managerEmail) {
		logger.info("request for countBy TimeSheetApproval",managerEmail);
		return timeSheetApprovalRepository.countByManagerEmail(managerEmail);
	}

	@Override
	public List<TimeSheetApproval> findByManagerEmail(String managerEmail) {
		logger.info("request for fetchAllbyManagerEmail TimeSheetApproval",managerEmail);
		return timeSheetApprovalRepository.findByManagerEmail(managerEmail);
	}
	
	@Override
	public Optional<TimeSheetApproval> findById(String id) {
		logger.info("request for fetchAllbyManagerEmail TimeSheetApproval",id);
		return timeSheetApprovalRepository.findById(id);
	}
}