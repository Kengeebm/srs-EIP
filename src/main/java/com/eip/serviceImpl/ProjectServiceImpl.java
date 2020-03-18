package com.eip.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eip.domain.Project;
import com.eip.repository.ProjectRepository;
import com.eip.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public Project save(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public void deleteById(String id) {
		projectRepository.deleteById(id);
	}

	@Override
	public Project findById(String id) {
		return projectRepository.findById(id).get();
	}
}
