package com.eip.serviceImpl;

import com.eip.domain.UserDetailHistory;
import com.eip.repository.UserDetailHistoryRepository;
import com.eip.service.UserDetailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserDetailHistoryServiceImpl implements UserDetailHistoryService {


    @Autowired
    UserDetailHistoryRepository userDetailHistoryRepository;

    @Override
    public void save(UserDetailHistory userDetailHistory) {
        userDetailHistoryRepository.save(userDetailHistory);
    }

    @Override
    public List<UserDetailHistory> findAll() {
        return userDetailHistoryRepository.findAll();
    }
}
