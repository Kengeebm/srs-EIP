package com.eip.service;

import com.eip.domain.TimeSheet;
import com.eip.domain.UserDetail;

import org.springframework.scheduling.annotation.Async;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The interface Time sheet service.
 */
public interface TimeSheetService {

    /**
     * display all records
     *
     * @return list
     */
    List<TimeSheet> findAll();

    /**
     * display records by id
     *
     * @param id the id
     * @return optional
     */
    Optional<TimeSheet> findById(String id);

    /**
     * insert a record
     *
     * @param timesheet the timesheet
     * @return time sheet
     */
    TimeSheet save(TimeSheet timesheet);

    /**
     * insert all records
     *
     * @param timesheet the timesheet
     * @return list
     */
    List<TimeSheet> saveAll(List<TimeSheet> timesheet);

    /**
     * update the record
     *
     * @param timesheet the timesheet
     * @return
     */
    void update(TimeSheet timesheet);

    /**
     * update all records
     *
     * @param timesheet the timesheet
     * @return list
     */
    List<TimeSheet> updateAll(List<TimeSheet> timesheet);

    /**
     * delete a particular record
     *
     * @param id the id
     */
    void deleteById(String id);

    /**
     * delete all records
     */
    void deleteAll();

    /**
     * Find by user login list.
     *
     * @param userLogin the user login
     * @return the list
     */
    List<TimeSheet> findByUserLogin(String userLogin);

    /**
     * Find by date between list.
     *
     * @param fromDate the from date
     * @param toDate   the to date
     * @return the list
     */
    List<TimeSheet> findByDateBetween(LocalDate fromDate, LocalDate toDate, String employeeId);

    /**
     * Generate excel byte array input stream.
     *
     * @param fromDate the from date
     * @param toDate   the to date
     * @param email    the email
     * @return the byte array input stream
     */
    ByteArrayInputStream generateExcel(LocalDate fromDate, LocalDate toDate, String email);

    /**
     * Send reports to email string.
     *
     * @param fromDate the from date
     * @param toDate   the to date
     * @param email    the email
     * @param user     the user
     * @return the string
     */
    
    List<TimeSheet> findByApprovalStatus(String ApprovalStatus);

    List<UserDetail> findNotSubmittedEmployeeList();

    List<TimeSheet> updateEmployeeListByTimesheetStatus(String status, List<TimeSheet> userTimeSheetEmployeeStatusList);

    String requestUnfreezeTimeSheet(LocalDate fromDate, LocalDate toDate, String id, String email, String user);

    @Async
    String sendMailToEmployeeForSubmitTimesheet(List<UserDetail> timeSheets);

    ByteArrayInputStream allDataGenerateExcel(List<TimeSheet> timeSheetList);

    Long timesheetStatusCount(String status);
    
    Long countByApproveStatusAndSubmitDate(String status,String submitDate);

	String sendReportsToEmail(LocalDate fromDate, LocalDate toDate, String id, String email, String user,
			String firstName, String lastName);
	
	List<TimeSheet> findByEmailIdAndApproveStatus(String emailId, String approveStatus);
}
