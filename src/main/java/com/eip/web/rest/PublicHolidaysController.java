package com.eip.web.rest;

import com.eip.domain.PublicHolidays;
import com.eip.domain.Status;
import com.eip.repository.PublicHolidaysRepository;
import com.eip.service.PublicHolidaysService;
import com.eip.serviceImpl.PublicHolidaysServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PublicHolidaysController {
	private static final Logger logger = LoggerFactory.getLogger(PublicHolidaysServiceImpl.class);


    @Autowired
    PublicHolidaysService publicHolidaysService;

    @GetMapping("/publicHolidays")
    public List<PublicHolidays> findAll() {
    	logger.info("request to fetchAll from publicHolidays {}");
        return publicHolidaysService.findAll();
    }
    
    @GetMapping("/publicHolidays/{id}")
    public Optional<PublicHolidays> findById(@PathVariable String id) {
    	return publicHolidaysService.findById(id);
    }
    
    @PostMapping("/publicHolidays")
	public ResponseEntity<Boolean> save(@RequestBody PublicHolidays holidays) {
	      if (holidays.getId() == null) {
	      List<PublicHolidays> holidayList = publicHolidaysService.findAll();
	      if (holidayList.stream().filter(data -> data.getName().equals(holidays.getName()) && data.getDate().equals(holidays.getDate()) && data.getProjectCode().contentEquals(holidays.getProjectCode())).count()>0) {
	          return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
	      }
	      publicHolidaysService.save(holidays);
	      } else {
	      publicHolidaysService.save(holidays);
	      }
	        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	 }
    
    @PutMapping("/publicHolidays")
	public ResponseEntity<Boolean> update(@RequestBody PublicHolidays holidays) {
	      if (holidays.getId() != null) {
	      List<PublicHolidays> holidayList = publicHolidaysService.findAll();
	      if (holidayList.stream().filter(data -> data.getName().equals(holidays.getName()) && data.getDate().equals(holidays.getDate()) && data.getProjectCode().contentEquals(holidays.getProjectCode())).count()>0) {
	          return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
	      }
	      publicHolidaysService.save(holidays);
	      } else {
	      publicHolidaysService.save(holidays);
	      }
	        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	 }
    
    @DeleteMapping("/publicHolidays/{id}")
	public void delete(@PathVariable String id) {
    	publicHolidaysService.delete(id);
    }
    
    @GetMapping("/publicHolidays/project/{projectCode}")
	public List<PublicHolidays> findByProjectCode(@PathVariable String projectCode) {
       	logger.info("request to fetchAll By project Code from publicHolidays {}");
		return publicHolidaysService.findByProjectCode(projectCode);
	}
}
