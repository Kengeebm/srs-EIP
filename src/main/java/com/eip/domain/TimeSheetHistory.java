package com.eip.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;






@Document(collection="History")
public class  TimeSheetHistory implements Serializable{
	
private static final long serialVersionUID = 1L;
        @Id
        private String id;
        private String ts_id;	
    	private LocalDate dateTime;
    	private String status;
    	private String client;
    	private String project;
    	private String task;
    	private Long efforts;
    	private String userLogin;
        private String updatedBy;
        private LocalDate updatedOn;
    	
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTs_id() {
			return ts_id;
		}
		public void setTs_id(String ts_id) {
			this.ts_id = ts_id;
		}
		public LocalDate getDateTime() {
			return dateTime;
		}
		public void setDateTime(LocalDate date_time) {
			this.dateTime = date_time;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getClient() {
			return client;
		}
		public void setClient(String client) {
			this.client = client;
		}
		public String getProject() {
			return project;
		}
		public void setProject(String project) {
			this.project = project;
		}
		public String getTask() {
			return task;
		}
		public void setTask(String task) {
			this.task = task;
		}
		public Long getEfforts() {
			return efforts;
		}
		public void setEfforts(Long efforts) {
			this.efforts = efforts;
		}
		public String getUserLogin() {
			return userLogin;
		}
		public void setUserLogin(String user_login) {
			this.userLogin = user_login;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
		public LocalDate getUpdatedOn() {
			return updatedOn;
		}
		public void setUpdatedOn(LocalDate updatedOn) {
			this.updatedOn = updatedOn;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "TimeSheetHistory [id=" + id + ", ts_id=" + ts_id + ", dateTime=" + dateTime + ", status=" + status
					+ ", client=" + client + ", project=" + project + ", task=" + task + ", efforts=" + efforts
					+ ", user_login=" + userLogin + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
		}
        
		
	
		
		
		
	}



