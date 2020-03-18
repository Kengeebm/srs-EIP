package com.eip.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eip.domain.ReportingManager;
import com.eip.repository.ReportingManagerRepository;
import com.eip.service.ReportingManagerService;

@Service
public class ReportingManagerServiceImpl implements ReportingManagerService {
	private static final Logger logger = LoggerFactory.getLogger(ReportingManagerServiceImpl.class);

	@Autowired
	ReportingManagerRepository reportingManagerRepository;

	@Override
	public List<ReportingManager> findAll() {
		return reportingManagerRepository.findAll();
	}

	@Override
	public void save(ReportingManager reportingManager) {
        reportingManagerRepository.save(reportingManager);
	}

	@Override
	public void update(ReportingManager reportingManager) {
		reportingManagerRepository.save(reportingManager);
	}

	@Override
	public Optional<ReportingManager> findById(String id) {
	    return reportingManagerRepository.findById(id);
	}

	@Override
	public void delete(String id) {
		reportingManagerRepository.deleteById(id);
	}

	@Override
	public String findByName(String name) {
		List<ReportingManager> reportingmanagersdata = reportingManagerRepository.findAll();
		String rmmail = null;
		for (ReportingManager rm : reportingmanagersdata) {
			if (rm.getReportingManagerName().equals(name)) {
				rmmail = rm.getReportingManagerEmail();
			}
		}
		return rmmail;
	}

	@Override
	public List<ReportingManager> findByReportingManagerName(String name) {
		return reportingManagerRepository.findByReportingManagerName(name);
	}
}
