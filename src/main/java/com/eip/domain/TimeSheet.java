package com.eip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "timesheet")
public class TimeSheet implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

	@Field("user_name")
    private String userName;

    @Field("email_id")
    private String emailId;

    @Field("save_date")
    private LocalDateTime saveDate;

    @Field("submit_date")
    private LocalDate submitDate;
    
    @Field("submit_time")
    private String submitTime;

    @Field("employee_id")
    private String employeeId;

    @Field("approve_status")
    private String approveStatus;
    
    @Field("approved_by")
    private String approvedBy;
    
    @Field("approved_date")
    private LocalDate approvedDate;
    
    @Field("approved_time")
    private String approvedTime;
    
    @Field("no_workingdays")
    private String noWorkingDays;
    
    @Field("no_workfromhome")
    private String noWorkFromHome;
    
    @Field("no_workfromclientlocation")
    private String noWorkFromClientLocation;
    
    @Field("no_compoff")
    private String noCompOff;
    
    @Field("no_leaves")
    private String noLeaves;

    @Field("timesheet_date_status")
    private List<TimeSheetDateStatus> timeSheetDateStatusList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDateTime getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(LocalDateTime saveDate) {
		this.saveDate = saveDate;
	}

	public LocalDate getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(LocalDate submitDate) {
		this.submitDate = submitDate;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDate getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(LocalDate approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(String approvedTime) {
		this.approvedTime = approvedTime;
	}

	public String getNoWorkingDays() {
		return noWorkingDays;
	}

	public void setNoWorkingDays(String noWorkingDays) {
		this.noWorkingDays = noWorkingDays;
	}

	public String getNoWorkFromHome() {
		return noWorkFromHome;
	}

	public void setNoWorkFromHome(String noWorkFromHome) {
		this.noWorkFromHome = noWorkFromHome;
	}

	public String getNoWorkFromClientLocation() {
		return noWorkFromClientLocation;
	}

	public void setNoWorkFromClientLocation(String noWorkFromClientLocation) {
		this.noWorkFromClientLocation = noWorkFromClientLocation;
	}

	public String getNoCompOff() {
		return noCompOff;
	}

	public void setNoCompOff(String noCompOff) {
		this.noCompOff = noCompOff;
	}

	public String getNoLeaves() {
		return noLeaves;
	}

	public void setNoLeaves(String noLeaves) {
		this.noLeaves = noLeaves;
	}

	public List<TimeSheetDateStatus> getTimeSheetDateStatusList() {
		return timeSheetDateStatusList;
	}

	public void setTimeSheetDateStatusList(List<TimeSheetDateStatus> timeSheetDateStatusList) {
		this.timeSheetDateStatusList = timeSheetDateStatusList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TimeSheet [id=" + id + ", userName=" + userName + ", emailId=" + emailId + ", saveDate=" + saveDate
				+ ", submitDate=" + submitDate + ", submitTime=" + submitTime + ", employeeId=" + employeeId
				+ ", approveStatus=" + approveStatus + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate
				+ ", approvedTime=" + approvedTime + ", noWorkingDays=" + noWorkingDays + ", noWorkFromHome="
				+ noWorkFromHome + ", noWorkFromClientLocation=" + noWorkFromClientLocation + ", noCompOff=" + noCompOff
				+ ", noLeaves=" + noLeaves + ", timeSheetDateStatusList=" + timeSheetDateStatusList + "]";
	}

	public TimeSheet() {
		super();
	}
}
