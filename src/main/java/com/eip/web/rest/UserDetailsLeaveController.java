package com.eip.web.rest;

import com.eip.domain.UserDetailsLeave;
import com.eip.service.UserDetailsLeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.Constants;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

/**
 * The type User details leave controller.
 */
@RestController
@RequestMapping("/api/userdetailsleave")
public class UserDetailsLeaveController {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsLeaveController.class);

    /**
     * The User details leave service.
     */
    @Autowired
    UserDetailsLeaveService userDetailsLeaveService;

    /**
     * Find all list.
     *
     * @return the list
     */
    @GetMapping()
    public List<UserDetailsLeave> findAll() {
        logger.info("Request for findAll from userdetailsleave");
        return userDetailsLeaveService.findAll();
    }

    /**
     * Save.
     *
     * @param userDetailsLeave the user details leave
     */
    @PostMapping()
    public void save(@RequestBody UserDetailsLeave userDetailsLeave) {
        logger.info("Request for save from userdetailsleave", userDetailsLeave);
        userDetailsLeaveService.save(userDetailsLeave);
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    @GetMapping("/{employeeId}")
    public Optional<UserDetailsLeave> findById(@PathVariable String id) {
        logger.info("Request for findById from userdetailsleave", id);
        return userDetailsLeaveService.findById(id);
    }

    /**
     * Find by employee id list.
     *
     * @param employeeId the employee id
     * @return the list
     */
    @GetMapping("/employee/{employeeId}")
    public List<UserDetailsLeave> findByEmployeeId(@PathVariable String employeeId) {
        logger.info("Request for findByEmployeeId from userdetailsleave", employeeId);

        return userDetailsLeaveService.findByEmployeeId(employeeId);
    }

    /**
     * Leave request mail boolean.
     *
     * @param userDetailsLeave the user details leave
     * @return the boolean
     */
    @PostMapping("/leave-cancel")
    public Boolean leaveRequestMail(@RequestBody UserDetailsLeave userDetailsLeave) {
        logger.info("Sending Leave Request to the HR through mail", userDetailsLeave);
        return userDetailsLeaveService.leaveRequestMail(userDetailsLeave, false);
    }

    /**
     * Find by status list.
     *
     * @param status the status
     * @return the list
     */
    @GetMapping("/leavestatus/{status}")
    public List<UserDetailsLeave> findByStatus(@PathVariable String status) {
        logger.info("inside findByStatus , status is :", status);
        return userDetailsLeaveService.findByStatus(status);
    }
    /**
     * Find by leave type list.
     *
     * @param type the leave type
     * @return the list
     */
//    @GetMapping("/leavetype/{type}/{status}")
//    public List<UserDetailsLeave> findByLeaveTypeAndStatus(@PathVariable String type,@PathVariable String status) {
//        logger.info("inside findBy WFH , leaveType is :", type + "status",status);
//        return userDetailsLeaveService.findByLeaveTypeAndStatus(type, status);
//     }
    
//    @GetMapping("/leavetype/{type}")
//    public List<UserDetailsLeave> findByLeaveType(@PathVariable String type) {
//        logger.info("inside findBy AL and SL Pending , leaveType is :", type);
//        if (Constants.WFH.equalsIgnoreCase(type)) {
//            return userDetailsLeaveService.findByLeaveTypeAndStatus(type, "Pending");
//        } else {
//            return userDetailsLeaveService.findBySlAlLeaveAndStatus(Constants.WFH, "Pending");
//        }
//    }
//    
//    @GetMapping("/leaveapprovedlist/{type}")
//    public List<UserDetailsLeave> findAllLeaveApproved(@PathVariable String type) {
//        logger.info("inside  findBy AL and SL Approved , leaveType is :", type);
//        if (Constants.WFH.equalsIgnoreCase(type)) {
//            return userDetailsLeaveService.findByLeaveTypeAndStatus(type, "Approved");
//        } else {
//            return userDetailsLeaveService.findBySlAlLeaveAndStatus(Constants.WFH, "Approved");
//        }
//    }
    
    @PostMapping("/leavestatus/{status}")
    public List<UserDetailsLeave> updateEmployeeListByLeaveStatus(@PathVariable String status,
                                                                  @RequestBody List<UserDetailsLeave> userDetailsLeaveList) {
        logger.info("inside findByStatus , status is :", status);
        return userDetailsLeaveService.updateEmployeeListByLeaveStatus(status, userDetailsLeaveList);
    }

    @GetMapping("leavelist/download")
    public ResponseEntity<InputStreamResource> userDetailLeaveDataToExcel(@RequestBody List<UserDetailsLeave> userDetailsLeaveList) throws Exception {

        ByteArrayInputStream in = userDetailsLeaveService.userDetailLeaveDataToExcel(userDetailsLeaveList);
        return ResponseEntity.ok().header("Content-Type", "application/vnd.ms-excel").body(new InputStreamResource(in));
    }

    /**
     * Find by status list.
     *
     * @param statusList the status
     * @return the list
     */
    @GetMapping("/leavestatuscount/{statusList}")
    public Integer findByStatusCount(@PathVariable List<String> statusList) {
        logger.info("NAGENDRA_SINGH findByStatusCount , statusList is :", statusList);
        return userDetailsLeaveService.findBySubmittedList(statusList).size();
    }
    
	/*
	 * For sending mails to the employees whether their leave application is
	 * approved or rejected
	 */
    @PostMapping("/leave-response")
    public String sendMailToEmployeeForLeaveApproval(@RequestBody List<UserDetailsLeave> userDetailsLeave) {
        logger.info("Sending mail to employees for leave status", userDetailsLeave);
        return userDetailsLeaveService.sendMailToEmployeeForLeaveApproval(userDetailsLeave);
    }
    /**
     * Find by leave type list.
     *
     * @param type the leave type
     * @return the list
     */
//    @GetMapping("/leavestatuscount/{type}/{statusList}")
//    public Integer findCountByLeavetypeAndStatus(@PathVariable String type, @PathVariable List<String> statusList) {
//        logger.info("inside findByLeaveType , leaveType is :", type);
//        return userDetailsLeaveService.findCountByLeaveTypeAndStatus(type, statusList);
//    }
    
    @GetMapping("/totalleavestatuscount/{leaveType}/{status}")
	public Long LeaveStatusCountByLeaveType(@PathVariable String leaveType, @PathVariable String status) {
		return userDetailsLeaveService.LeaveStatusCountByLeaveType(leaveType,status);
	}
    @GetMapping("/totalleavestatuscountbymonth/{leaveType}/{status}/{submitDate}")
	public Long countByLeaveTypeAndStatusAndSubmitDate(@PathVariable String leaveType,@PathVariable String status, @PathVariable String submitDate) {
		return userDetailsLeaveService.countByLeaveTypeAndStatusAndSubmitDate(leaveType, status, submitDate);
	}
    
    @GetMapping("/leavelist/{empId}/{status}")
    public  List<UserDetailsLeave> findByEmployeeIdAndStatusIn(@PathVariable String empId, @PathVariable List<String> status){
    	return userDetailsLeaveService.findByEmployeeIdAndStatusIn(empId,status);
    }
    
    @GetMapping("/leavelistbytwoleavetype/{leaveTypes}/{statusList}")
	public List<UserDetailsLeave> findByLeaveTypeInAndStatusIn(@PathVariable List<String> leaveTypes, @PathVariable List<String> statusList) {
		return userDetailsLeaveService.findByLeaveTypeInAndStatusIn(leaveTypes, statusList);
	}
    
    @GetMapping("/leavelistbyreportingmanager/{reportingMngEmail}/{leaveTypes}/{statusList}")
	public List<UserDetailsLeave> findByReportingManagerEmailInAndLeaveTypeInAndStatusIn(@PathVariable List<String> reportingMngEmail,
			@PathVariable List<String> leaveTypes, @PathVariable List<String> statusList) {
		return userDetailsLeaveService.findByReportingManagerEmailInAndLeaveTypeInAndStatusIn(reportingMngEmail, leaveTypes, statusList);
	}

    @GetMapping("/leavelistbyreportingmanager/{reportingMngEmail}/{statusList}")
	public List<UserDetailsLeave> findByReportingManagerEmailInAndStatusIn(@PathVariable List<String> reportingMngEmail,
			@PathVariable List<String> statusList) {
		return userDetailsLeaveService.findByReportingManagerEmailInAndStatusIn(reportingMngEmail, statusList);
	}
    
}
