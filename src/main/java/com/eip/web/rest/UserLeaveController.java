package com.eip.web.rest;

import com.eip.domain.UserLeave;
import com.eip.service.UserLeaveService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userleave")
public class UserLeaveController {
	private static final Logger logger = LoggerFactory.getLogger(UserLeaveController.class);

	

	@Autowired
    UserLeaveService userLeaveService;

	@GetMapping("/{employeeId}")
	public Optional<UserLeave> findById(@PathVariable String employeeId) {
		logger.info("Request for fetchallById from userleave",employeeId);
         return userLeaveService.findByEmployeeId(employeeId);
	}

	@PostMapping()
	public void save(@RequestBody UserLeave entity) {
		logger.info("Request for save from userleave", entity);
		userLeaveService.save(entity);
	}

    @PutMapping()
    public void update(@RequestBody UserLeave entity) {
    	logger.info("Request for update from userleave", entity);
        userLeaveService.save(entity);
    }

	@GetMapping()
	public List<UserLeave> findAll() {
		logger.info("Request for fetchall from userleave");
		return userLeaveService.findAll();
	}

}
