package com.eip.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eip.domain.Position;
import com.eip.service.PositionService;

@RestController
@RequestMapping("/api")
public class PositionController {
	
	@Autowired
	PositionService  positionService;
	
	@GetMapping("/position")
	public List<Position> getPosition(){
		return positionService.getPosition();
		
	}
	
}
