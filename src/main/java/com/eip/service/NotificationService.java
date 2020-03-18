package com.eip.service;

import java.util.List;
import com.eip.domain.Notification;

public interface NotificationService {
	
	List<Notification> findAll();
	
	List<Notification> findTop5ByOrderByIdDesc(); 

	Notification save(Notification notification);

	void delete(String id);
}
