package com.eip.web.rest;

import java.net.URISyntaxException;import java.util.List;

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
import com.eip.service.LeaveTypeService;

@RestController
@RequestMapping("/api")
public class LeaveTypeController {

    private static final Logger logger = LoggerFactory.getLogger(LeaveTypeController.class);


	@Autowired
	LeaveTypeService  leaveTypeService;

	@GetMapping("/leavetype")
	public List<LeaveType> getLeaveTypes(){
		return leaveTypeService.getLeaveTypes();

	}

    @GetMapping("/leavetype/{id}")
    public LeaveType getLeaveTypes(@PathVariable String id){
        return leaveTypeService.getLeaveTypes(id);
    }

	@PostMapping("/leavetype")
	public ResponseEntity<Boolean> save(@RequestBody LeaveType leaveType) {
	      if (leaveType.getId() == null) {
	      List<LeaveType> leaveTypeList = leaveTypeService.getLeaveTypes();
	      if (leaveTypeList.stream().filter(data -> data.getName().equals(leaveType.getName())).count()>0) {
	          return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
	      }
	      leaveTypeService.save(leaveType);
	      } else {
	      leaveTypeService.save(leaveType);
	      }
	        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	 }

	@DeleteMapping("leavetype/{id}")
    public void delete(@PathVariable String id) {
        logger.info("delete id:" + id);
       leaveTypeService.delete(id);
    }
	
    @PutMapping("/leavetype")
	public ResponseEntity<Boolean> update(@RequestBody LeaveType leaveType) {
	      if (leaveType.getId() != null) {
	      List<LeaveType> leaveTypeList = leaveTypeService.getLeaveTypes();
	      if (leaveTypeList.stream().filter(data -> data.getName().equals(leaveType.getName())).count()>0) {
	          return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
	      }
	      leaveTypeService.save(leaveType);
	      } else {
	      leaveTypeService.save(leaveType);
	      }
	        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	 }

}
