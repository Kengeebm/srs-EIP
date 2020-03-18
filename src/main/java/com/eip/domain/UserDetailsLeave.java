package com.eip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document(collection = "user_details_leave")
public class UserDetailsLeave implements Serializable {

	@Id
	private String id;

	@Field("employee_id")
	private String employeeId;

	@Field("emp_name")
	private String empName;

	@Field("emp_mail")
	private String empMail;

	@Field("manager_id")
	private String reportingManagerEmail;

	@Field("notification_to")
	private String notificationTo;

	@Field("leave_type")
	private String leaveType;

	@Field("from_date")
	private String fromDate;

	@Field("to_date")
	private String toDate;

	@Field("total_days")
	private int totalDays;

	@Field("reason_for_leave")
	private String reasonForLeave;

	@Field(value = "status")
	private String status;

	@Field(value = "submit_date")
	private String submitDate;

	@Field(value = "submit_time")
	private String submitTime;

	@Field(value = "approved_by")
	private String approvedBy;

	@Field(value = "approved_date")
	private String approvedDate;

	@Field(value = "approved_time")
	private String approvedTime;

	@Field("reason_for_reject")
	private String reasonForReject;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpMail() {
		return empMail;
	}

	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}

	public String getReportingManagerEmail() {
		return reportingManagerEmail;
	}

	public void setReportingManagerEmail(String reportingManagerEmail) {
		this.reportingManagerEmail = reportingManagerEmail;
	}

	public String getNotificationTo() {
		return notificationTo;
	}

	public void setNotificationTo(String notificationTo) {
		this.notificationTo = notificationTo;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public String getReasonForLeave() {
		return reasonForLeave;
	}

	public void setReasonForLeave(String reasonForLeave) {
		this.reasonForLeave = reasonForLeave;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(String approvedTime) {
		this.approvedTime = approvedTime;
	}

	public String getReasonForReject() {
		return reasonForReject;
	}

	public void setReasonForReject(String reasonForReject) {
		this.reasonForReject = reasonForReject;
	}

	@Override
	public String toString() {
		return "UserDetailsLeave [id=" + id + ", employeeId=" + employeeId + ", empName=" + empName + ", empMail="
				+ empMail + ", reportingManagerEmail=" + reportingManagerEmail + ", notificationTo=" + notificationTo
				+ ", leaveType=" + leaveType + ", fromDate=" + fromDate + ", toDate=" + toDate + ", totalDays="
				+ totalDays + ", reasonForLeave=" + reasonForLeave + ", status=" + status + ", submitDate=" + submitDate
				+ ", submitTime=" + submitTime + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate
				+ ", approvedTime=" + approvedTime + ", reasonForReject=" + reasonForReject + "]";
	}

	public UserDetailsLeave() {
		super();
	}
}
