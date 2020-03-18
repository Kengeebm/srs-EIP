package com.eip.serviceImpl;

import com.eip.domain.UserLeave;
import com.eip.repository.UserLeaveRepository;
import com.eip.service.UserLeaveService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLeaveServiceImpl implements UserLeaveService {
	private static final Logger logger = LoggerFactory.getLogger(UserLeaveServiceImpl.class);


	@Autowired
    UserLeaveRepository userLeaveRepository;

	@Override
	public Optional<UserLeave> findByEmployeeId(String employeeId) {
		logger.info("request to fetchALL from userLeave {}",employeeId);
		return userLeaveRepository.findByEmployeeId(employeeId);
	}

	@Override
	public void save(UserLeave entity) {
		logger.info("request to save from userLeave {}",entity);
		userLeaveRepository.save(entity);

	}

	@Override
	public List<UserLeave> findAll() {
		logger.info("request to fetchALL from userLeave {}");
		return userLeaveRepository.findAll();
	}

}
