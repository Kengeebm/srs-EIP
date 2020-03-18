package com.eip.web.rest;

import com.eip.domain.TimeSheet;
import com.eip.domain.UserDetail;
import com.eip.service.TimeSheetService;
import com.eip.web.rest.vm.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The type Time sheet controller.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TimeSheetController {
	private static final Logger logger = LoggerFactory.getLogger(TimeSheetController.class);

	/**
	 * The Time sheet service.
	 */
	@Autowired
	TimeSheetService timeSheetService;

	/**
	 * Save time sheet.
	 *
	 * @param timesheet the timesheet
	 * @return the time sheet
	 */
	@PostMapping("/timesheet")
	public TimeSheet save(@RequestBody TimeSheet timesheet) {
		logger.info("request for save data from timesheet", timesheet);
		return timeSheetService.save(timesheet);
	}

	/**
	 * Save all list.
	 *
	 * @param timesheet the timesheet
	 * @return the list
	 */
	@PostMapping("/timesheetAll")
	public List<TimeSheet> saveAll(@RequestBody List<TimeSheet> timesheet) {
		logger.info("request for saveall data from timesheet", timesheet);
		return timeSheetService.saveAll(timesheet);
	}

	/**
	 * Find all data list.
	 *
	 * @return the list
	 */
	@GetMapping("/timesheetAll")
	public List<TimeSheet> findAllData() {
		logger.info("request for findall data from timesheet");
		return timeSheetService.findAll();
	}

	/**
	 * Find by approval status list.
	 *
	 * @param approveStatus the approve status
	 * @return the list
	 */
	@GetMapping("/timesheetstatus/{approveStatus}")
	public List<TimeSheet> findByApprovalStatus(@PathVariable String approveStatus) {
		return timeSheetService.findByApprovalStatus(approveStatus);
	}

	/**
	 * Update string.
	 *
	 * @param id        the id
	 * @param timesheet the timesheet
	 * @return the string
	 */
	@PutMapping("/timesheet/{id}")
	public String update(@PathVariable String id, @RequestBody TimeSheet timesheet) {
		logger.info("request for update data from timesheet", timesheet);
		timesheet.setId(id);
		timeSheetService.update(timesheet);
		return "Timesheet record for tsId= " + id + "updated";
	}

	/**
	 * Update all list.
	 *
	 * @param timesheet the timesheet
	 * @return the list
	 */
	@PutMapping("/timesheet")
	public List<TimeSheet> updateAll(@RequestBody List<TimeSheet> timesheet) {
		logger.info("request for update data from timesheet", timesheet);
		return timeSheetService.updateAll(timesheet);
	}

	/**
	 * Find all list.
	 *
	 * @return the list
	 */
	@GetMapping("/timesheet")
	public List<TimeSheet> findAll() {
		logger.info("FetchAll from timesheet");
		return timeSheetService.findAll();
	}

	/**
	 * Find by id optional.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@GetMapping("/timesheet/{id}")
	public Optional<TimeSheet> findById(@PathVariable String id) {
		logger.info("FetchAll  from timesheet", id);
		return timeSheetService.findById(id);
	}

	/**
	 * Delete all string.
	 *
	 * @return the string
	 */
	@DeleteMapping("/timesheet")
	public String deleteAll() {
		logger.debug("delete  from timesheet");
		timeSheetService.deleteAll();
		return "All employee records deleted.";
	}

	/**
	 * Delete string.
	 *
	 * @param id the id
	 * @return the string
	 */
	@DeleteMapping("/timesheet/{id}")
	public String delete(@PathVariable String id) {
		logger.debug("delete data from timesheet");
		timeSheetService.deleteById(id);
		return "Employee record for employee-id= " + id + " deleted";
	}

	/**
	 * Find by date between time sheet.
	 *
	 * @param fromDate   the from date
	 * @param toDate     the to date
	 * @param employeeId the employee id
	 * @return the time sheet
	 */
	@GetMapping("/timesheet/datebased")
	public TimeSheet findByDateBetween(@RequestParam LocalDate fromDate, @RequestParam LocalDate toDate,
			@RequestParam String employeeId) {
		logger.info("FetchAll data from timesheet from", fromDate, "to", toDate);
		List<TimeSheet> timeSheet = timeSheetService.findByDateBetween(fromDate, toDate, employeeId);
		if (timeSheet != null && timeSheet.size() > 0) {
			return timeSheet.get(0);
		}
		return null;
	}

	/**
	 * Find by user login list.
	 *
	 * @param userLogin the user login
	 * @return the list
	 */
	@GetMapping("/timesheet/get")
	public List<TimeSheet> findByUserLogin(@RequestParam String userLogin) {
		logger.info("FetchAll data from timesheet", userLogin);
		return timeSheetService.findByUserLogin(userLogin);
	}

	/**
	 * Excel time sheets report response entity.
	 *
	 * @param fromDate the from date
	 * @param toDate   the to date
	 * @param email    the email
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@GetMapping("timesheet/download")
	public ResponseEntity<InputStreamResource> excelTimeSheetsReport(@RequestParam LocalDate fromDate,
			@RequestParam LocalDate toDate, @RequestParam String email) throws Exception {

		ByteArrayInputStream in = timeSheetService.generateExcel(fromDate, toDate, email);
		return ResponseEntity.ok().header("Content-Type", "application/vnd.ms-excel").body(new InputStreamResource(in));
	}

	/**
	 * Send reports as mail response entity.
	 *
	 * @param id       the id
	 * @param fromDate the from date
	 * @param toDate   the to date
	 * @param email    the email
	 * @param user     the user
	 * @return the response entity
	 */
	@GetMapping("timesheet/mail")
	public ResponseEntity<Message> sendReportsAsMail(@RequestParam String id, @RequestParam LocalDate fromDate,
			@RequestParam LocalDate toDate, @RequestParam String email, @RequestParam String user,
			@RequestParam String firstName, @RequestParam String lastName) {
		String status = "Email could not be sent";
		try {
			timeSheetService.sendReportsToEmail(fromDate, toDate, id, email, user, firstName, lastName);
			status = "Email sent successfully";
			Message message = new Message();
			message.setMessage(status);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message();
			message.setMessage(status);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
	}

	/**
	 * Find not submitted employee list list.
	 *
	 * @return the list
	 */
	@GetMapping("/timesheet/no-submit-emp-list")
	public List<UserDetail> findNotSubmittedEmployeeList() {
		return timeSheetService.findNotSubmittedEmployeeList();
	}

	/**
	 * Update employee list by timesheet status list.
	 *
	 * @param status                          the status
	 * @param userTimeSheetEmployeeStatusList the user time sheet employee status
	 *                                        list
	 * @return the list
	 */
	@PostMapping("/timesheet/timesheetapprovalstatus/{status}")
	public List<TimeSheet> updateEmployeeListByTimesheetStatus(@PathVariable String status,
			@RequestBody List<TimeSheet> userTimeSheetEmployeeStatusList) {
		return timeSheetService.updateEmployeeListByTimesheetStatus(status, userTimeSheetEmployeeStatusList);
	}

	/**
	 * Request unfreeze time sheet response entity.
	 *
	 * @param id       the id
	 * @param fromDate the from date
	 * @param toDate   the to date
	 * @param email    the email
	 * @param user     the user
	 * @return the response entity
	 */
	@GetMapping("/timesheet/unfreez-request")
	public ResponseEntity<Message> requestUnfreezeTimeSheet(@RequestParam String id, @RequestParam LocalDate fromDate,
			@RequestParam LocalDate toDate, @RequestParam String email, @RequestParam String user) {
		String status = "Email could not be sent";
		try {

			timeSheetService.requestUnfreezeTimeSheet(fromDate, toDate, id, email, user);
			status = "Email sent successfully";
			Message message = new Message();
			message.setMessage(status);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message();
			message.setMessage(status);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
	}

	@PostMapping("/timesheet/sendmailremainder")
	public ResponseEntity<Message> sendMailToEmployeeForSubmitTimesheet(@RequestBody List<UserDetail> timeSheets) {
		Message message = new Message();
		try {
			message.setMessage(timeSheetService.sendMailToEmployeeForSubmitTimesheet(timeSheets));
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} catch (Exception e) {
			message.setMessage("Email is not sent");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
	}

	@PostMapping(value = "/timesheet/download-all-employee-timesheet")
	public ResponseEntity<InputStreamResource> allExcelTimeSheetsReport(@RequestBody List<TimeSheet> timeSheetList)
			throws Exception {
		logger.info(
				"-------------------------------------download data from timesheet-----------------------------------------------------------------");
		ByteArrayInputStream in = timeSheetService.allDataGenerateExcel(timeSheetList);
		InputStreamResource inputStreamResource = new InputStreamResource(in);
		ResponseEntity<InputStreamResource> entity = null;
		try {
			entity = ResponseEntity.ok().header("Content-Type", "application/vnd.ms-excel").body(inputStreamResource);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return entity;
	}

	@GetMapping("/timesheet/timesheetstatuscount/{statusList}")
	public Long timesheetStatusCount(@PathVariable String statusList) {
		return timeSheetService.timesheetStatusCount(statusList);

	}

	@GetMapping("/timesheet/timesheetstatusbymonth/{status}/{submitDate}")
	public Long countByApproveStatusAndSubmitDate(@PathVariable String status, @PathVariable String submitDate) {
		return timeSheetService.countByApproveStatusAndSubmitDate(status, submitDate);
	}

	@GetMapping("/timesheet/timesheetlistbyemailstatus/{emailId}/{approveStatus}")
	public List<TimeSheet> findByEmailIdAndApproveStatus(@PathVariable String emailId,
			@PathVariable String approveStatus) {
		return timeSheetService.findByEmailIdAndApproveStatus(emailId, approveStatus);
	}
}
