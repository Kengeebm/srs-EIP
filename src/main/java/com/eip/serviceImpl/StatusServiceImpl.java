package com.eip.serviceImpl;

import com.eip.domain.Status;
import com.eip.repository.StatusRepository;
import com.eip.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Status service.
 */
@Service
public class StatusServiceImpl implements StatusService {

    /**
     * The Status repository.
     */
    @Autowired
    StatusRepository statusRepository;

    @Override
    public List<Status> getStatus() {
        return statusRepository.findAll();
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void delete(String id) {
        statusRepository.deleteById(id);
    }

    @Override
    public Status getStatusById(String id) {
        return statusRepository.findById(id).get();
    }

}
