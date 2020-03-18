package com.eip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "public_holidays")
public class PublicHolidays {

    @Id
    private String id;

    @Field("holiday_name")
    private String name;

    @Field("holiday_date")
    private LocalDate date;

    @Field("project_code")
    private String projectCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Override
	public String toString() {
		return "PublicHolidays [id=" + id + ", name=" + name + ", date=" + date + ", projectCode=" + projectCode + "]";
	}

	public PublicHolidays() {
		super();
	}
}
