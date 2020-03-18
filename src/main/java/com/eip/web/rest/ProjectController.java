package com.eip.web.rest;

import java.util.List;
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
import com.eip.domain.Project;
import com.eip.serviceImpl.ProjectServiceImpl;

@RestController
@RequestMapping("/api")
public class ProjectController {
	 
	@Autowired
	ProjectServiceImpl projectService;
	
	@GetMapping("/project")
	public List<Project> findAll() {
		return projectService.findAll();
	}
	
	@PostMapping("/project")
	public ResponseEntity<Boolean> save(@RequestBody Project project) {
	      if (project.getId() == null) {
	      List<Project> projectList = projectService.findAll();
	      if (projectList.stream().filter(data -> data.getName().equals(project.getName()) || data.getProjectCode().contentEquals(project.getProjectCode())).count()>0) {	
	          return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
	      }
	      projectService.save(project);
	      } else {
	    	  projectService.save(project);
	      }
	        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@PutMapping("/project")
	public ResponseEntity<Boolean> update(@RequestBody Project project) {
	      if (project.getId() != null) {
	      List<Project> projectList = projectService.findAll();
	      if (projectList.stream().filter(data -> data.getName().equals(project.getName()) || data.getProjectCode().contentEquals(project.getProjectCode())).count()>0) {	
	    	  return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
	      }
	      projectService.save(project);
	      } else {
	    	  projectService.save(project);
	      }
	        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	 }
	
	 @DeleteMapping("/project/{id}")
	 public void deleteById(@PathVariable String id) {
		 projectService.deleteById(id);
	 }
	 
	 @GetMapping("/project/{id}")
	 public Project findById(@PathVariable String id){
	        return projectService.findById(id);
	 }
}
