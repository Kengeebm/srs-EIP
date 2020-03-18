package com.eip.serviceImpl;

import com.eip.domain.PublicHolidays;
import com.eip.repository.PublicHolidaysRepository;
import com.eip.service.PublicHolidaysService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicHolidaysServiceImpl implements PublicHolidaysService {
	private static final Logger logger = LoggerFactory.getLogger(PublicHolidaysServiceImpl.class);


    @Autowired
    PublicHolidaysRepository publicHolidaysRepository;

    @Override
    public List<PublicHolidays> findAll() {
    	logger.info("Request for fetchAll from publicHolidays");
        return publicHolidaysRepository.findAll();
    }

    @Override
    public void save(PublicHolidays holidays) {
    	logger.info("Request for fetchAll from publicHolidays",holidays);
        publicHolidaysRepository.save(holidays);
    }

    @Override
    public Optional<PublicHolidays> findById(String id) {
    	logger.info("Request for fetchAll from publicHolidays",id);
        return publicHolidaysRepository.findById(id);
    }

	@Override
	public void delete(String id) {
    	logger.info("Request to delete from publicHolidays",id);
    	publicHolidaysRepository.deleteById(id);
		
	}

	@Override
	public void update(PublicHolidays holidays) {
        publicHolidaysRepository.save(holidays);

	}

	@Override
	public List<PublicHolidays> findByProjectCode(String projectCode) {
		return publicHolidaysRepository.findByProjectCode(projectCode);
	}
}