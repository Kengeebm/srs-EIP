package com.eip.domain;

import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userdetail")
public class UserDetail {

    @Id
    String id;

    @Unique
    String empId;

    String login;

    String firstName;

    String lastName;

    String email;
    
    String reportingManagerEmail;

    String position;

    String contactNo;

    Double experience;

    String workLocation;

    String dateOfJoin;

    Double expWithSrs;
    
    String projectCode;
    
    String notificationTo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReportingManagerEmail() {
		return reportingManagerEmail;
	}

	public void setReportingManagerEmail(String reportingManagerEmail) {
		this.reportingManagerEmail = reportingManagerEmail;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Double getExperience() {
		return experience;
	}

	public void setExperience(Double experience) {
		this.experience = experience;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(String dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public Double getExpWithSrs() {
		return expWithSrs;
	}

	public void setExpWithSrs(Double expWithSrs) {
		this.expWithSrs = expWithSrs;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getNotificationTo() {
		return notificationTo;
	}

	public void setNotificationTo(String notificationTo) {
		this.notificationTo = notificationTo;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", empId=" + empId + ", login=" + login + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", reportingManagerEmail=" + reportingManagerEmail
				+ ", position=" + position + ", contactNo=" + contactNo + ", experience=" + experience
				+ ", workLocation=" + workLocation + ", dateOfJoin=" + dateOfJoin + ", expWithSrs=" + expWithSrs
				+ ", projectCode=" + projectCode + ", notificationTo=" + notificationTo + "]";
	}

	

    
}
