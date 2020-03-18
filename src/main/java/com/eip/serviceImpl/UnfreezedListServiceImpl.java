package com.eip.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eip.domain.UnfreezedList;
import com.eip.repository.UnfreezedListRepository;
import com.eip.service.UnfreezedListService;

@Service
@Transactional
public class UnfreezedListServiceImpl implements UnfreezedListService {

	@Autowired
	UnfreezedListRepository unfreezedListRepo;
	
	@Override
	public List<UnfreezedList> findAll() {
		return unfreezedListRepo.findAll();
	}

	@Override
	public UnfreezedList save(UnfreezedList unfreezedList) {
		return unfreezedListRepo.save(unfreezedList);
	}
}
