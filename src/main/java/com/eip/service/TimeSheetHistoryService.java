package com.eip.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.eip.domain.TimeSheet;
import com.eip.domain.TimeSheetHistory;

public interface TimeSheetHistoryService {
	
	public TimeSheetHistory save(TimeSheetHistory timesheet);
	public TimeSheetHistory update(TimeSheetHistory timesheet);//for update or saving data
	public List<TimeSheetHistory> findall();//for fetching data
	public void deleteById(String id);
	List<TimeSheetHistory> search(LocalDate fromDate, LocalDate toDate);
	public List<TimeSheetHistory> timesheetToTimesheetHistory(List<TimeSheet> timeSheets);
	public List<TimeSheetHistory> findByUserLogin(String userLogin);
}
