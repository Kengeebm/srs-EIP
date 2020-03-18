package com.eip.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eip.domain.WorkLocation;
import com.eip.repository.WorkLocationRepository;
import com.eip.service.WorkLocationService;

@Service
public class WorkLocationServiceImpl implements WorkLocationService {
	
	@Autowired
	WorkLocationRepository workLocationRepository;
	
	@Override
	public List<WorkLocation> getLocation() {
		List<WorkLocation> list=new ArrayList<WorkLocation>();
		WorkLocation location1= new WorkLocation("1", "Bangalore");
		WorkLocation location2= new WorkLocation("2", "Hyderabad");
		WorkLocation location3= new WorkLocation("3", "Pune");
		WorkLocation location4= new WorkLocation("4", "New Delhi");
		list.add(location1);
		list.add(location2);
		list.add(location3);
		list.add(location4);
		return list;
	}

}
