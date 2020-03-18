package com.eip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "work_location")
public class WorkLocation {

@Id	
private String id;

private String location;

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}



public WorkLocation(String id, String location) {
	super();
	this.id = id;
	this.location = location;
}
@Override
public String toString() {
	return "WorkLocation [id=" + id + ", location=" + location + "]";
}



}
