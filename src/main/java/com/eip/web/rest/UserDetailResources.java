package com.eip.web.rest;

import com.eip.domain.UserDetail;
import com.eip.service.UserDetailService;

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
public class UserDetailResources {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailResources.class);

    @Autowired
    UserDetailService detailService;

    @GetMapping("/user/detail")
    public ResponseEntity<List<UserDetail>> findAll() {
    	logger.info( "request for  fetchAllById from timesheet");
        return new ResponseEntity<List<UserDetail>>(detailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/detail/{id}")
    public ResponseEntity<Optional<UserDetail>> findById(@PathVariable String id) {
    	logger.info( "request for  fetchAllById from timesheet",id);
        return new ResponseEntity<Optional<UserDetail>>(detailService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/user/detail/create")
    public ResponseEntity<UserDetail> create(@RequestBody UserDetail candidate) {
    	logger.info( "request for save data from timesheet",candidate);
        return new ResponseEntity<UserDetail>(detailService.create(candidate), HttpStatus.OK);
    }

    @PostMapping("/user/detail/createAll")
    public ResponseEntity<List<UserDetail>> createAll(List<UserDetail> candidates) {
    	logger.info( "request for saveAll data from timesheet",candidates);
        return new ResponseEntity<List<UserDetail>>(detailService.createAll(candidates), HttpStatus.OK);
    }

    @PutMapping("/user/detail/update")
    public ResponseEntity<UserDetail> update(@RequestBody UserDetail userDetail) {
    	 logger.info( "request for update data from timesheet",userDetail);
        return new ResponseEntity<UserDetail>(detailService.update(userDetail), HttpStatus.OK);
    }

    @PutMapping("/user/detail/updateAll")
    public ResponseEntity<List<UserDetail>> updateAll(List<UserDetail> candidates) {
    	 logger.info( "request for update data from timesheet",candidates);
        return new ResponseEntity<List<UserDetail>>(detailService.updateAll(candidates), HttpStatus.OK);
    }

    @DeleteMapping("/user/detail/delete")
    public ResponseEntity<String> delete(String id) {
    	 logger.debug( "request for delete data from timesheet",id);
        detailService.delete(id);
    return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping("/user/detail/deleteAll")
    public ResponseEntity<String> deleteAll(List<UserDetail> candidates) {
    	logger.debug( "request for delete from timesheet",candidates);
        detailService.deleteAll(candidates);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/user-detail/{email}")
    public ResponseEntity<UserDetail> findByEmailId(@PathVariable String email) {
    	logger.info( "request for  fetchAllById from timesheet",email);
        return new ResponseEntity<UserDetail>(detailService.findByEmail(email), HttpStatus.OK);
    }
}
