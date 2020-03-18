package com.eip.service;

import java.util.List;
import com.eip.domain.Project;

public interface ProjectService {
	
	List<Project> findAll();
	
	Project save(Project project);
	
	void deleteById(String id);
	
	Project findById(String id);
}