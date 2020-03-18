package com.eip.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eip.domain.WorkLocation;
import com.eip.service.WorkLocationService;

@RestController
@RequestMapping("/api")
public class WorkLocationController {
	
	@Autowired
	WorkLocationService  workLocationService;
	
	@GetMapping("/location")
	public List<WorkLocation> getLocation(){
		return workLocationService.getLocation();
		
	}
	
}
