package com.eip.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.eip.domain.TimeSheetHistory;
@Repository
public interface TimeSheetHistoryRepository extends MongoRepository<TimeSheetHistory, String> {
	public List<TimeSheetHistory> findByDateTimeBetween(LocalDate fromDate, LocalDate toDate) ;
	List<TimeSheetHistory> findByUserLogin(String userLogin);}