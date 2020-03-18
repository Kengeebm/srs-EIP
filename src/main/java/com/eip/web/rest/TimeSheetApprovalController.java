 package com.eip.web.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eip.domain.TimeSheetApproval;
import com.eip.service.TimeSheetApprovalService;
import com.eip.serviceImpl.TimeSheetApprovalServiceImpl;

/**
 * @author rahul jha
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TimeSheetApprovalController {
	private static final Logger logger = LoggerFactory.getLogger(TimeSheetApprovalController.class);

	@Autowired
	TimeSheetApprovalService timeSheetApprovalService;

	/**
	 * This method return all the record of time sheet for approval
	 * 
	 * @return
	 */
	@GetMapping("/findall")
	public ResponseEntity<List<TimeSheetApproval>> getTimeSheetApprovalDetails() {
		logger.info( "FetchAll data from timesheetAproval");
		List<TimeSheetApproval> result = timeSheetApprovalService.getTimeSheetApprovalDetails();
		return new ResponseEntity<List<TimeSheetApproval>>(result, HttpStatus.OK);
	}

	/**
	 * This method return time sheet approval record as per given user login
	 * 
	 * @param userLogin
	 * @return
	 */
	@GetMapping("/findbyuser/{userLogin}")
	public ResponseEntity<List<TimeSheetApproval>> getTimeSheetApprovalDetailsByUser(@PathVariable String userLogin) {
		logger.info( "FetchAll data from timesheetAproval",userLogin);
		List<TimeSheetApproval> result = timeSheetApprovalService.getTimeSheetApprovalDetailsByUser(userLogin);
		return new ResponseEntity<List<TimeSheetApproval>>(result, HttpStatus.OK);

	}

	/**
	 * This method save one time sheet approval request
	 * 
	 * @param timeSheetApproval
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<TimeSheetApproval> saveTimeSheetApprovalDetails(
			@RequestBody TimeSheetApproval timeSheetApproval) {
		logger.info( " request for save from timesheetAproval",timeSheetApproval);
		TimeSheetApproval result = timeSheetApprovalService.saveTimeSheetApprovalDetails(timeSheetApproval);
         return new ResponseEntity<TimeSheetApproval>(result, HttpStatus.CREATED);
	}

	/**
	 * This method save all the time sheet approval request
	 * 
	 * @param timeSheetApproval
	 * @return
	 */
	@PostMapping("/createall")
	public ResponseEntity<List<TimeSheetApproval>> saveAllTimeSheetApprovalDetails(
			@RequestBody List<TimeSheetApproval> timeSheetApproval) {
		logger.info( " request for updateall from timesheetAproval",timeSheetApproval);
		List<TimeSheetApproval> result = timeSheetApprovalService.saveAllTimeSheetApprovalDetails(timeSheetApproval);
		return new ResponseEntity<List<TimeSheetApproval>>(result, HttpStatus.CREATED);

	}

	/**
	 * This method update one time sheet approval request
	 * 
	 * @param timeSheetApproval
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<TimeSheetApproval> updateTimeSheetApproval(@RequestBody TimeSheetApproval timeSheetApproval) {
		logger.info( " request for update from timesheetAproval",timeSheetApproval);
		TimeSheetApproval result = timeSheetApprovalService.updateTimeSheetApproval(timeSheetApproval);
		return new ResponseEntity<TimeSheetApproval>(result, HttpStatus.OK);
	}

	/**
	 * This method delete time sheet approval record as per id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<TimeSheetApproval> deleteTimeSheetApproval(@RequestParam String id) {
		logger.debug( " request for delete  timesheetAproval",id);
		timeSheetApprovalService.deleteTimeSheetApproval(id);
		return new ResponseEntity<TimeSheetApproval>(HttpStatus.OK);
	}
	
	/**
	 * This method gives the number of record per manager email
	 * 
	 * @param managerEmail
	 * @return
	 */
	@GetMapping("/manageremailcount")
	public ResponseEntity<Long> countBymanagerEamil(@RequestParam String managerEmail){
		logger.debug( " request for fetchAll from timesheetAproval",managerEmail);
		Long result = timeSheetApprovalService.countByManagerEmail(managerEmail);
		return new ResponseEntity<Long>(result, HttpStatus.OK);
	}
	
	/**
	 * This method will return all the data as per manager email
	 * 
	 * @param managerEmail
	 * @return
	 */
	@GetMapping("/findallbymanager")
	public ResponseEntity<List<TimeSheetApproval>> findByManagerEmail(@RequestParam String managerEmail){
		logger.debug( " request for fetchAllByManeger from timesheetAproval",managerEmail);
		List<TimeSheetApproval> result = timeSheetApprovalService.findByManagerEmail(managerEmail);
		return new ResponseEntity<List<TimeSheetApproval>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/findbyId/{id}")
	public ResponseEntity<Optional<TimeSheetApproval>> getTimeSheetApprovalDetailsById(@PathVariable String id) {
		logger.debug( " request for fetchAllByManeger from timesheetAproval",id);
		Optional<TimeSheetApproval> result = timeSheetApprovalService.findById(id);
		return new ResponseEntity<Optional<TimeSheetApproval>>(result, HttpStatus.OK);

	}

}
