package com.eip.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eip.domain.UnfreezedList;

public interface UnfreezedListRepository extends MongoRepository<UnfreezedList, String> {
	
}
