package com.eip.service;

import java.util.List;

import com.eip.domain.LeaveType;

public interface LeaveTypeService {

	List<LeaveType> getLeaveTypes();

    LeaveType getLeaveTypes(String id);

	LeaveType save(LeaveType leaveType);

	void delete(String id);

}
