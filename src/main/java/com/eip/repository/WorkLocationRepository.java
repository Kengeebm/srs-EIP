package com.eip.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eip.domain.WorkLocation;

@Repository
public interface WorkLocationRepository extends MongoRepository<WorkLocation, String> {

	
}
