package com.eip.serviceImpl;

import com.eip.domain.UserDetail;
import com.eip.repository.UserDetailRepository;
import com.eip.service.UserDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailService {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    UserDetailRepository detailRepository;

    @Override
    public List<UserDetail> findAll() {
    	logger.info("request to fetchALL from UserDetail {}");
        return detailRepository.findAll();
    }

    @Override
    public UserDetail findByEmail(String email) {
    	logger.info("request to fetchALLByEmailId from UserDetail {}",email);
        return detailRepository.findByEmail(email);
    }

    @Override
    public Optional<UserDetail> findById(String id) {
    	logger.info("request to fetchALLById from UserDetail {}",id);
        return detailRepository.findById(id);
    }

    @Override
    public UserDetail create(UserDetail candidate) {
    	logger.info("request to save UserDetail {}",candidate);
        return detailRepository.save(candidate);
    }

    @Override
    public List<UserDetail> createAll(List<UserDetail> candidates) {
    	logger.info("request to saveAll UserDetail {}",candidates);
        return detailRepository.saveAll(candidates);
    }

    @Override
    public UserDetail update(UserDetail candidate) {
    	logger.debug("request to update UserDetail {}",candidate);
        return detailRepository.save(candidate);
    }

    @Override
    public List<UserDetail> updateAll(List<UserDetail> candidates) {
    	logger.debug("request to updateAll UserDetail {}",candidates);
        return detailRepository.saveAll(candidates);
    }

    @Override
    public void delete(String id) {
    	logger.debug("request to deleteById UserDetail {}",id);
        detailRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<UserDetail> candidates) {
    	logger.debug("request to deleteAllById UserDetail {}",candidates);
        detailRepository.deleteAll(candidates);

    }

    @Override
    public Optional<UserDetail> findByLogin(String login) {
        return detailRepository.findByLogin(login);
    }
}
