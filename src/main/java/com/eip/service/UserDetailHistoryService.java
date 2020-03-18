package com.eip.service;

import com.eip.domain.UserDetailHistory;

import java.util.List;


public interface UserDetailHistoryService {

    void save(UserDetailHistory request);

    List<UserDetailHistory> findAll();


}
