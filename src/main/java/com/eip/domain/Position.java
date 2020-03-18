package com.eip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "position")
public class Position {

	@Id	
	private String id;

	private String position;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getposition() {
		return position;
	}
	public void setposition(String position) {
		this.position = position;
	}
	

	public Position(String id, String position) {
		super();
		this.id = id;
		this.position = position;
	}
	@Override
	public String toString() {
		return "Workposition [id=" + id + ", position=" + position + "]";
	}


}
