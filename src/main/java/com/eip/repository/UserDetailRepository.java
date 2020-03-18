package com.eip.repository;

import com.eip.domain.UserDetail;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends MongoRepository<UserDetail, String> {

    UserDetail findByEmail(String email);

	Optional<UserDetail> findByLogin(String login);
}
