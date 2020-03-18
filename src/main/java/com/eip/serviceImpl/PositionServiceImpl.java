package com.eip.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eip.domain.Position;
import com.eip.repository.PositionRepository;
import com.eip.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {
	
	@Autowired
	PositionRepository positionRepository;

	@Override
	public List<Position> getPosition() {
		List<Position> list=new ArrayList<Position>();
		Position Position1= new Position("1", "Java Developer");
		Position Position2= new Position("2", "System Admin");
		Position Position3= new Position("3", "Tester");
		list.add(Position1);
		list.add(Position2);
		list.add(Position3);
		return list;	}
	
	

}
