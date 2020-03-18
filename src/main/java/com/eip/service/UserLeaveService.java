package com.eip.service;

import com.eip.domain.UserLeave;

import java.util.List;
import java.util.Optional;

public interface UserLeaveService {

	Optional<UserLeave> findByEmployeeId(String id);

	void save(UserLeave entity);

	List<UserLeave> findAll();
}
