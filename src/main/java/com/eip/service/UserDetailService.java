package com.eip.service;

import java.util.List;
import java.util.Optional;

import com.eip.domain.UserDetail;

public interface UserDetailService {

    void deleteAll(List<UserDetail> candidates);

    void delete(String id);

    List<UserDetail> updateAll(List<UserDetail> candidates);

    UserDetail update(UserDetail candidate);

    List<UserDetail> createAll(List<UserDetail> candidates);

    UserDetail create(UserDetail candidate);

    Optional<UserDetail> findById(String id);

    List<UserDetail> findAll();

    UserDetail findByEmail(String email);

	Optional<UserDetail> findByLogin(String login);
}
