package com.eip.domain;

import org.springframework.data.annotation.Id;

public class ReportingManager {

	@Id	
	private String id;
	
	private String reportingManagerId;
    
	private String reportingManagerName;
	
	private String reportingManagerEmail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportingManagerId() {
		return reportingManagerId;
	}

	public void setReportingManagerId(String reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}

	public String getReportingManagerName() {
		return reportingManagerName;
	}

	public void setReportingManagerName(String reportingManagerName) {
		this.reportingManagerName = reportingManagerName;
	}

	public String getReportingManagerEmail() {
		return reportingManagerEmail;
	}

	public void setReportingManagerEmail(String reportingManagerEmail) {
		this.reportingManagerEmail = reportingManagerEmail;
	}

	@Override
	public String toString() {
		return "ReportingManager [id=" + id + ", reportingManagerId=" + reportingManagerId + ", reportingManagerName="
				+ reportingManagerName + ", reportingManagerEmail=" + reportingManagerEmail + ", getId()=" + getId()
				+ ", getReportingManagerId()=" + getReportingManagerId() + ", getReportingManagerName()="
				+ getReportingManagerName() + ", getReportingManagerEmail()=" + getReportingManagerEmail()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
