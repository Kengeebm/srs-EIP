package com.eip.service;

import com.eip.domain.PublicHolidays;

import java.util.List;
import java.util.Optional;

public interface PublicHolidaysService {

    List<PublicHolidays> findAll();

    void save(PublicHolidays holidays);
    
    void update(PublicHolidays holidays);

    Optional<PublicHolidays> findById(String id);
    
    void delete(String id);
   
	List<PublicHolidays> findByProjectCode(String projectCode);
}