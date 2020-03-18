package com.eip.domain;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "unfreezedList")
public class UnfreezedList implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private String id;
    
	@Field("employee_id")
    private String employeeId;
	
    @Field("user_name")
    private String userName;

    @Field("submit_date")
    private LocalDate submitDate;
    
    @Field("submit_time")
    private String submitTime;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UnfreezedList [id=" + id + ", employeeId=" + employeeId + ", userName=" + userName + ", submitDate="
				+ submitDate + ", submitTime=" + submitTime + "]";
	}

	public UnfreezedList() {
		super();
	}
}
