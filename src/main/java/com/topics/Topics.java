package com.topics;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topics {
	@Id
	private int id;
	private String name;
	private String Description;
	
	public Topics() {
		
	}
	public Topics(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		Description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

}
