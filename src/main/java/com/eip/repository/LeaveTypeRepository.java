package com.eip.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eip.domain.LeaveType;

@Repository
public interface LeaveTypeRepository extends MongoRepository<LeaveType, String> {

	
}
