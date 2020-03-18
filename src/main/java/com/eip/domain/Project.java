package com.eip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "project")
public class Project {


    @Id
    private String id;

    @Field("project_name")
    private String name;

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

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", projectCode=" + projectCode + "]";
	}

	public Project() {
		super();
	}
}
