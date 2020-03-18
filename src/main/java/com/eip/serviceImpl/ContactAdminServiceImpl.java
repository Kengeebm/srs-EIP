package com.eip.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eip.domain.ContactAdmin;
import com.eip.repository.ContactAdminRepository;
import com.eip.service.ContactAdminService;
import com.eip.service.MailService;

@Service
@Transactional
public class ContactAdminServiceImpl implements ContactAdminService{
	
	 

	@Autowired
	ContactAdminRepository contactadminRepository;
	
	@Autowired
	MailService mailService;

	@Override
	public ContactAdmin createRequest(ContactAdmin request) {
		mailService.emailForContactSystemAdministrator(request);
		return contactadminRepository.save(request);
	}

	@Override
	public List<ContactAdmin> findAllRequest() {
		return contactadminRepository.findAll();
	}

	@Override
	public Optional<ContactAdmin> findContactAdminByEmpId(String empId) {
		return contactadminRepository.findByEmpId(empId);
	}

	@Override
	public void deleteContactAdminByEmpId(String empId) {
		contactadminRepository.deleteContactAdminByEmpId(empId);

	}

	}
