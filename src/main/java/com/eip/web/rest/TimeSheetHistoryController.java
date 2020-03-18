package com.eip.web.rest;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eip.domain.TimeSheetHistory;
import com.eip.service.TimeSheetHistoryService;

@RestController
@RequestMapping("/api")
public class TimeSheetHistoryController {
	
	
	private static final Logger log = LoggerFactory.getLogger(TimeSheetHistoryController.class);

	@Autowired
	TimeSheetHistoryService timeSheetHistoryService;

	/**
	 * request to save  timeSheetHistory Entity
	 * @param timesheet
	 * @return
	 */
	@PostMapping("/timesheet/history")
	public ResponseEntity<TimeSheetHistory> save(@RequestBody TimeSheetHistory timeSheet) {
		log.debug("request to save timeSheetHistory {}",timeSheet);
		TimeSheetHistory timeSheet1 = timeSheetHistoryService.save(timeSheet);
		return new ResponseEntity<TimeSheetHistory>(timeSheet1, HttpStatus.CREATED);
	}
	
	/**request to update  timeSheetHistory Entity
	 * @param timesheet
	 * @return
	 */
	@PutMapping("/timesheet/history")
	public ResponseEntity<TimeSheetHistory> update(@RequestBody TimeSheetHistory timeSheet){
		log.debug("request to update TimeSheetHistory {}",timeSheet);
		TimeSheetHistory timesheet1 = timeSheetHistoryService.update(timeSheet);
		return new ResponseEntity<TimeSheetHistory>(timesheet1, HttpStatus.OK);
	}
	/**request for fetchall timeSheetHistory Entity
	 * @return
	 */
	@GetMapping("/timesheet/history")
	public ResponseEntity<List<TimeSheetHistory>> findAll(){
		log.debug("request for fetchall TimeSheetHistory{}");
		List<TimeSheetHistory>timeSheetlist=timeSheetHistoryService.findall();
		return new ResponseEntity<List<TimeSheetHistory>>(timeSheetlist,HttpStatus.OK);
	}
	/**request for delete timeSheetHistory Entity
	 * @param id
	 */
	@DeleteMapping("/timesheet/history/{id}")
	public void deleteById(@PathVariable String id) {
		log.debug("request for delete TimeSheetHistory");
		timeSheetHistoryService.deleteById(id);
	}
	
	/**request for fetchall timeSheetHistory Entity  fromdate to todate
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	@GetMapping("/timesheet/history/search")
	public ResponseEntity<List<TimeSheetHistory>> search(@RequestParam LocalDate fromDate,@RequestParam LocalDate toDate){
		log.debug("request to fetchall TimeSheetHistory from ",fromDate,"to ",toDate);
		List<TimeSheetHistory>timeSheetlist=timeSheetHistoryService.search(fromDate, toDate);
		return new ResponseEntity<List<TimeSheetHistory>>(timeSheetlist,HttpStatus.OK);
	}
	
	@GetMapping("/timesheet/history/login")
	public ResponseEntity<List<TimeSheetHistory>> findByUserLogin(@RequestParam String userLogin){
		log.debug("request to fetchall based on userLogin"+ userLogin);
		List<TimeSheetHistory>timeSheetlist=timeSheetHistoryService.findByUserLogin(userLogin);
		return new ResponseEntity<List<TimeSheetHistory>>(timeSheetlist,HttpStatus.OK);
	}
}

		
		
		
		
		
		
		
		
		
	
	

