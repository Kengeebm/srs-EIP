package com.eip.service;

import com.eip.domain.UserDetailsLeave;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

public interface UserDetailsLeaveService {

    List<UserDetailsLeave> findAll();

    void save(UserDetailsLeave history);

    Optional<UserDetailsLeave> findById(String id);

    List<UserDetailsLeave> findByEmployeeId(String employeeId);

    List<UserDetailsLeave> findByStatus(String status);

    Boolean leaveRequestMail(UserDetailsLeave userDetailsLeave, Boolean isApplyLeave);

//    List<UserDetailsLeave> findByLeaveTypeAndStatus(String leaveType, String status);

//    List<UserDetailsLeave> findBySlAlLeaveAndStatus(String leaveType, String status);

    List<UserDetailsLeave> updateEmployeeListByLeaveStatus(String leaveStatus, List<UserDetailsLeave> userDetailsLeaveList);

    ByteArrayInputStream userDetailLeaveDataToExcel(List<UserDetailsLeave> UserDetailList);

    List<UserDetailsLeave> findBySubmittedList(List<String> statusList);

//    Integer findCountByLeaveTypeAndStatus(String leaveType, List<String> statusList);
    
    Long LeaveStatusCountByLeaveType(String leaveType,String status);
    
    Long countByLeaveTypeAndStatusAndSubmitDate(String leaveType, String status, String date);
    
    List<UserDetailsLeave> findByEmployeeIdAndStatusIn(String empId, List<String> status);

	String sendMailToEmployeeForLeaveApproval(List<UserDetailsLeave> userDetailsLeave);
	
    List<UserDetailsLeave> findByLeaveTypeInAndStatusIn(List<String> leaveTypes, List<String>  statusList);
     
    List<UserDetailsLeave> findByReportingManagerEmailInAndLeaveTypeInAndStatusIn(List<String> reportingMngEmail, List<String> leaveTypes, List<String> statusList);
    
    List<UserDetailsLeave> findByReportingManagerEmailInAndStatusIn(List<String> reportingMngEmail, List<String> statusList);

}
