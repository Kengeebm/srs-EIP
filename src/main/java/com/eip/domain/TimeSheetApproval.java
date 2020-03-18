package com.eip.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="timesheet_approval")
public class TimeSheetApproval {
	
	@Id
	private String id;
	private LocalDate currentDate;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String description;
	private String managerName;
	private String managerEmail;
	private String userLogin;
	private Integer approvalFlag= 0;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public Integer getApprovalFlag() {
		return approvalFlag;
	}
	public void setApprovalFlag(Integer approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	@Override
	public String toString() {
		return "TimeSheetApproval [id=" + id + ", currentDate=" + currentDate + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", description=" + description + ", managerName=" + managerName + ", managerEmail="
				+ managerEmail + ", userLogin=" + userLogin + ", approvalFlag=" + approvalFlag + "]";
	}
		
}
