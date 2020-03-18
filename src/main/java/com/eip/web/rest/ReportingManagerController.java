package com.eip.web.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eip.domain.ReportingManager;
import com.eip.serviceImpl.ReportingManagerServiceImpl;

@RestController
@RequestMapping("/api")
public class ReportingManagerController {

	private static final Logger log = LoggerFactory.getLogger(StatusController.class);

	@Autowired
	ReportingManagerServiceImpl reportingManagerServiceImpl;

	String reportingmanagerlists = null;

	@GetMapping("/reportingmanager")
	public List<ReportingManager> findAll() {
		return reportingManagerServiceImpl.findAll();
	}

	@PostMapping("/reportingmanager")
	public ResponseEntity<Boolean> save(@RequestBody ReportingManager reportingManager) {
		if (reportingManager.getId() == null) {
			List<ReportingManager> reportingManagerList = reportingManagerServiceImpl.findAll();
			if (reportingManagerList.stream()
					.filter(data -> data.getReportingManagerId().equals(reportingManager.getReportingManagerId())
							|| data.getReportingManagerEmail()
									.contentEquals(reportingManager.getReportingManagerEmail()))
					.count() > 0) {
				return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
			}
			reportingManagerServiceImpl.save(reportingManager);
		} else {
			reportingManagerServiceImpl.save(reportingManager);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@PutMapping("/reportingmanager")
	public ResponseEntity<Boolean> update(@RequestBody ReportingManager reportingManager) {
      if (reportingManager.getId() == null) {
			List<ReportingManager> reportingManagerList = reportingManagerServiceImpl.findAll();
	if (reportingManagerList.stream()
			.filter(data -> data.getReportingManagerId().equals(reportingManager.getReportingManagerId())
					|| data.getReportingManagerEmail()
							.contentEquals(reportingManager.getReportingManagerEmail()))
			.count() > 0) {
		return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
	}
	reportingManagerServiceImpl.save(reportingManager);
} else {
	reportingManagerServiceImpl.save(reportingManager);
}
return new ResponseEntity<Boolean>(true, HttpStatus.OK);
}

	@DeleteMapping("/reportingmanager/{id}")
	public void delete(@PathVariable String id) {
		reportingManagerServiceImpl.delete(id);
	}

	@GetMapping("/reportingmanager/{id}")
	public Optional<ReportingManager> findById(@PathVariable String id) {
		return reportingManagerServiceImpl.findById(id);
	}

	@GetMapping("/reportingmanager/email/{name}")
	public String findByName(@PathVariable String name) {
		log.debug("request to fetchall based on name" + name);
		reportingmanagerlists = reportingManagerServiceImpl.findByName(name);
		return reportingmanagerlists;
	}
	
	@GetMapping("/reportingmanager/emailbyname/{name}")
	public List<ReportingManager> findByReportingManagerName(@PathVariable String name) {
		return reportingManagerServiceImpl.findByReportingManagerName(name);
	}
}
