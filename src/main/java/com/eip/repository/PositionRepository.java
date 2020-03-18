package com.eip.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eip.domain.Position;

@Repository
public interface PositionRepository extends MongoRepository<Position, String> {

}
