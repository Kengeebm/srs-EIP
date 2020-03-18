package com.eip.repository;

import com.eip.domain.UserLeave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLeaveRepository extends MongoRepository<UserLeave, String> {

    Optional<UserLeave> findByEmployeeId(String employeeId);
}
