package com.eip.service;

import java.util.List;
import java.util.Optional;

import com.eip.domain.UnfreezedList;

public interface UnfreezedListService {

	List<UnfreezedList> findAll();
	
	UnfreezedList save(UnfreezedList unfreezedList);
	
}
