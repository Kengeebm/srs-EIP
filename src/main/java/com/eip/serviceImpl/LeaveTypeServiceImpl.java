package com.eip.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eip.domain.LeaveType;
import com.eip.repository.LeaveTypeRepository;
import com.eip.service.LeaveTypeService;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @Override
    public List<LeaveType> getLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    @Override
    public LeaveType getLeaveTypes(String id) {
        return leaveTypeRepository.findById(id).get();
    }

    @Override
    public LeaveType save(LeaveType leaveType) {
        // TODO Auto-generated method stub
        return leaveTypeRepository.save(leaveType);
    }

    @Override
    public void delete(String id) {
        leaveTypeRepository.deleteById(id);
    }


}
