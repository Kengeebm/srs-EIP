package com.eip.repository;

import com.eip.domain.UserDetailsLeave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsLeaveRepository extends MongoRepository<UserDetailsLeave, String> {

    List<UserDetailsLeave> findByEmployeeId(String employeeId);

    List<UserDetailsLeave> findByStatus(String status);

    List<UserDetailsLeave> findByStatusIn(List<String> statusList);
  
    List<UserDetailsLeave> findByLeaveTypeAndStatus(String leaveType, String status);

//    List<UserDetailsLeave> findByLeaveTypeNotLikeAndStatus(String leaveType, String status);
    
    Long countByLeaveTypeAndStatus(String leaveType, String status);
    
    List<UserDetailsLeave> findByEmployeeIdAndStatusIn(String empId, List<String> status);
    
    List<UserDetailsLeave> findByLeaveTypeInAndStatusIn(List<String> leaveTypes, List<String> statusList);
    
    List<UserDetailsLeave> findByReportingManagerEmailInAndLeaveTypeInAndStatusIn(List<String> reportingMngEmail, List<String> leaveTypes, List<String> statusList);
    
    List<UserDetailsLeave> findByReportingManagerEmailInAndStatusIn(List<String> reportingMngEmail, List<String> statusList);
}
