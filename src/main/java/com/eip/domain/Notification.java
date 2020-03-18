package com.eip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "notification")
public class Notification {

	@Id
	private String id;

	@Field("message")
	private String message;

	@Field("published_date")
	private String publishedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", message=" + message + ", publishedDate=" + publishedDate + "]";
	}

	public Notification(String id, String message, String publishedDate) {
		super();
		this.id = id;
		this.message = message;
		this.publishedDate = publishedDate;
	}

	public Notification() {
		super();
	}
}
