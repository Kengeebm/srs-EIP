package com.eip.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eip.domain.Status;

@Repository
public interface StatusRepository extends MongoRepository<Status, String> {

}
