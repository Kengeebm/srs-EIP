package com.eip.repository;

import com.eip.domain.TimeSheet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * The interface Time sheet repository.
 */
public interface TimeSheetRepository extends MongoRepository<TimeSheet, String> {

    /**
     * Find by date between list.
     *
     * @param fromDate the from date
     * @param toDate   the to date
     * @return the list
     */
    //List<TimeSheet> findByDateBetween(LocalDate fromDate, LocalDate toDate);

    /**
     * Find by employee id list.
     *
     * @param userLogin the user login
     * @return the list
     */
    List<TimeSheet> findByEmployeeId(String userLogin);

    /**
     * Find by approve status list.
     *
     * @param approveStatus the approval status
     * @return the list
     */
    List<TimeSheet> findByApproveStatus(String approveStatus);

    /**
     * Find by approve status is not list.
     *
     * @return the list
     */
    List<TimeSheet> findByApproveStatusNull();

    List<TimeSheet> findByApproveStatusIn(String approveStatus);

    List<TimeSheet> findByApproveStatusIn(List<String> status);
    
    Long countByApproveStatus(String status);
    
    List<TimeSheet> findByEmailIdAndApproveStatus(String emailId, String approveStatus);

}
