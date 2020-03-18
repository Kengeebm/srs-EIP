package com.eip.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.eip.domain.TimeSheetDateStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eip.domain.TimeSheet;
import com.eip.domain.TimeSheetHistory;
import com.eip.repository.TimeSheetHistoryRepository;
import com.eip.security.SecurityUtils;
import com.eip.service.TimeSheetHistoryService;
;

@Service
@Transactional
public class TimeSheetHistroyServiceImpl implements TimeSheetHistoryService {

	private static final Logger logger = LoggerFactory.getLogger(TimeSheetHistroyServiceImpl.class);

	@Autowired
	TimeSheetHistoryRepository timesheethistroy;

	/*request to fetch TimeSheetHistory
	 *  (non-Javadoc)
	 * @see com.eip.service.TimeSheetHistoryService#save(com.eip.domain.TimeSheetHistory)
	 */
	@Override
	public TimeSheetHistory save(TimeSheetHistory timeSheet) {
		logger.debug("request to fetch TimeSheetHistory {}",timeSheet);
      timeSheet.setUpdatedBy(SecurityUtils.getCurrentUserLogin().get());
      timeSheet.setUpdatedOn(LocalDate.now());
		return timesheethistroy.save(timeSheet);
	}


	/*request to update TimeSheetHistory
	 *  (non-Javadoc)
	 * @see com.eip.service.TimeSheetHistoryService#update(com.eip.domain.TimeSheetHistory)
	 */
	@Override
	public TimeSheetHistory update(TimeSheetHistory timeSheet) {
		logger.debug("request to update TimeSheetHistory {}",timeSheet);
		return timesheethistroy.save(timeSheet) ;
	}

	/*request to fetchall TimeSheetHistory
	 *  (non-Javadoc)
	 * @see com.eip.service.TimeSheetHistoryService#findall()
	 */
	@Override
	public List<TimeSheetHistory> findall() {
		logger.debug("request to fetchall TimeSheetHistory {}");
		return timesheethistroy.findAll();
	}

	/*request for delete TimeSheetHistory
	 *  (non-Javadoc)
	 * @see com.eip.service.TimeSheetHistoryService#deleteById(java.lang.String)
	 */
	@Override
	public void deleteById(String id) {
		logger.debug("request for delete TimeSheetHistory");
		timesheethistroy.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.eip.service.TimeSheetHistoryService#search(java.time.LocalDate, java.time.LocalDate)
	 */
	@Override
	public List<TimeSheetHistory> search(LocalDate fromDate, LocalDate toDate) {
		logger.debug("request to fetchall TimeSheetHistory {}");
		return timesheethistroy.findByDateTimeBetween(fromDate, toDate);

	}

	@Override
	public List<TimeSheetHistory> timesheetToTimesheetHistory(List<TimeSheet> timeSheets) {
		logger.debug("request to fetchall TimeSheetHistory {}",timeSheets );
		List<TimeSheetHistory> historys = new ArrayList<TimeSheetHistory>();

		for(TimeSheetDateStatus timeSheetDateStatus:timeSheets.get(0).getTimeSheetDateStatusList()) {
		TimeSheetHistory history = new TimeSheetHistory();
/*
		history.setClient(timeSheetDateStatus.getClient());
		history.setDateTime(timeSheetDateStatus.getDate());
		history.setEfforts(timeSheetDateStatus.getEfforts());
		history.setTs_id(timeSheetDateStatus.getId());
		history.setProject(timeSheetDateStatus.getProject());
		history.setStatus(timeSheetDateStatus.getStatus());
		history.setTask(timeSheetDateStatus.getTask());
		history.setUserLogin(timeSheetDateStatus.getUserLogin());
*/
		history.setUpdatedBy(SecurityUtils.getCurrentUserLogin().get());
		history.setUpdatedOn(LocalDate.now());
		historys.add(history);
		}

		return timesheethistroy.saveAll(historys);
	}

	public List<TimeSheetHistory> findByUserLogin(String userLogin){
		logger.debug("request to delete TimeSheetHistory {}",userLogin);
		return timesheethistroy.findByUserLogin(userLogin);
	}

	}



