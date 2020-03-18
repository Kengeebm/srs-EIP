package com.eip.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eip.domain.Notification;
import com.eip.serviceImpl.NotificationServiceImpl;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	NotificationServiceImpl notificationServiceImpl;

	@GetMapping("/findAll")
	public List<Notification> findAll() {
		return notificationServiceImpl.findAll();
	}
    
	@GetMapping("/findLastFive")
	public List<Notification> findTop5ByOrderByIdDesc() {
		return notificationServiceImpl.findTop5ByOrderByIdDesc();
	}
	
	@PostMapping("/create")
	public Notification save(@RequestBody Notification notification) {
		return notificationServiceImpl.save(notification);
	}
    
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id) {
		notificationServiceImpl.delete(id);
	}
}
