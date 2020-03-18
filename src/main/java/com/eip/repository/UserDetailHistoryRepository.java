package com.eip.repository;

import com.eip.domain.UserDetailHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailHistoryRepository extends MongoRepository<UserDetailHistory, String> {

}
