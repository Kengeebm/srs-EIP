package com.eip.web.rest;

import java.net.URISyntaxException;
import java.util.List;

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

import com.eip.domain.LeaveType;
import com.eip.domain.Status;
import com.eip.service.StatusService;

@RestController
@RequestMapping("/api")
public class StatusController {


    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);


	@Autowired
	StatusService  statusService;

	@GetMapping("/status")
	public List<Status> getPosition(){
		return statusService.getStatus();

	}


	@PostMapping("/status")
	public ResponseEntity<Boolean> save(@RequestBody Status status) {
	      if (status.getId() == null) {
	      List<Status> statusList = statusService.getStatus();
	      if (statusList.stream().filter(data -> data.getName().equals(status.getName())).count()>0) {
	          return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
	      }
	      statusService.save(status);
	      } else {
	    	  statusService.save(status);
	      }
	        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	 }
	
    @PutMapping("/status")
	public ResponseEntity<Boolean> update(@RequestBody Status status) {
	      if (status.getId() != null) {
	      List<Status> statusList = statusService.getStatus();
	      if (statusList.stream().filter(data -> data.getName().equals(status.getName())).count()>0) {
	          return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
	      }
	      statusService.save(status);
	      } else {
	      statusService.save(status);
	      }
	        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	 }

    @DeleteMapping("/status/{id}")
    public void delete(@PathVariable String id) {
        logger.info("delete id:" + id);
        statusService.delete(id);
    }

    @GetMapping("/status/{id}")
    public Status getStatusById(@PathVariable String id){
        return statusService.getStatusById(id);
    }



}
