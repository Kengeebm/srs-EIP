package com.eip.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eip.domain.Notification;
import com.eip.repository.NotificationRepository;
import com.eip.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository notificationRepo;
	
	@Override
	public List<Notification> findAll() {
		return notificationRepo.findAll();
	}
	
	@Override
	public List<Notification> findTop5ByOrderByIdDesc() {
		return notificationRepo.findTop5ByOrderByIdDesc();
	}

	@Override
	public Notification save(Notification notification) {
		return notificationRepo.save(notification);
	}

	@Override
	public void delete(String id) {
        notificationRepo.deleteById(id);
	}
}
