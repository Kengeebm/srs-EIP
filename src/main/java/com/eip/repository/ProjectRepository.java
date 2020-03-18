package com.eip.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eip.domain.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

}
