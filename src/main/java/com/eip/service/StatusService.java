package com.eip.service;

import java.util.List;

import com.eip.domain.Status;

public interface StatusService {

	List<Status> getStatus();

	Status save(Status status);

	 void delete(String id);

    Status getStatusById(String id);
}
