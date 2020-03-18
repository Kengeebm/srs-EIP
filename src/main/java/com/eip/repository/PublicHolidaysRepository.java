package com.eip.repository;

import com.eip.domain.PublicHolidays;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicHolidaysRepository extends MongoRepository<PublicHolidays, String> {
	
	List<PublicHolidays> findByProjectCode(String projectCode);

}
