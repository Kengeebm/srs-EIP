package com.eip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Async;

import com.eip.domain.ContactAdmin;
import com.eip.domain.TimeSheet;




public interface ContactAdminService {

	ContactAdmin createRequest(ContactAdmin request);
	List<ContactAdmin> findAllRequest();
	void deleteContactAdminByEmpId(String empId);
	Optional<ContactAdmin> findContactAdminByEmpId(String empId);

	
}
